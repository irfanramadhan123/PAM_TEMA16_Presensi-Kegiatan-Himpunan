package com.example.presensikegiatanhimpunan.ui.detail

import androidx.lifecycle.ViewModel
import com.example.presensikegiatanhimpunan.data.SumberKegiatanPalsu
import com.example.presensikegiatanhimpunan.model.Kegiatan
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// STUB — diisi Teman 1 (Orang B)
class DetailKegiatanViewModel(id: Int, sumber: SumberKegiatanPalsu) : ViewModel() {
    val kegiatan: StateFlow<Kegiatan?> = MutableStateFlow(null)
}
