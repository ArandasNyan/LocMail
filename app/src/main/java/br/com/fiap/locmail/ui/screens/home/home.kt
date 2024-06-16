// ui/screens/HomeScreen.kt
package br.com.fiap.locmail.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.locmail.R
import br.com.fiap.locmail.ui.components.CustomTabRow
import br.com.fiap.locmail.ui.components.DrawerContent
import br.com.fiap.locmail.ui.components.SwipeableEmailItem
import br.com.fiap.locmail.ui.components.buttons.SimpleFab
import br.com.fiap.locmail.ui.components.modals.SearchResultsModal
import br.com.fiap.locmail.ui.theme.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    // Variáveis de estado
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    // Títulos das abas
    val tabTitles = listOf("Todos", "Lidos", "Não Lidos", "Arquivado")

    // Modal de busca
    var showSearchModal by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    // Drawer
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    // Coroutines
    val scope = rememberCoroutineScope()

    // Lista de emails
    val listState = rememberLazyListState()

    // Verifica se o primeiro item da lista está visível
    val isFabExpanded by remember { derivedStateOf { listState.firstVisibleItemIndex == 0 } }

    // Simulação de estado de e-mails arquivados e todos os e-mails
    var allEmails by remember { mutableStateOf(getAllEmails()) }
    var archivedEmails by remember { mutableStateOf(getArchivedEmails()) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(onCloseDrawer = { scope.launch { drawerState.close() } })
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.padding(top = 48.dp))

                /* =================== Heading app with user data =================== */
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp, 0.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.cat), // Substitua com a imagem do usuário
                            contentDescription = "Sender Picture",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .clip(shape = CircleShape)
                                .size(40.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "Bom Dia 👋",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.W300,
                                color = Zinc500
                            )
                            Text(
                                text = "Arandas Nyan",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.W500,
                                color = Zinc700
                            )
                        }
                    }

                    Row {
                        IconButton(onClick = { showSearchModal = true }) {
                            Icon(
                                painter = painterResource(id = R.drawable.search),
                                contentDescription = "Search",
                                tint = Zinc700 // Use a cor original do ícone
                            )
                        }
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(
                                painter = painterResource(id = R.drawable.menu),
                                contentDescription = "Menu",
                                tint = Zinc700 // Use a cor original do ícone
                            )
                        }
                    }
                }

                /* =================== TabSlide Container =================== */
                CustomTabRow(
                    selectedTabIndex = selectedTabIndex,
                    onTabSelected = { index -> selectedTabIndex = index },
                    tabTitles = tabTitles
                )

                /* =================== Section Emails List =================== */
                Column(modifier = Modifier.fillMaxWidth()) {
                    when (selectedTabIndex) {
                        0 -> EmailListContent(
                            emails = allEmails,
                            listState = listState,
                            onArchive = {
                                email ->
                                    allEmails = allEmails - email
                                    archivedEmails = archivedEmails + email
                            },
                            onUnarchive = {},
                            isArchiving = true,
                            selectedTabIndex = selectedTabIndex
                        )
                        1 -> EmailListContent(
                            emails = getReadEmails(),
                            listState = listState,
                            onArchive = {},
                            onUnarchive = {},
                            isArchiving = false,
                            selectedTabIndex = selectedTabIndex
                        )
                        2 -> EmailListContent(
                            emails = getUnreadEmails(),
                            listState = listState,
                            onArchive = {},
                            onUnarchive = {},
                            isArchiving = false,
                            selectedTabIndex = selectedTabIndex
                        )
                        3 -> EmailListContent(
                            emails = archivedEmails,
                            listState = listState,
                            onArchive = {},
                            onUnarchive = {
                                email ->
                                    archivedEmails = archivedEmails - email
                                    allEmails = allEmails + email
                            },
                            isArchiving = true,
                            selectedTabIndex = selectedTabIndex
                        )
                    }
                }
                /* =================== Section Search Modal =================== */
                if (showSearchModal) {
                    SearchResultsModal(
                        query = searchQuery,
                        onDismissRequest = { showSearchModal = false },
                        onSearch = { query ->
                            searchQuery = query
                            // Atualizar resultados de busca aqui
                        }
                    )
                }
            }

            /* =================== Section FabButton =================== */
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.BottomEnd // Ajuste para posicionar o FAB no canto inferior direito
            ) {
                SimpleFab(isFabExpanded)
            }
        }
    }
}

/* =================== List emails Content =================== */
@Composable
fun EmailListContent(
    emails: List<Email>,
    listState: LazyListState,
    onArchive: (Email) -> Unit,
    onUnarchive: (Email) -> Unit,
    isArchiving: Boolean,
    selectedTabIndex: Int
) {
    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 6.dp)
    ) {
        items(emails) { email ->
            SwipeableEmailItem(
                email = email,
                onArchive = onArchive,
                onUnarchive = onUnarchive,
                isArchiving = isArchiving,
                selectedTabIndex = selectedTabIndex
            )
        }
    }
}

/* =================== Section System for Emails =================== */
// Dados fictícios para emails
data class Email(
    val sender: String,
    val subject: String,
    val contentPreview: String,
    val time: String,
    val isRead: Boolean,
    val iconId: Int,
    val isArchived: Boolean = false
)

fun getAllEmails(): List<Email> = listOf(
    // Adicione emails fictícios aqui
    Email(
        sender = "Laura Faustino",
        subject = "Design app do challenge local web",
        contentPreview = "Olá, tudo bem? Queria falar sobre o seu design app.",
        time = "10:10 PM",
        isRead = true,
        iconId = R.drawable.laurafaustino,
        isArchived = true
    ),
    Email(
        sender = "Laura Faustino",
        subject = "Design app do challenge local web",
        contentPreview = "Olá, tudo bem? Queria falar sobre o seu design app.",
        time = "10:10 PM",
        isRead = false,
        iconId = R.drawable.laurafaustino,
        isArchived = true
    ),
    Email(
        sender = "Laura Faustino",
        subject = "Design app do challenge local web",
        contentPreview = "Olá, tudo bem? Queria falar sobre o seu design app.",
        time = "10:10 PM",
        isRead = false,
        iconId = R.drawable.laurafaustino,
        isArchived = false
    ),
    Email(
        sender = "Laura Faustino",
        subject = "Design app do challenge local web",
        contentPreview = "Olá, tudo bem? Queria falar sobre o seu design app.",
        time = "10:10 PM",
        isRead = true,
        iconId = R.drawable.laurafaustino,
        isArchived = false
    ),
    Email(
        sender = "Laura Faustino",
        subject = "Design app do challenge local web",
        contentPreview = "Olá, tudo bem? Queria falar sobre o seu design app.",
        time = "10:10 PM",
        isRead = true,
        iconId = R.drawable.laurafaustino,
        isArchived = false
    ),Email(
        sender = "Laura Faustino",
        subject = "Design app do challenge local web",
        contentPreview = "Olá, tudo bem? Queria falar sobre o seu design app.",
        time = "10:10 PM",
        isRead = true,
        iconId = R.drawable.laurafaustino,
        isArchived = false
    ),
    Email(
        sender = "Laura Faustino",
        subject = "Design app do challenge local web",
        contentPreview = "Olá, tudo bem? Queria falar sobre o seu design app.",
        time = "10:10 PM",
        isRead = true,
        iconId = R.drawable.laurafaustino,
        isArchived = false
    ),Email(
        sender = "Laura Faustino",
        subject = "Design app do challenge local web",
        contentPreview = "Olá, tudo bem? Queria falar sobre o seu design app.",
        time = "10:10 PM",
        isRead = true,
        iconId = R.drawable.laurafaustino,
        isArchived = false
    ),
    Email(
        sender = "Laura Faustino",
        subject = "Design app do challenge local web",
        contentPreview = "Olá, tudo bem? Queria falar sobre o seu design app.",
        time = "10:10 PM",
        isRead = true,
        iconId = R.drawable.laurafaustino,
        isArchived = false
    ),Email(
        sender = "Laura Faustino",
        subject = "Design app do challenge local web",
        contentPreview = "Olá, tudo bem? Queria falar sobre o seu design app.",
        time = "10:10 PM",
        isRead = true,
        iconId = R.drawable.laurafaustino,
        isArchived = false
    ),
    Email(
        sender = "Laura Faustino",
        subject = "Design app do challenge local web",
        contentPreview = "Olá, tudo bem? Queria falar sobre o seu design app.",
        time = "10:10 PM",
        isRead = true,
        iconId = R.drawable.laurafaustino,
        isArchived = false
    ),
)

fun getReadEmails(): List<Email> = getAllEmails().filter { it.isRead }

fun getUnreadEmails(): List<Email> = getAllEmails().filter { !it.isRead }

fun getArchivedEmails(): List<Email> = getAllEmails().filter { it.isArchived }
