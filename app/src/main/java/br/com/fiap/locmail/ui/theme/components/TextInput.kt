// ui/components/TextInput.kt
package br.com.fiap.locmail.ui.theme.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.fiap.locmail.ui.theme.Zinc700

@Composable
fun TextInput(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(8.dp),
        maxLines = 1,
        label = {
            Text(
                text = label,
                onTextLayout = {} // Especificando explicitamente o onTextLayout como null
            )
        },
        modifier = modifier
            .padding(vertical = 8.dp),
        colors = OutlinedTextFieldDefaults.colors(Zinc700)
    )
}
