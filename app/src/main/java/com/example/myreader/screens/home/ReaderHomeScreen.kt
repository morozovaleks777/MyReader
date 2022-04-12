package com.example.myreader.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myreader.components.FABContent
import com.example.myreader.components.ListCard
import com.example.myreader.components.ReaderAppBar
import com.example.myreader.components.TitleSection
import com.example.myreader.model.MBook
import com.example.myreader.navigation.ReaderScreens
import com.google.firebase.auth.FirebaseAuth

@Composable
fun HomeScreen(navController: NavController){
  Scaffold(topBar = {
                    ReaderAppBar(title = "My Reader", navController =navController )
  },
  floatingActionButton = {
      FABContent{}
  }) {
      Surface(modifier = Modifier.fillMaxSize()) {
          HomeContent(navController = navController)
      }

  }
}

@Composable
fun HomeContent(navController: NavController){

    val listOfBooks= listOf(
        MBook("124","next book","somebody","something interesting"),
        MBook("124","next book","somebody","something interesting"),
        MBook("124","next book","somebody","something interesting"),
        MBook("124","next book","somebody","something interesting"),
        MBook("124","last book","somebody","something interesting"))

    val email = FirebaseAuth.getInstance().currentUser?.email
    val currentUserName=if(!email.isNullOrEmpty()){
        email.split("@")[0]
    }else {"NoName"}

    Column(modifier = Modifier.padding(2.dp),
    verticalArrangement = Arrangement.Top) {
        Row(
            modifier = Modifier.align(alignment = Alignment.Start)
        ) {
            TitleSection(label = "Your reading \n " + "activity right now")
            Spacer(modifier = Modifier.fillMaxWidth(0.7f))
            Column {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription ="Profile" ,
                modifier = Modifier
                    .clickable {
                        navController.navigate(ReaderScreens.StatsScreen.name)
                    }
                    .size(45.dp),
                tint = MaterialTheme.colors.secondaryVariant)

                Text(text = currentUserName.toString(),
                modifier = Modifier.padding(2.dp),
                    style = MaterialTheme.typography.overline,
                    color = Color.Red,
                    fontSize = 15.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Clip
                )
                Divider()
                
            }
        }
        ReadingRightNowArea(books = listOf(), navController =navController )
        
        TitleSection(label = "Reading list")

        BookListArea(listOfBooks //= emptyList<MBook>()
            ,navController=navController)

    }
}

@Composable
fun BookListArea(listOfBooks: List<MBook>, navController: NavController) {
    HorizontalScrollableComponent(listOfBooks){
        //Todo : on card clicked go to details
    }
}

@Composable
fun HorizontalScrollableComponent(listOfBooks: List<MBook> ,onCardPressed: (String) ->Unit) {
    val scrollState = rememberScrollState()
    Row(modifier = Modifier
        .fillMaxWidth()
        .heightIn(280.dp)
        .horizontalScroll(scrollState)) {
for(book in listOfBooks){
    ListCard(book){
        onCardPressed(it)
    }
}
    }

}


@Composable
fun ReadingRightNowArea(
    books:List<MBook>, navController: NavController
){
    ListCard()

}




