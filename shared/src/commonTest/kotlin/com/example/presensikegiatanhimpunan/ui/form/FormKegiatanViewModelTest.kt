package com.example.presensikegiatanhimpunan.ui.form

import com.example.presensikegiatanhimpunan.data.SumberKegiatanPalsu
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class FormKegiatanViewModelTest {

    private val dispatcher = StandardTestDispatcher()

    @BeforeTest
    fun aturMain() {
        Dispatchers.setMain(dispatcher)
    }

    @AfterTest
    fun resetMain() {
        Dispatchers.resetMain()
    }

    private fun isiFormValid(vm: FormKegiatanViewModel) {
        vm.ubahNama("Rapat Akbar")
        vm.ubahDeskripsi("Rapat seluruh anggota")
        vm.ubahLokasi("Aula ITERA")
        vm.ubahTanggal("2026-11-20")
    }

    @Test
    fun bisaSimpanFalseSaatFormKosong() = runTest {
        val vm = FormKegiatanViewModel(SumberKegiatanPalsu())

        val state = vm.uiState.value

        assertEquals("", state.nama)
        assertEquals("", state.deskripsi)
        assertEquals("", state.lokasi)
        assertEquals("", state.tanggal)
        assertFalse(state.bisaSimpan)
    }

    @Test
    fun ubahNamaMemotongTeksKe60Karakter() = runTest {
        val vm = FormKegiatanViewModel(SumberKegiatanPalsu())
        val teksPanjang = "A".repeat(80)

        vm.ubahNama(teksPanjang)

        assertEquals(60, vm.uiState.value.nama.length)
        assertEquals("A".repeat(60), vm.uiState.value.nama)
    }

    @Test
    fun simpanSaatTidakValidTidakMenambahDaftar() = runTest {
        val sumber = SumberKegiatanPalsu()
        val vm = FormKegiatanViewModel(sumber)
        val jumlahAwal = sumber.daftar.value.size

        vm.simpan()
        advanceUntilIdle()

        assertEquals(jumlahAwal, sumber.daftar.value.size)
        assertFalse(vm.uiState.value.sedangMenyimpan)
        assertNull(vm.uiState.value.pesan)
    }

    @Test
    fun simpanValidMenambahDaftarDanMengosongkanForm() = runTest {
        val sumber = SumberKegiatanPalsu()
        val vm = FormKegiatanViewModel(sumber)
        val jumlahAwal = sumber.daftar.value.size
        isiFormValid(vm)
        assertTrue(vm.uiState.value.bisaSimpan)

        vm.simpan()
        advanceUntilIdle()

        assertEquals(jumlahAwal + 1, sumber.daftar.value.size)
        val state = vm.uiState.value
        assertEquals("", state.nama)
        assertEquals("", state.deskripsi)
        assertEquals("", state.lokasi)
        assertEquals("", state.tanggal)
        assertFalse(state.sedangMenyimpan)
        assertEquals("Kegiatan tersimpan", state.pesan)
    }

    @Test
    fun simpanDuaKaliBerturutTurutHanyaMenambahSatu() = runTest {
        val sumber = SumberKegiatanPalsu()
        val vm = FormKegiatanViewModel(sumber)
        val jumlahAwal = sumber.daftar.value.size
        isiFormValid(vm)

        vm.simpan()
        vm.simpan()
        advanceUntilIdle()

        assertEquals(jumlahAwal + 1, sumber.daftar.value.size)
    }
}
