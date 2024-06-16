package br.com.fiap.locmail.ui.screens.login.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.locmail.R
import br.com.fiap.locmail.ui.theme.RoyalBlue
import br.com.fiap.locmail.ui.theme.Zinc50
import br.com.fiap.locmail.ui.theme.Zinc700
import br.com.fiap.locmail.ui.theme.Zinc900
import br.com.fiap.locmail.ui.components.buttons.CustomButton
import br.com.fiap.locmail.ui.components.TextInput

@Composable
fun NewAccountScreen() {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .padding(0.dp, 104.dp, 0.dp, 0.dp)
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
                        text = "Crie uma Conta!",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium,
                        color = Zinc50,
                        onTextLayout = { }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Ficamos felizes por nos escolher!",
                        fontSize = 16.sp,
                        color = Zinc50,
                        onTextLayout = { }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

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
                                modifier = Modifier
                                    .fillMaxWidth(),
                                value = username,
                                onValueChange = { username = it },
                                label = "Seu nome de usuário",
                                leadingIconResId = R.drawable.at_sign,
                                leadingIconTint = Zinc50
                            )

                            TextInput(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                value = password,
                                onValueChange = { password = it },
                                label = "Seu melhor email",
                                leadingIconResId = R.drawable.mails,
                                leadingIconTint = Zinc50
                            )

                            TextInput(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                value = password,
                                onValueChange = { password = it },
                                label = "Sua melhor senha",
                                leadingIconResId = R.drawable.key_round,
                                leadingIconTint = Zinc50
                            )
                        }
                        
                        Spacer(modifier = Modifier.height(4.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                /* =================== Check Box =================== */
                                Checkbox(
                                    checked = rememberMe,
                                    onCheckedChange = { rememberMe = it },
                                    modifier = Modifier
                                        .padding(0.dp)
                                        .size(20.dp, 20.dp),
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = RoyalBlue,
                                        uncheckedColor = Zinc700
                                    )
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                Text(
                                    text = "Lembre de mim!",
                                    color = Zinc50,
                                    fontSize = 14.sp,
                                    onTextLayout = { }
                                )
                            }

                            /* =================== Phrase Forgot =================== */
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

                    Spacer(modifier = Modifier.height(28.dp))

                    /* =================== Button Register =================== */
                    CustomButton(
                        text = "Registrar",
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
                            text = "Já possui uma conta?",
                            color = Zinc50,
                            fontSize = 14.sp,
                            onTextLayout = {}
                        )
                        Text (
                            text = " "
                        )
                        ClickableText(
                            text = AnnotatedString("Faça seu login"),
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