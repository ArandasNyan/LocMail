// ui/screens/LoginScreen.kt
package br.com.fiap.locmail.ui.screens.login.forgot

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.locmail.R
import br.com.fiap.locmail.ui.theme.*
import br.com.fiap.locmail.ui.components.buttons.CustomButton
import br.com.fiap.locmail.ui.components.TextInput

@Composable
fun ForgotPasswordScreen() {
    var useremail by remember { mutableStateOf("") }

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
            /* =================== Logo Image =================== */
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
                /* =================== Greetings =================== */
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp, 36.dp, 12.dp, 0.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Eita! Esqueceu sua senha?",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium,
                        color = Zinc50,
                        onTextLayout = { }
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Não se preocupe, deixaremos isso fácil por você!",
                        fontSize = 16.sp,
                        color = Zinc100,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.width(260.dp),
                        onTextLayout = { }
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Enviaremos um código no seu email favorito!",
                        fontSize = 14.sp,
                        color = Zinc400,
                        onTextLayout = { }
                    )
                }


                Spacer(modifier = Modifier.height(8.dp))

                /* =================== Form =================== */
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(24.dp, 0.dp)
                ) {
                    // Inputs fields, remember me and forgot pass
                    Column(
                        modifier = Modifier.padding(0.dp, 12.dp)
                    ) {
                        Column() {
                            TextInput(
                                modifier = Modifier.fillMaxWidth(),
                                value = useremail,
                                onValueChange = { useremail = it },
                                label = "Seu email favorito",
                                leadingIconResId = R.drawable.mails,
                                leadingIconTint = Zinc50
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    /* =================== Button Send =================== */
                    CustomButton(
                        text = "Enviar",
                        onClick = { /* Handle login */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(8.dp),
                        buttonColor = RoyalBlue,
                        textColor = Zinc50,
                        fontSize = 20
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    /* =================== Phrase text =================== */
                    Row {
                        Text(
                            text = "Não possui uma conta?",
                            color = Zinc50,
                            fontSize = 14.sp,
                            onTextLayout = {}
                        )
                        Text (
                            text = " "
                        )
                        ClickableText(
                            text = AnnotatedString("Regriste uma"),
                            onClick = { /* Handle login */ },
                            style = TextStyle(
                                color = RoyalBlue,
                                fontSize = 14.sp
                            )
                        )
                    }
                }
            }
        }
    }
}