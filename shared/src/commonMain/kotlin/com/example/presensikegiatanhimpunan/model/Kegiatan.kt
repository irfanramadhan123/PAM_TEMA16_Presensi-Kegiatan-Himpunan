package com.example.presensikegiatanhimpunan.model

data class Kegiatan(
    val id: Int,
    val nama: String,
    val deskripsi: String?,
    val lokasi: String,
    val tanggal: String,
    val status: StatusKegiatan = StatusKegiatan.Dijadwalkan
)
