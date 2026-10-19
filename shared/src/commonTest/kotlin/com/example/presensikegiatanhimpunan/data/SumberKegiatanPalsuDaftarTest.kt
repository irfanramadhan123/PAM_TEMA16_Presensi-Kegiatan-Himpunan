package com.example.presensikegiatanhimpunan.data

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SumberKegiatanPalsuDaftarTest {

    @Test
    fun semuaMengembalikanMinimalSepuluhKegiatan() {
        val sumber = SumberKegiatanPalsu()

        val daftar = sumber.semua()

        assertTrue(daftar.size >= 10, "Jumlah kegiatan hanya ${daftar.size}")
    }

    @Test
    fun idKegiatanUnik() {
        val sumber = SumberKegiatanPalsu()

        val daftar = sumber.semua()
        val idUnik = daftar.map { it.id }.distinct()

        assertEquals(daftar.size, idUnik.size)
    }

    @Test
    fun statusKegiatanBervariasi() {
        val sumber = SumberKegiatanPalsu()

        val jumlahStatus = sumber.semua().map { it.status }.distinct().size

        assertTrue(jumlahStatus >= 2, "Status hanya $jumlahStatus jenis")
    }
}
