package com.example.presensikegiatanhimpunan.ui.form

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun KolomTeks(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = singleLine,
        modifier = modifier.fillMaxWidth()
    )
}

@Preview
@Composable
fun KolomTeksPreview() {
    MaterialTheme {
        KolomTeks(
            label = "Nama",
            value = "Seminar INFEST",
            onValueChange = {}
        )
    }
}
