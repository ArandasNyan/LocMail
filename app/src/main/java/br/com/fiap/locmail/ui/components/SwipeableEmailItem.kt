// ui/components/SwipeableEmailItem.kt
package br.com.fiap.locmail.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.FractionalThreshold
import androidx.wear.compose.material.rememberSwipeableState
import androidx.wear.compose.material.swipeable
import br.com.fiap.locmail.R
import br.com.fiap.locmail.ui.screens.home.Email
import br.com.fiap.locmail.ui.theme.RoyalBlue
import br.com.fiap.locmail.ui.theme.Zinc50
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@OptIn(ExperimentalWearMaterialApi::class, ExperimentalWearMaterialApi::class)
@Composable
fun SwipeableEmailItem(
    email: Email,
    onArchive: (Email) -> Unit,
    onUnarchive: (Email) -> Unit,
    isArchiving: Boolean, // Flag para verificar se deve arquivar ou desarquivar
    selectedTabIndex: Int, // Recebe o índice da aba
    modifier: Modifier = Modifier
) {
    val swipeableState = rememberSwipeableState(initialValue = 0)

    val anchors = when (selectedTabIndex) {
        0 -> mapOf(0f to 0, 300f to 1) // Arquivar na aba "Todos"
        3 -> mapOf(0f to 0, -300f to 2) // Desarquivar na aba "Arquivado"
        else -> mapOf(0f to 0) // Desabilita o swipe em outras abas
    }
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.3f) },
                orientation = Orientation.Horizontal,
                enabled = isArchiving // Desabilitar o swipe se não estiver arquivando ou desarquivando
            )
    ) {
        // Background para indicar ação de arquivamento ou desarquivamento
        if (selectedTabIndex == 0) {
            // Background para arquivar (swipe para a direita)
            Box(
                modifier = Modifier
                    .height(72.dp)
                    .fillMaxWidth()
                    .background(RoyalBlue)
                    .align(Alignment.CenterStart)
                    .padding(start = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.archive_restore),
                        contentDescription = "Archive",
                        tint = Zinc50,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Arquivar", color = Zinc50)
                }
            }
        } else if (selectedTabIndex == 3) {
            // Background para desarquivar (swipe para a esquerda)
            Box(
                modifier = Modifier
                    .height(72.dp)
                    .fillMaxWidth()
                    .background(RoyalBlue)
                    .align(Alignment.CenterEnd)
                    .padding(end = 16.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text("Remover", color = Zinc50)
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.package_open), // Ícone de desarquivamento
                        contentDescription = "Unarchive",
                        tint = Zinc50,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }

        // Conteúdo do item de e-mail
        Box(
            modifier = Modifier
                .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
                .background(Zinc50)
                .clip(RoundedCornerShape(8.dp))
                .fillMaxWidth()
        ) {
            EmailListItem(
                email = email
            )
        }
    }

    // Executar ação quando swipado o suficiente
    LaunchedEffect(swipeableState.currentValue) {
        if (swipeableState.currentValue == 1) {
            coroutineScope.launch {
                swipeableState.snapTo(0)
                onArchive(email)
            }
        } else if (swipeableState.currentValue == 2) {
            coroutineScope.launch {
                swipeableState.snapTo(0)
                onUnarchive(email)
            }
        }
    }
}

