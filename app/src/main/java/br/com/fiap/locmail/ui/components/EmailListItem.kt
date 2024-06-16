// ui/components/EmailListItem.kt
package br.com.fiap.locmail.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.locmail.ui.screens.home.Email
import br.com.fiap.locmail.ui.theme.Zinc500
import br.com.fiap.locmail.ui.theme.Zinc700

@Composable
fun EmailListItem(
    email: Email,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Transparent, RoundedCornerShape(8.dp))
            .padding(vertical = 0.dp, horizontal = 12.dp)
    ) {
        Image(
            painter = painterResource(id = email.iconId),
            contentDescription = "Sender Picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .size(40.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                /* =================== Text Sender =================== */
                Text(
                    text = email.sender,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Zinc700
                )
                /* =================== Text Time =================== */
                Text(
                    text = email.time,
                    fontSize = 12.sp,
                    color = Zinc500
                )
            }
            Text(
                text = email.subject,
                fontSize = 14.sp,
                fontWeight = FontWeight.W500,
                color = Zinc700
            )
            Text(
                text = email.contentPreview,
                fontSize = 12.sp,
                fontWeight = FontWeight.W400,
                color = Zinc500,
                maxLines = 2
            )
        }
    }
}
