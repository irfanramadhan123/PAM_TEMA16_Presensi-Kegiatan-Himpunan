package com.example.presensikegiatanhimpunan.model

sealed class StatusKegiatan {
    data object Dijadwalkan : StatusKegiatan()
    data object Berlangsung : StatusKegiatan()
    data object Selesai : StatusKegiatan()
}
