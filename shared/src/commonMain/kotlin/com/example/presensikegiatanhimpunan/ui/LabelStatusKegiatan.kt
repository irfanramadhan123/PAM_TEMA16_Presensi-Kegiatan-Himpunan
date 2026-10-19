package com.example.presensikegiatanhimpunan.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.presensikegiatanhimpunan.model.StatusKegiatan

private data class WarnaLabel(
    val latar: Color,
    val teks: Color
)

@Composable
fun LabelStatusKegiatan(status: StatusKegiatan) {
    val gelap = isSystemInDarkTheme()
    val (label, warna) = when (status) {
        StatusKegiatan.Dijadwalkan -> "Dijadwalkan" to if (gelap) {
            WarnaLabel(latar = Color(0xFF17324D), teks = Color(0xFFBBDEFB))
        } else {
            WarnaLabel(latar = Color(0xFFE3F2FD), teks = Color(0xFF0D47A1))
        }

        StatusKegiatan.Berlangsung -> "Berlangsung" to if (gelap) {
            WarnaLabel(latar = Color(0xFF4A2A00), teks = Color(0xFFFFCC80))
        } else {
            WarnaLabel(latar = Color(0xFFFFF3E0), teks = Color(0xFFE65100))
        }

        StatusKegiatan.Selesai -> "Selesai" to if (gelap) {
            WarnaLabel(latar = Color(0xFF14351B), teks = Color(0xFFA5D6A7))
        } else {
            WarnaLabel(latar = Color(0xFFE8F5E9), teks = Color(0xFF1B5E20))
        }
    }

    Surface(
        color = warna.latar,
        contentColor = warna.teks,
        shape = MaterialTheme.shapes.small
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        )
    }
}
