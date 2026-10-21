package com.example.presensikegiatanhimpunan

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.example.presensikegiatanhimpunan.data.SumberKegiatanPalsu
import com.example.presensikegiatanhimpunan.ui.LayarUtama

@Composable
@Preview
fun App() {
    MaterialTheme {
        val kegiatan = remember { SumberKegiatanPalsu().semua() }
        LayarUtama(kegiatan = kegiatan)
    }
}
