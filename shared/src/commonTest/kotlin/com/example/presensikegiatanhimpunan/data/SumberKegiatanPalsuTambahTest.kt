package com.example.presensikegiatanhimpunan.data

import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SumberKegiatanPalsuTambahTest {

    @Test
    fun tambahMenambahDaftar() = runTest {
        val sumber = SumberKegiatanPalsu()
        val jumlahAwal = sumber.daftar.value.size

        val baru = sumber.tambah("Rapat Akbar", null, "Aula ITERA", "2026-11-20")

        assertEquals(jumlahAwal + 1, sumber.daftar.value.size)
        assertTrue(sumber.daftar.value.contains(baru))
    }

    @Test
    fun idKegiatanBaruUnik() = runTest {
        val sumber = SumberKegiatanPalsu()

        sumber.tambah("A", null, "Lokasi A", "2026-11-20")
        sumber.tambah("B", "Deskripsi", "Lokasi B", "2026-11-21")
        val daftar = sumber.daftar.value

        assertEquals(daftar.size, daftar.map { it.id }.distinct().size)
    }
}
