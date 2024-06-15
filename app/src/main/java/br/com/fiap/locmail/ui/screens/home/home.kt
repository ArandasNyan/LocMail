// ui/screens/HomeScreen.kt
package br.com.fiap.locmail.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.locmail.R
import br.com.fiap.locmail.ui.components.modals.SearchResultsModal
import br.com.fiap.locmail.ui.theme.*
import br.com.fiap.locmail.ui.components.CustomTabRow
import br.com.fiap.locmail.ui.components.DrawerContent
import br.com.fiap.locmail.ui.components.EmailListItem
import kotlinx.coroutines.launch

@Composable
fun HomeScreen() {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabTitles = listOf("Todos", "Lidos", "Não Lidos", "Arquivado")
    var showSearchModal by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(onCloseDrawer = { scope.launch { drawerState.close() } })
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
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
                            color = Zinc500,
                            onTextLayout = {}
                        )
                        Text(
                            text = "Arandas Nyan",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W600,
                            color = Zinc700,
                            onTextLayout = {}
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
                    0 -> EmailListContent(emails = getAllEmails())
                    1 -> EmailListContent(emails = getReadEmails())
                    2 -> EmailListContent(emails = getUnreadEmails())
                    3 -> EmailListContent(emails = getArchivedEmails())
                }
            }

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
    }
}

/* =================== List emails Content =================== */
@Composable
fun EmailListContent(emails: List<Email>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 6.dp)
    ) {
        items(emails) { email ->
            EmailListItem(
                sender = email.sender,
                subject = email.subject,
                contentPreview = email.contentPreview,
                time = email.time,
                userIconId = email.iconId,
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
        "Laura Faustino",                     /* Nome do remetente */
        "Design app do challenge local web",  /* Titulo do email */
        "Olá, tudo bem? queria falar sobe o seu design app",               /* Preview do email */
        "10:10 PM",                             /* Hora do email */
        true,                                /* Email não lido */
        R.drawable.laurafaustino,                    /* Icone do remetente */
        isArchived = true,                           /* Email arquivado */
    ),
    Email(
        "Laura Faustino",                     /* Nome do remetente */
        "Design app do challenge local web",  /* Titulo do email */
        "Olá, tudo bem? queria falar sobe o seu design app",               /* Preview do email */
        "10:10 PM",                             /* Hora do email */
        false,                                /* Email não lido */
        R.drawable.laurafaustino,                    /* Icone do remetente */
        isArchived = false,                           /* Email arquivado */
    ),
    Email(
        "Laura Faustino",                     /* Nome do remetente */
        "Design app do challenge local web",  /* Titulo do email */
        "Olá, tudo bem? queria falar sobe o seu design app",               /* Preview do email */
        "10:10 PM",                             /* Hora do email */
        false,                                /* Email não lido */
        R.drawable.laurafaustino,                    /* Icone do remetente */
        isArchived = false,                           /* Email arquivado */
    ),
)

fun getReadEmails(): List<Email> = getAllEmails().filter { it.isRead }

fun getUnreadEmails(): List<Email> = getAllEmails().filter { !it.isRead }

fun getArchivedEmails(): List<Email> = getAllEmails().filter { it.isArchived }
