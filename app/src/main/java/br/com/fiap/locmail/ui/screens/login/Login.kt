// LoginScreen.kt
package br.com.fiap.locmail.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.locmail.R
import br.com.fiap.locmail.ui.theme.RoyalBlue
import br.com.fiap.locmail.ui.theme.Zinc50
import br.com.fiap.locmail.ui.theme.Zinc700
import br.com.fiap.locmail.ui.theme.Zinc900
import br.com.fiap.locmail.ui.components.buttons.CustomButton
import br.com.fiap.locmail.ui.components.TextInput

@Composable
fun LoginScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .padding(0.dp, 88.dp, 0.dp, 0.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.size(128.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))

            Card(
                shape = RoundedCornerShape(24.dp, 24.dp, 0.dp, 0.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .offset(y = 0.dp),
                colors = CardDefaults.cardColors(Zinc900)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp, 36.dp, 12.dp, 0.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Bem-Vindo!",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium,
                        color = Zinc50
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Que bom ver você novamente!",
                        fontSize = 16.sp,
                        color = Zinc50
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(24.dp, 0.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(0.dp, 12.dp)
                    ) {
                        TextInput(
                            modifier = Modifier.fillMaxWidth(),
                            value = username,
                            onValueChange = { username = it },
                            label = "Seu usuário",
                            leadingIconResId = R.drawable.at_sign,
                            leadingIconTint = Zinc50
                        )

                        TextInput(
                            modifier = Modifier.fillMaxWidth(),
                            value = password,
                            onValueChange = { password = it },
                            label = "Sua senha",
                            leadingIconResId = R.drawable.key_round,
                            leadingIconTint = Zinc50
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    checked = rememberMe,
                                    onCheckedChange = { rememberMe = it },
                                    modifier = Modifier
                                        .padding(0.dp)
                                        .size(20.dp, 20.dp),
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = RoyalBlue,
                                        uncheckedColor = Zinc700)
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                Text(
                                    text = "Lembre de mim!",
                                    color = Zinc50,
                                    fontSize = 14.sp
                                )
                            }

                            ClickableText(
                                text = AnnotatedString("Esqueceu a senha?"),
                                onClick = { /* Handle click */ },
                                style = TextStyle(
                                    color = RoyalBlue,
                                    fontSize = 14.sp
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    CustomButton(
                        text = "Entrar",
                        onClick = {
                            if (validateLogin(username, password)) {
                                navController.navigate("home")
                            } else {
                                errorMessage = "Usuário ou senha inválidos"
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(8.dp),
                        buttonColor = RoyalBlue,
                        textColor = Zinc50,
                        fontSize = 20
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = errorMessage,
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 14.sp
                    )

                    Row {
                        Text(
                            text = "Não possui uma conta?",
                            color = Zinc50,
                            fontSize = 14.sp
                        )
                        Text(
                            text = " "
                        )
                        ClickableText(
                            text = AnnotatedString("Crie uma"),
                            style = TextStyle(
                                color = RoyalBlue,
                                fontSize = 14.sp
                            ),
                            onClick = {
                                // Navegar para a tela de criação de conta
                            }
                        )
                    }
                }
            }
        }
    }
}

fun validateLogin(username: String, password: String): Boolean {
    // Validação simples de exemplo
    return username == "admin" && password == "1234"
}
