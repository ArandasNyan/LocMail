package br.com.fiap.locmail.ui.components

import android.graphics.Color
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import br.com.fiap.locmail.ui.theme.*
import kotlinx.coroutines.launch

@Composable
fun CustomTabRow(
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    tabTitles: List<String>
) {
    val coroutineScope = rememberCoroutineScope()

    TabRow(selectedTabIndex = selectedTabIndex) {
        tabTitles.forEachIndexed { index, title ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = {
                    coroutineScope.launch { onTabSelected(index) }
                },
                text = { Text(title, color = if (selectedTabIndex == index) RoyalBlue else Zinc700) }
            )
        }
    }
}