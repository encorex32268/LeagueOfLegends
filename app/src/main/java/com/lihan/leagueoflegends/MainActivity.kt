package com.lihan.leagueoflegends

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lihan.leagueoflegends.feature.champion.presentation.ChampionsScreenRoot
import com.lihan.leagueoflegends.core.domain.navigation.Routes
import com.lihan.leagueoflegends.feature.champion.presentation.detail.ChampionDetailScreenRoot
import com.lihan.leagueoflegends.ui.theme.LeagueOfLegendsTheme
import org.koin.compose.KoinContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KoinContext {
                LeagueOfLegendsTheme {
                    val navController = rememberNavController()
                    NavHost(
                        modifier = Modifier
                            .fillMaxSize(),
                        navController = navController,
                        startDestination = Routes.Champions
                    ){
                        composable<Routes.Champions>{
                            ChampionsScreenRoot(
                                onChampionClickGoToDetail = {
                                    navController.navigate(
                                        Routes.ChampionDetail(it)
                                    )
                                }
                            )
                        }
                        composable<Routes.ChampionDetail>{
                            ChampionDetailScreenRoot(
                                onBack = {
                                    navController.navigateUp()
                                }
                            )
                        }

                    }

                }
            }
        }
    }
}