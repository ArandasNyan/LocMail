package br.com.fiap.locmail.ui.components.buttons

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.locmail.ui.theme.RoyalBlue
import br.com.fiap.locmail.ui.theme.Zinc50

@Composable
fun SimpleFab(isFabExpanded: Boolean) {
    val fabHeight by animateDpAsState(
        targetValue = if (isFabExpanded) 56.dp else 56.dp,
        label = ""
    )
    val fabWidth by animateDpAsState(
        targetValue = if (isFabExpanded) 140.dp else 56.dp,
        label = ""
    )
    val fabElevation by animateDpAsState(
        targetValue = if (isFabExpanded) 8.dp else 16.dp,
        label = ""
    )

    FloatingActionButton(
        onClick = { /* Ação para o botão */ },
        containerColor = RoyalBlue,
        contentColor = Zinc50,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .offset(y = (-42).dp)
            .height(fabHeight)
            .width(fabWidth)
    ) {
        if (isFabExpanded) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Escrever",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Escrever",
                    color = Zinc50,
                    fontSize = 18.sp,
                )
            }
        } else {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Escrever",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}