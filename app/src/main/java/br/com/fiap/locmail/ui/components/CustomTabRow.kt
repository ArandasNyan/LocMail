// ui/components/CustomTabRow.kt
package br.com.fiap.locmail.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import br.com.fiap.locmail.ui.theme.RoyalBlue
import br.com.fiap.locmail.ui.theme.Zinc200
import br.com.fiap.locmail.ui.theme.Zinc50
import br.com.fiap.locmail.ui.theme.Zinc700
import kotlinx.coroutines.launch

@Composable
fun CustomTabRow(
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    tabTitles: List<String>
) {
    val coroutineScope = rememberCoroutineScope()
    val tabWidth = 96.dp
    val tabHeight = 48.dp

    // Animated properties for the slider
    val indicatorWidth by animateDpAsState(
        targetValue = tabWidth,
        animationSpec = tween(durationMillis = 300), label = ""
    )
    val indicatorOffset by animateDpAsState(
        targetValue = selectedTabIndex * tabWidth + 0.5.dp,
        animationSpec = tween(durationMillis = 300), label = ""
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        // Tab Row
        Box(
            modifier = Modifier
                .height(tabHeight)
                .background(Zinc200, shape = RoundedCornerShape(8.dp))
                .padding(vertical = 4.dp, horizontal = 4.dp)
        ) {
            // Slider
            Box(
                modifier = Modifier
                    .offset(x = indicatorOffset)
                    .width(indicatorWidth)
                    .height(tabHeight)
                    .background(Zinc50, shape = RoundedCornerShape(4.dp))
            )

            // Tabs
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                tabTitles.forEachIndexed { index, title ->
                    Box(
                        modifier = Modifier
                            .width(tabWidth)
                            .height(tabHeight)
                            .padding(2.dp)
                            .pointerInput(Unit) { // Intercept touch events
                                detectTapGestures(
                                    onTap = {
                                        coroutineScope.launch { onTabSelected(index) }
                                    }
                                )
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = title,
                            color = if (selectedTabIndex == index) RoyalBlue else Zinc700,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = if(selectedTabIndex == index) FontWeight.W500 else FontWeight.W400
                        )
                    }
                }
            }
        }
    }
}
