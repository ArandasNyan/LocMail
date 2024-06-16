// ui/components/modals/SearchResultsModal.kt
package br.com.fiap.locmail.ui.components.modals

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import br.com.fiap.locmail.ui.theme.Zinc50
import br.com.fiap.locmail.ui.theme.Zinc700

@Composable
fun SearchResultsModal(
    query: TextFieldValue,
    onDismissRequest: () -> Unit,
    onSearch: (TextFieldValue) -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent, shape = RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Zinc50, shape = RoundedCornerShape(12.dp))
                    .padding(16.dp)
            ) {
                // Placeholder for content
                Text(
                    "Em breve!",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 24.sp,
                    color = Zinc700
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "Eita! Não se preocupe, ainda não temos nada aqui, mas nossa equipe está a todo vapor para dar facilidade para as suas busca por emails!",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Zinc700
                )
            }
        }
    }
}
