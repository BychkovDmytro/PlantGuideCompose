package com.bychkovdmytro.plantguidecompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bychkovdmytro.plantguidecompose.ui.components.InfoScreen
import com.bychkovdmytro.plantguidecompose.ui.components.MainScreen
import com.bychkovdmytro.plantguidecompose.ui.theme.PlantGuideComposeTheme
import com.bychkovdmytro.plantguidecompose.utils.ListItem
import com.bychkovdmytro.plantguidecompose.utils.Routes

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            var item: ListItem? = null

            PlantGuideComposeTheme {
                NavHost(navController = navController, startDestination = Routes.MAIN_SCREEN) {
                    composable(Routes.MAIN_SCREEN) {
                        MainScreen(context = this@MainActivity) { listItem ->
                            item = listItem
                            navController.navigate(Routes.INFO_SCREEN)
                        }
                    }
                    composable(Routes.INFO_SCREEN) {
                        InfoScreen(item = item!!)
                    }
                }
            }
        }
    }
}
