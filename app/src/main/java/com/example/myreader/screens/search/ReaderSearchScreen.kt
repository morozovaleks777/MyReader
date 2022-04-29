package com.example.myreader.screens.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.myreader.components.InputField
import com.example.myreader.components.ListCard
import com.example.myreader.components.ReaderAppBar
import com.example.myreader.model.MBook
import com.example.myreader.navigation.ReaderScreens
import com.google.firebase.auth.internal.R

@ExperimentalComposeUiApi
@Preview
@Composable
fun SearchScreen(navController: NavController= NavController(LocalContext.current)) {
    Scaffold(topBar = { ReaderAppBar(
        title = "Search books",
        icon = Icons.Default.ArrowBack,
        showProfile = false,
        navController =navController,
        OnBackArrowClicked = {
            navController.navigate(ReaderScreens.HomeScreen.name)
        }
    ) }) {
       Surface() {
           Column {
SearchForm(modifier = Modifier
    .fillMaxWidth()
    .padding(16.dp)
)
             Spacer(modifier = Modifier.height(13.dp))
               BookList(navController)
           }

        }

    }
}

@Composable
fun BookList(navController: NavController) {
    val listOfBooks= listOf(
        MBook("124","next book","somebody","something interesting"),
        MBook("124","next book","somebody","something interesting"),
        MBook("124","next book","somebody","something interesting"),
        MBook("124","next book","somebody","something interesting"),
        MBook("124","last book","somebody","something interesting"))
    LazyColumn(modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp) ){
   items(items = listOfBooks){
      book -> BookRow(book,navController,)

   }
    }

}

@Composable
fun BookRow(book: MBook, navController: NavController) {
Card(modifier = Modifier
    .clickable { }
    .fillMaxWidth()
    .height(100.dp)
    .padding(3.dp),
shape = RectangleShape,
elevation = 7.dp) {
    Row(modifier = Modifier.padding(5.dp),
    verticalAlignment = Alignment.Top) {
        val imageUrl="http://books.google.com/books/content?id=kb8dyR3hBnsC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
        Image(painter = rememberImagePainter(data = imageUrl) , contentDescription = "book image",
        modifier = Modifier.padding(end = 4.dp)
            .fillMaxHeight()
            .width(80.dp))
        
        Column() {
            Text(text = book.titles.toString(), overflow = TextOverflow.Ellipsis)
            Text(text = "author : ${book.authors}", overflow = TextOverflow.Clip,
            style = MaterialTheme.typography.caption)
            //Todo add more fields later

        }

    }

}




}

@ExperimentalComposeUiApi
@Composable
fun SearchForm(
    modifier: Modifier = Modifier,
    loading: Boolean= false,
    hint: String="search",
    onSearch: (String) -> Unit={}){
Column() {
    val searchQueryState= rememberSaveable {mutableStateOf("") }
    val keyboardController=LocalSoftwareKeyboardController.current
    val valid= remember(searchQueryState.value) {
        searchQueryState.value.trim().isNotEmpty()
    }
        InputField(valueState = searchQueryState,
            labelId = "search",
            enabled =true ,
            onAction = KeyboardActions {
if(!valid) return@KeyboardActions
              onSearch(searchQueryState.value.trim())
                searchQueryState.value  = ""
                keyboardController?.hide()
            })


}
}