package com.example.presensikegiatanhimpunan.ui.detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.presensikegiatanhimpunan.model.Kegiatan

// STUB — diisi Teman 1 (Orang B)
@Composable
fun LayarDetailKegiatan(kegiatan: Kegiatan?, kembali: () -> Unit, ubah: () -> Unit) {
    Text(kegiatan?.nama ?: "Memuat…")
}
