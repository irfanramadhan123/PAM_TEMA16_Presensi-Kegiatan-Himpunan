package com.example.presensikegiatanhimpunan.ui.form

data class FormKegiatanUiState(
    val nama: String = "",
    val deskripsi: String = "",
    val lokasi: String = "",
    val tanggal: String = "",
    val sedangMenyimpan: Boolean = false,
    val pesan: String? = null
) {
    val bisaSimpan: Boolean
        get() = nama.isNotBlank() &&
            lokasi.isNotBlank() &&
            tanggal.isNotBlank() &&
            !sedangMenyimpan
}
