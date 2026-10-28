package com.example.presensikegiatanhimpunan.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

fun pindahTab(nav: NavController, rute: Any) {
    nav.navigate(rute) {
        popUpTo(nav.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
