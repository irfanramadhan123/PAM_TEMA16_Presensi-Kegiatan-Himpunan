package com.example.presensikegiatanhimpunan.ui.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.presensikegiatanhimpunan.data.SumberKegiatanPalsu
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FormKegiatanViewModel(private val sumber: SumberKegiatanPalsu) : ViewModel() {

    private val _uiState = MutableStateFlow(FormKegiatanUiState())
    val uiState: StateFlow<FormKegiatanUiState> = _uiState.asStateFlow()

    fun ubahNama(teks: String) =
        _uiState.update { it.copy(nama = teks.take(60), pesan = null) }

    fun ubahDeskripsi(teks: String) =
        _uiState.update { it.copy(deskripsi = teks.take(200), pesan = null) }

    fun ubahLokasi(teks: String) =
        _uiState.update { it.copy(lokasi = teks.take(60), pesan = null) }

    fun ubahTanggal(teks: String) =
        _uiState.update { it.copy(tanggal = teks.take(10), pesan = null) }

    fun simpan() {
        val s = _uiState.value
        if (!s.bisaSimpan) return
        _uiState.update { it.copy(sedangMenyimpan = true) }
        viewModelScope.launch {
            sumber.tambah(
                nama = s.nama.trim(),
                deskripsi = s.deskripsi.trim().ifBlank { null },
                lokasi = s.lokasi.trim(),
                tanggal = s.tanggal.trim()
            )
            _uiState.value = FormKegiatanUiState(pesan = "Kegiatan tersimpan")
        }
    }
}
