// ui/components/DrawerContent.kt
package br.com.fiap.locmail.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import br.com.fiap.locmail.R

@Composable
fun DrawerContent(
    onCloseDrawer: () -> Unit,
    modifier: Modifier = Modifier
) {
    // State to control scroll
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxHeight()
            .widthIn(min = 180.dp, max = 260.dp)
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp, 16.dp, 16.dp, 48.dp)
            .verticalScroll(scrollState)
    ) {
        Column {
            // Header with app name
            Text(
                text = "Locmail",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        Column {
            // Menu sections
            Text(
                text = "Todos os Marcadores",
                style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray),
                modifier = Modifier.padding(vertical = 8.dp)
            )
            DrawerMenuItem(
                iconResId = R.drawable.bookmark,
                title = "Importante",
                onClick = onCloseDrawer
            )
            DrawerMenuItem(
                iconResId = R.drawable.clock,
                title = "Adiados",
                onClick = onCloseDrawer
            )
            DrawerMenuItem(
                iconResId = R.drawable.send,
                title = "Enviados",
                onClick = onCloseDrawer
            )
            DrawerMenuItem(
                iconResId = R.drawable.clock_rotate_right,
                title = "Programado",
                onClick = onCloseDrawer
            )
            DrawerMenuItem(
                iconResId = R.drawable.box,
                title = "Caixa de saída",
                onClick = onCloseDrawer
            )
            DrawerMenuItem(
                iconResId = R.drawable.page_edit,
                title = "Rascunhos",
                onClick = onCloseDrawer
            )
            DrawerMenuItem(
                iconResId = R.drawable.shield_alert,
                title = "Spam",
                onClick = onCloseDrawer
            )
            DrawerMenuItem(
                iconResId = R.drawable.trash,
                title = "Lixeira",
                onClick = onCloseDrawer
            )
            DrawerMenuItem(
                iconResId = R.drawable.box,
                title = "Caixa de saída",
                onClick = onCloseDrawer
            )
            DrawerMenuItem(
                iconResId = R.drawable.page_edit,
                title = "Rascunhos",
                onClick = onCloseDrawer
            )
            DrawerMenuItem(
                iconResId = R.drawable.shield_alert,
                title = "Spam",
                onClick = onCloseDrawer
            )
            DrawerMenuItem(
                iconResId = R.drawable.trash,
                title = "Lixeira",
                onClick = onCloseDrawer
            )
            DrawerMenuItem(
                iconResId = R.drawable.box,
                title = "Caixa de saída",
                onClick = onCloseDrawer
            )
            DrawerMenuItem(
                iconResId = R.drawable.page_edit,
                title = "Rascunhos",
                onClick = onCloseDrawer
            )
            DrawerMenuItem(
                iconResId = R.drawable.shield_alert,
                title = "Spam",
                onClick = onCloseDrawer
            )
            DrawerMenuItem(
                iconResId = R.drawable.trash,
                title = "Lixeira",
                onClick = onCloseDrawer
            )
            DrawerMenuItem(
                iconResId = R.drawable.box,
                title = "Caixa de saída",
                onClick = onCloseDrawer
            )
            DrawerMenuItem(
                iconResId = R.drawable.page_edit,
                title = "Rascunhos",
                onClick = onCloseDrawer
            )
            DrawerMenuItem(
                iconResId = R.drawable.shield_alert,
                title = "Spam",
                onClick = onCloseDrawer
            )
            DrawerMenuItem(
                iconResId = R.drawable.trash,
                title = "Lixeira",
                onClick = onCloseDrawer
            )

            // Apps de Integração
            Text(
                text = "Apps de Integração",
                style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray),
                modifier = Modifier.padding(vertical = 8.dp)
            )
            DrawerMenuItem(
                iconResId = R.drawable.calendar_range,
                title = "Google Agenda",
                onClick = onCloseDrawer
            )
            DrawerMenuItem(
                iconResId = R.drawable.square_user_round,
                title = "Google Contato",
                onClick = onCloseDrawer
            )
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

        Column {
            // Bottom section
            DrawerMenuItem(
                iconResId = R.drawable.settings,
                title = "Configurações",
                onClick = onCloseDrawer
            )
            DrawerMenuItem(
                iconResId = R.drawable.circle_help,
                title = "Ajuda e feedback",
                onClick = onCloseDrawer
            )
        }
    }
}

@Composable
fun DrawerMenuItem(
    iconResId: Int,
    title: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = title,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(title, style = MaterialTheme.typography.bodyMedium)
    }
}
