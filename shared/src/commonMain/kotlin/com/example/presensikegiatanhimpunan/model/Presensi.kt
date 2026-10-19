package com.example.presensikegiatanhimpunan.model

data class Presensi(
    val id: Int,
    val kegiatanId: Int,
    val anggotaId: String,
    val waktuPresensi: String,
    val keterangan: String? = null
)
