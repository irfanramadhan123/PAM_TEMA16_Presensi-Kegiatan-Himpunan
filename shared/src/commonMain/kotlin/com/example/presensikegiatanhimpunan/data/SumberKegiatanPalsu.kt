package com.example.presensikegiatanhimpunan.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.example.presensikegiatanhimpunan.model.Kegiatan
import com.example.presensikegiatanhimpunan.model.StatusKegiatan

class SumberKegiatanPalsu : Sumber<Kegiatan> {

    private val dataKegiatan: Map<Int, Kegiatan> = listOf(
        Kegiatan(
            id = 1,
            nama = "Kader",
            deskripsi = "Kegiatan kaderisasi anggota himpunan.",
            lokasi = "Gedung F ITERA",
            tanggal = "2026-10-05"
        ),
        Kegiatan(
            id = 2,
            nama = "PTKOM",
            deskripsi = "Kegiatan PTKOM himpunan.",
            lokasi = "Gedung GK 1 ITERA",
            tanggal = "2026-10-12"
        ),
        Kegiatan(
            id = 3,
            nama = "Seminar INFEST",
            deskripsi = "Seminar untuk menambah wawasan dan pengetahuan mahasiswa.",
            lokasi = "Aula Kampus ITERA",
            tanggal = "2026-10-18"
        ),
        Kegiatan(
            id = 4,
            nama = "Lomba INFEST",
            deskripsi = "Perlombaan dalam rangka kegiatan INFEST.",
            lokasi = "Kampus ITERA",
            tanggal = "2026-10-24"
        ),
        Kegiatan(
            id = 5,
            nama = "Latihan Futsal Bersama",
            deskripsi = "Latihan futsal bersama anggota himpunan.",
            lokasi = "Lapangan Futsal Kampus ITERA",
            tanggal = "2026-10-30"
        )
    ).associateBy { it.id }

    private fun buatKegiatanDefault(id: Int): Kegiatan = Kegiatan(
        id = id,
        nama = "Kegiatan #$id",
        deskripsi = null,
        lokasi = "Lokasi Kegiatan #$id",
        tanggal = "2026-11-01"
    )

    private fun ambilBasis(id: Int): Kegiatan =
        dataKegiatan[id] ?: buatKegiatanDefault(id)

    override suspend fun ambil(id: Int): Kegiatan {
        delay(1000)
        return ambilBasis(id)
    }

    override fun pantau(id: Int): Flow<Kegiatan> = flow {
        val basis = ambilBasis(id)
        emit(basis)
        delay(1000)
        emit(basis.copy(status = StatusKegiatan.Berlangsung))
        delay(1000)
        emit(basis.copy(status = StatusKegiatan.Selesai))
    }
}
