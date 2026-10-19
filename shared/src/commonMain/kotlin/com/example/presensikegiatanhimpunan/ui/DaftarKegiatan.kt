package com.example.presensikegiatanhimpunan.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.presensikegiatanhimpunan.model.Kegiatan

@Composable
fun DaftarKegiatan(
    kegiatan: List<Kegiatan>,
    onPilih: (Kegiatan) -> Unit = {}
) {
    val kegiatanTerurut = remember(kegiatan) {
        kegiatan.sortedByDescending { it.tanggal }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = kegiatanTerurut,
            key = { it.id }
        ) { item ->
            KartuKegiatan(
                kegiatan = item,
                onKlik = { onPilih(item) }
            )
        }
    }
}
