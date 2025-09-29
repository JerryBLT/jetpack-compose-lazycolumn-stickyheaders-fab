package com.example.lazycolumnwithstickyheaders

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lazycolumnwithstickyheaders.ui.theme.LazyColumnWithStickyHeadersTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LazyColumnWithStickyHeadersTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ContactListScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContactListScreen( modifier: Modifier = Modifier) {
    // Generate 50+ fake contacts and group alphabetically
    val contacts = remember { generateContacts() }
    val grouped = remember { contacts.groupBy { it.first().uppercaseChar() } }

    // State object to track scroll position
    val listState = rememberLazyListState()

    // Coroutine scope for smooth scrolling
    val scope = rememberCoroutineScope()


    // wrap it in derivedStateOf so recomposition happens only when the boolean changes
    val showButton by remember {
        derivedStateOf { listState.firstVisibleItemIndex > 10 }
    }

    Scaffold(
        floatingActionButton = {
            // Show FAB only after scrolling past item 10
            if (showButton) {
                FloatingActionButton(onClick = {
                    scope.launch {
                        // Smooth scroll back to the first item
                        listState.animateScrollToItem(0)
                    }
                }) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowUp,
                        contentDescription = "Scroll to Top"
                    )
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            state = listState,
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            grouped.forEach { (letter, names) ->
                // Sticky header for each alphabet group
                stickyHeader {
                    Text(
                        text = letter.toString(),
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .padding(10.dp)
                    )
                }
                // List items under each header
                items(names) { name ->
                    Text(
                        text = name,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 15.dp)
                    )
                }
            }
        }
    }
}

// Helper: generates 50 sample names
fun generateContacts(): List<String> {
    val names = listOf(
        "Alice", "Aaron", "Bobby", "Becky", "Bella", "Cathy", "Chris", "Clara",
        "David", "Diana", "Ethan", "Ela", "Frank", "Fiona", "George", "Grace",
        "Hank", "Holly", "Ian", "Ivy", "Jerry", "Jill", "Kate", "Kyle",
        "Leo", "Luna", "Mayra", "Maria", "Nina", "Nick", "Olivia", "Oscar",
        "Paul", "Paula", "Quinn", "Quincy", "Rachel", "Ryan", "Sam", "Sophia",
        "Tom", "Tina", "Uma", "Ursula", "Victor", "Vera", "Will", "Wendy",
        "Xander", "Yara", "Zane"
    )
    return names.shuffled()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LazyColumnWithStickyHeadersTheme {
        ContactListScreen()
    }
}