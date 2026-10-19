package com.example.presensikegiatanhimpunan.data

import com.example.presensikegiatanhimpunan.model.StatusKegiatan
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class SumberKegiatanPalsuTest {

    @Test
    fun ambilMengembalikanKegiatanYangSesuai() = runTest {
        val sumber = SumberKegiatanPalsu()

        val kegiatan = sumber.ambil(1)

        println("Data kegiatan: ${kegiatan.nama}")

        assertEquals(1, kegiatan.id)
        assertEquals("Kader", kegiatan.nama)
        assertEquals("Kegiatan kaderisasi anggota himpunan.", kegiatan.deskripsi)
        assertEquals("Gedung F ITERA", kegiatan.lokasi)
        assertEquals("2026-10-05", kegiatan.tanggal)
        assertEquals(StatusKegiatan.Dijadwalkan, kegiatan.status)
    }

    @Test
    fun pantauMengirimPerubahanStatusSecaraBerurutan() = runTest {
        val sumber = SumberKegiatanPalsu()
        val statusYangDiterima = mutableListOf<StatusKegiatan>()
        val idYangDiterima = mutableListOf<Int>()

        sumber.pantau(1).collect { kegiatan ->
            println("Status: ${kegiatan.status}")
            statusYangDiterima += kegiatan.status
            idYangDiterima += kegiatan.id
        }

        assertEquals(3, statusYangDiterima.size)
        assertEquals(
            listOf(
                StatusKegiatan.Dijadwalkan,
                StatusKegiatan.Berlangsung,
                StatusKegiatan.Selesai
            ),
            statusYangDiterima
        )
        assertEquals(listOf(1, 1, 1), idYangDiterima)
    }
}
