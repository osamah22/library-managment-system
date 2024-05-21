package com.android.libraryManagementSystem

import android.widget.GridLayout.Spec
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.Dispatchers

@Composable
fun HomeScreen(
    booksViewModel: BooksViewModel,
    navHost: NavHostController,
    modifier: Modifier = Modifier,
    nextDestination: NavDestination
) {
    val states = listOf("Wants to read", "Reading", "Read")
    var stateNumber = 1
    // 2. LazyColumn Setup
    LazyColumn(Modifier.padding(20.dp)) {

        items(states/*,key={it.id}*/){state->
            ShowShelve(
                stateName = state,
                stateNumber = stateNumber,
                foe = { navHost.navigate(nextDestination.navigatorName) },
                bVM = booksViewModel
            )
            stateNumber++
        }
        item {
            Text(text = "", Modifier.height(60.dp))
            Button(onClick = { navHost.navigate("ABScreen")}, modifier = Modifier
                .fillMaxWidth()) {
                Text(text = "Add book")
            }
        }
    }
}

@Composable
fun ShowShelve(stateName: String, stateNumber : Int, foe: () -> Unit = {}, bVM: BooksViewModel){
    Text(text = stateName, Modifier.fillMaxWidth())
    Button(onClick = {bVM.setState(stateNumber); foe()}, Modifier.fillMaxWidth()) {
        Text(text = "show")
    }
}