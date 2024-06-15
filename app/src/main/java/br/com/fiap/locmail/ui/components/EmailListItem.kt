package br.com.fiap.locmail.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.ContentPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import br.com.fiap.locmail.R
import br.com.fiap.locmail.ui.theme.Zinc700

@Composable
fun EmailListItem(
    sender: String,
    subject: String,
    contentPreview: String,
    time: String,
    userIconId: Int
) {
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(12.dp, 0.dp, 12.dp, 0.dp),
        colors = ButtonDefaults.buttonColors(Color.Transparent)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = userIconId), // Substitua com de usuário
                contentDescription = "Sender Picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .size(40.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Column {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        /* =================== Text Sender =================== */
                        Text(
                            sender,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            color = Zinc700

                        )
                        /* =================== Text Time =================== */
                        Text(
                            time,
                            fontSize = 13.sp,
                            color = Zinc700
                        )
                    }
                    Text(
                        subject,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W500,
                        color = Zinc700
                    )
                    Text(
                        contentPreview,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W400,
                        color = Zinc700,
                        maxLines = 2,
                        minLines = 1
                    )
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(4.dp))
}