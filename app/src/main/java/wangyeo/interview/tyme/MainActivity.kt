package wangyeo.interview.tyme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import wangyeo.interview.tyme.ui.modules.launching.SplashScreen
import wangyeo.interview.tyme.ui.theme.AppComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppComposeTheme {
                val navController = rememberNavController()

                Scaffold() {
                    NavHost(navController, startDestination = "splash") {
                        composable(route = "splash") {
                            SplashScreen()
                        }
                    }
                }
            }
        }
    }
}