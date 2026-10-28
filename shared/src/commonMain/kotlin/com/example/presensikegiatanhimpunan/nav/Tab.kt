package com.example.presensikegiatanhimpunan.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

data class Tab(val label: String, val ikon: ImageVector, val rute: Any)

val TAB = listOf(
    Tab("Beranda", Icons.Default.Home, RuteBeranda),
    Tab("Asisten", Icons.Default.Star, RuteAsisten),
    Tab("Profil", Icons.Default.Person, RuteProfil)
)
