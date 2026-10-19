# Tema 16. Presensi Kegiatan Himpunan

## Identitas Kelompok

| No. | Nama Anggota                | NIM       | Jabatan |
| --- | --------------------------- | --------- | ------- |
| 1   | Muhammad Irfan Ramadhan     | 124140159 | Ketua   |
| 2   | Muhammad Arfa Raditya       | 124140015 | Anggota |
| 3   | Jeremi Pison Efrat Sianturi | 124140195 | Anggota |

## Riwayat Perubahan Minggu Ini (Modul 2)

| Nama | File | Perubahan |
| --- | --- | --- |
| Muhammad Arfa Raditya | `model/Kegiatan.kt`, `Presensi.kt`, `StatusKegiatan.kt` | Membuat model data kegiatan, presensi, dan status. |
| Muhammad Irfan Ramadhan | `data/Sumber.kt`, `SumberKegiatanPalsu.kt` | Membuat kontrak `Sumber<T>` dan sumber data kegiatan palsu. |
| Jeremi Pison Efrat Sianturi | `gradle/libs.versions.toml`, `shared/build.gradle.kts`, `SumberKegiatanPalsuTest.kt`, `README.md` | Menambah dependensi Coroutines, unit test `ambil()` dan `pantau()`, serta dokumentasi. |

## Ringkasan Modul 2

- `Kegiatan` menyimpan `id`, `nama`, `deskripsi`, `lokasi`, `tanggal`, dan `status`.
- `StatusKegiatan` memiliki tiga status: `Dijadwalkan`, `Berlangsung`, `Selesai`.
- `Sumber<T>` menyediakan `suspend fun ambil(id)` untuk mengambil satu data dan `fun pantau(id): Flow<T>` untuk memantau perubahan data.
- `SumberKegiatanPalsu` menggunakan `delay()` untuk mensimulasikan proses asynchronous.
- `pantau()` mengirim status berurutan melalui `emit()`: `Dijadwalkan` -> `Berlangsung` -> `Selesai`.
- Pengujian menggunakan `runTest`, `kotlin.test`, dan virtual time sehingga tidak menunggu `delay()` secara nyata.
