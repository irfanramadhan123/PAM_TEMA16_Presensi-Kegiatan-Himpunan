package com.example.presensikegiatanhimpunan

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.presensikegiatanhimpunan.data.SumberKegiatanPalsu
import com.example.presensikegiatanhimpunan.nav.RuteAsisten
import com.example.presensikegiatanhimpunan.nav.RuteBeranda
import com.example.presensikegiatanhimpunan.nav.RuteDetail
import com.example.presensikegiatanhimpunan.nav.RuteForm
import com.example.presensikegiatanhimpunan.nav.RuteProfil
import com.example.presensikegiatanhimpunan.nav.TAB
import com.example.presensikegiatanhimpunan.nav.pindahTab
import com.example.presensikegiatanhimpunan.ui.LayarUtama
import com.example.presensikegiatanhimpunan.ui.asisten.LayarAsisten
import com.example.presensikegiatanhimpunan.ui.detail.DetailKegiatanViewModel
import com.example.presensikegiatanhimpunan.ui.detail.LayarDetailKegiatan
import com.example.presensikegiatanhimpunan.ui.form.FormKegiatanScreen
import com.example.presensikegiatanhimpunan.ui.form.FormKegiatanViewModel
import com.example.presensikegiatanhimpunan.ui.profil.LayarProfil

@Composable
@Preview
fun App() {
    MaterialTheme {
        val sumber = remember { SumberKegiatanPalsu() }
        val nav = rememberNavController()
        val entry by nav.currentBackStackEntryAsState()

        Scaffold(
            bottomBar = {
                NavigationBar {
                    TAB.forEach { tab ->
                        NavigationBarItem(
                            selected = entry?.destination?.hasRoute(tab.rute::class) == true,
                            onClick = { pindahTab(nav, tab.rute) },
                            icon = { Icon(tab.ikon, contentDescription = null) },
                            label = { Text(tab.label) }
                        )
                    }
                }
            }
        ) { padding ->
            NavHost(nav, startDestination = RuteBeranda, modifier = Modifier.padding(padding)) {
                composable<RuteBeranda> {
                    val kegiatan by sumber.daftar.collectAsState()
                    LayarUtama(
                        kegiatan = kegiatan,
                        onTambah = { nav.navigate(RuteForm) },
                        onPilih = { nav.navigate(RuteDetail(it.id)) }
                    )
                }
                composable<RuteAsisten> { LayarAsisten() }
                composable<RuteProfil> { LayarProfil() }
                composable<RuteDetail> { backStackEntry ->
                    val id = backStackEntry.toRoute<RuteDetail>().id
                    val vm = viewModel { DetailKegiatanViewModel(id, sumber) }
                    val kegiatan by vm.kegiatan.collectAsState()
                    LayarDetailKegiatan(
                        kegiatan = kegiatan,
                        kembali = { nav.popBackStack() },
                        ubah = { nav.navigate(RuteForm) }
                    )
                }
                composable<RuteForm> {
                    FormKegiatanScreen(
                        vm = viewModel { FormKegiatanViewModel(sumber) },
                        onKembali = { nav.popBackStack() }
                    )
                }
            }
        }
    }
}
