// ui/components/SearchResultsModal.kt
package br.com.fiap.locmail.ui.components.modals

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import br.com.fiap.locmail.R

@Composable
fun SearchResultsModal(
    query: TextFieldValue,
    onDismissRequest: () -> Unit,
    onSearch: (TextFieldValue) -> Unit,
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                // Barra de pesquisa no topo
                SearchBar(query = query, onSearch = onSearch, onDismissRequest = onDismissRequest)

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Melhores resultados",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = "Pelo que seria sua busca?",
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                SearchFilters()

                Spacer(modifier = Modifier.height(16.dp))

                // Resultados de pesquisa
                SearchResultsSection()
            }
        }
    }
}

@Composable
fun SearchBar(
    query: TextFieldValue,
    onSearch: (TextFieldValue) -> Unit,
    onDismissRequest: () -> Unit
) {
    var searchQuery by remember { mutableStateOf(query) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(8.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = { onSearch(searchQuery) }
            ),
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
        )
        IconButton(onClick = { searchQuery = TextFieldValue("") }) {
            Icon(
                painter = painterResource(id = R.drawable.x),
                contentDescription = "Clear Search"
            )
        }
        IconButton(onClick = onDismissRequest) {
            Icon(
                painter = painterResource(id = R.drawable.x),
                contentDescription = "Close"
            )
        }
    }
}

@Composable
fun SearchFilters() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FilterChip(text = "Pessoas", isSelected = true, onClick = { /* Handle click */ })
        FilterChip(text = "Jobs", isSelected = false, onClick = { /* Handle click */ })
        FilterChip(text = "Tags", isSelected = false, onClick = { /* Handle click */ })
    }
}

@Composable
fun FilterChip(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Surface(
        shape = MaterialTheme.shapes.small,
        color = if (isSelected) MaterialTheme.colorScheme.primary else Color.LightGray,
        modifier = Modifier
            .padding(4.dp)
            .clickable(onClick = onClick)
    ) {
        Text(
            text = text,
            color = if (isSelected) Color.White else Color.Black,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}

@Composable
fun SearchResultsSection() {
    Column {
        SectionHeader("Pessoas")
        ResultItem(
            iconResId = R.drawable.cat,
            title = "Emmanoel Arandas",
            subtitle = "emmanoelkratos2@gmail.com"
        )
        ResultItem(
            iconResId = R.drawable.laurafaustino,
            title = "Laura Faustino",
            subtitle = "laurafaustino19@gmail.com"
        )

        SectionHeader("Jobs")
        ResultItem(
            iconResId = R.drawable.laurafaustino,
            title = "Engenharia de Design",
            subtitle = "Clima Tempo • Remota"
        )
        ResultItem(
            iconResId = R.drawable.danilo,
            title = "Back-end Developer",
            subtitle = "Spotify • Alpha Ville"
        )

        SectionHeader("Tags")
        ResultItem(
            iconResId = R.drawable.cat,
            title = "Back-end Developer",
            subtitle = "Spotify • Alpha Ville"
        )
    }
}

@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Composable
fun ResultItem(iconResId: Int, title: String, subtitle: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(title, style = MaterialTheme.typography.bodyMedium)
            Text(subtitle, style = MaterialTheme.typography.bodySmall)
        }
    }
}
