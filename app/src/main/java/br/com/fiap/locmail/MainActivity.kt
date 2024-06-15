package br.com.fiap.locmail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import br.com.fiap.locmail.ui.theme.LocMailTheme
import br.com.fiap.locmail.ui.screens.home.HomeScreen
import br.com.fiap.locmail.ui.screens.login.LoginScreen
import br.com.fiap.locmail.ui.screens.login.forgot.ForgotPasswordScreen
import br.com.fiap.locmail.ui.screens.login.register.NewAccountScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LocMailTheme {
                Surface {
                    HomeScreen()
                }
            }
        }
    }
}

