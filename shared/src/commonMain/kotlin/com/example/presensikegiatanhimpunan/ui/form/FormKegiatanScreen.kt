package com.example.presensikegiatanhimpunan.ui.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormKegiatanScreen(
    vm: FormKegiatanViewModel,
    onKembali: () -> Unit
) {
    val state by vm.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tambah Kegiatan") },
                navigationIcon = {
                    IconButton(onClick = onKembali) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Kembali"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            KolomTeks("Nama", state.nama, vm::ubahNama)
            KolomTeks("Deskripsi", state.deskripsi, vm::ubahDeskripsi, singleLine = false)
            KolomTeks("Lokasi", state.lokasi, vm::ubahLokasi)
            KolomTeks("Tanggal (YYYY-MM-DD)", state.tanggal, vm::ubahTanggal)

            Text(
                text = "Pratinjau: ${state.nama} di ${state.lokasi}, ${state.tanggal}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Button(
                onClick = vm::simpan,
                enabled = state.bisaSimpan,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (state.sedangMenyimpan) "Menyimpan…" else "Simpan")
            }

            state.pesan?.let { Text(it) }
        }
    }
}
