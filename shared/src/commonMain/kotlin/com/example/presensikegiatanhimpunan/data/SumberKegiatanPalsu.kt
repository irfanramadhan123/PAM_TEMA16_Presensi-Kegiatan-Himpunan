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
        ),
        Kegiatan(
            id = 6,
            nama = "Open Recruitment Anggota Baru",
            deskripsi = "Pendaftaran dan seleksi anggota baru himpunan.",
            lokasi = "Gedung E ITERA",
            tanggal = "2026-09-12",
            status = StatusKegiatan.Selesai
        ),
        Kegiatan(
            id = 7,
            nama = "Pelatihan Kepemimpinan Dasar",
            deskripsi = "Pelatihan soft skill untuk anggota baru himpunan.",
            lokasi = "Ruang Seminar Gedung B",
            tanggal = "2026-09-20",
            status = StatusKegiatan.Selesai
        ),
        Kegiatan(
            id = 8,
            nama = "Kunjungan Industri",
            deskripsi = "Kunjungan ke perusahaan mitra untuk menambah wawasan.",
            lokasi = "Kawasan Industri Lampung",
            tanggal = "2026-10-16",
            status = StatusKegiatan.Berlangsung
        ),
        Kegiatan(
            id = 9,
            nama = "Malam Keakraban Anggota",
            deskripsi = "Ajang mempererat kekeluargaan antaranggota himpunan.",
            lokasi = "Villa Lembah Hijau",
            tanggal = "2026-11-07"
        ),
        Kegiatan(
            id = 10,
            nama = "Donor Darah Bersama PMI",
            deskripsi = "Kegiatan sosial donor darah bekerja sama dengan PMI.",
            lokasi = "Aula Kampus ITERA",
            tanggal = "2026-11-12"
        ),
        Kegiatan(
            id = 11,
            nama = "Festival Budaya Nusantara",
            deskripsi = "Pentas seni dan budaya dari seluruh anggota himpunan.",
            lokasi = "Lapangan Utama ITERA",
            tanggal = "2026-10-19",
            status = StatusKegiatan.Berlangsung
        ),
        Kegiatan(
            id = 12,
            nama = "Rapat Evaluasi Kepengurusan",
            deskripsi = "Evaluasi program kerja dan laporan pertanggungjawaban.",
            lokasi = "Ruang Rapat Himpunan",
            tanggal = "2026-09-30",
            status = StatusKegiatan.Selesai
        )
    ).associateBy { it.id }

    fun semua(): List<Kegiatan> = dataKegiatan.values.toList()

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
        emit(basis.copy(status = StatusKegiatan.Dijadwalkan))
        delay(1000)
        emit(basis.copy(status = StatusKegiatan.Berlangsung))
        delay(1000)
        emit(basis.copy(status = StatusKegiatan.Selesai))
    }
}
