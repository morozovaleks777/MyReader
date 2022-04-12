package com.example.myreader.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.myreader.model.MBook
import com.example.myreader.navigation.ReaderScreens
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ReaderLogo(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier.padding(bottom = 16.dp),
        text = "My Reader",
        style = MaterialTheme.typography.h3,
        color = Color.Red.copy(alpha = 0.5f)
    )
}

@Composable
fun EmailInput(
    modifier: Modifier = Modifier,
    emailState: MutableState<String>,
    enabled: Boolean=true,
    labelId: String = "email",
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
){
    InputField(
        modifier = modifier,
        valueState = emailState,
        labelId = labelId,
        imeAction = imeAction,
        onAction = onAction,
        enabled = enabled
    )
}

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    labelId: String,
    enabled: Boolean,
    isSingleLine:Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    OutlinedTextField(value = valueState.value,
        onValueChange ={valueState.value=it},
        label={ Text(text = labelId) },
        singleLine = isSingleLine,
        textStyle = TextStyle(fontSize = 18.sp,
            color = MaterialTheme.colors.onBackground),
        modifier = modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType=keyboardType,
            imeAction = imeAction)
    )
}

@Composable
fun PasswordInput(
    modifier: Modifier,
    passwordState: MutableState<String>,
    labelId: String,
    enabled: Boolean,
    passwordVisibility: MutableState<Boolean>,
    imeAction: ImeAction = ImeAction.Done,
    onAction: KeyboardActions = KeyboardActions.Default,
) {

    val visualTransformation = if (passwordVisibility.value) VisualTransformation.None else
        PasswordVisualTransformation()
    OutlinedTextField(value = passwordState.value,
        onValueChange = {
            passwordState.value = it
        },
        label = { Text(text = labelId)},
        singleLine = true,
        textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colors.onBackground),
        modifier = modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        enabled = enabled,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction),
        visualTransformation = visualTransformation,
        trailingIcon = {PasswordVisibility(passwordVisibility = passwordVisibility)},
        keyboardActions = onAction)

}

@Composable
fun PasswordVisibility(passwordVisibility: MutableState<Boolean>) {
    val visible = passwordVisibility.value
    IconButton(onClick = { passwordVisibility.value = !visible}) {
        Icons.Default.Close
    }
}

@Composable
fun TitleSection(
    modifier: Modifier =Modifier,
    label:String
){
    Surface(modifier = modifier.padding(start = 5.dp, top = 1.dp)) {
        Column {
            Text(text = label,
                fontSize = 19.sp,
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Left)

        }
    }
}

@Composable
fun FABContent(onTap: () -> Unit) {
    FloatingActionButton(onClick = {onTap() },
        shape = RoundedCornerShape(50.dp),
        backgroundColor = Color(0xFF92CBDF),
    ) {
        Icon(imageVector = Icons.Default.Add ,
            contentDescription ="add a book" ,
            tint = Color.White
        )

    }
}

@Composable
fun ReaderAppBar(
    title:String,
    showProfile:Boolean=true,
    navController: NavController
){
    TopAppBar(title = {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if(showProfile){
                Icon(imageVector =Icons.Default.Favorite,
                    contentDescription ="logo icon",
                    modifier = Modifier
                        .clip(RoundedCornerShape(size = 12.dp))
                        .scale(0.9f)
                    // .background(color = Color.LightGray)
                    // .border(BorderStroke(width = 1.dp, color = Color.Black))
                )
            }
            Spacer(modifier = Modifier.width(30.dp))

            Text(text = title,
                color = Color.Red.copy(alpha = 0.7f),
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
            )

            Spacer(modifier = Modifier.width(100.dp))


        }
    },
        actions = {
            IconButton(onClick = {
                FirebaseAuth.getInstance().signOut().run {
                    navController.navigate(ReaderScreens.LoginScreen.name)
                }
            }) {
                Icon(imageVector = Icons.Default.Logout, contentDescription ="logout icon",
                    tint = Color.Green.copy(alpha = 0.4f))
            }
        },
        backgroundColor = Color.Transparent,
        elevation = 0.dp)


}

@Composable
fun BookingRating(score: Double = 4.5) {
    Surface(
        modifier = Modifier
            .padding(4.dp)
            .height(70.dp),
        shape = RoundedCornerShape(56.dp),
        elevation = 6.dp,
        color = Color.White
    ) {
        Column(modifier = Modifier.padding(4.dp)) {
            Icon(
                imageVector = Icons.Filled.StarBorder, contentDescription = "Star",
                modifier = Modifier.padding(3.dp)
            )

            Text(
                text = score.toString(),
                style = MaterialTheme.typography.subtitle1
            )

        }

    }
}

@Preview
@Composable
fun RoundedButton(
    label:String = "Reading",
    radius: Int = 29,
    onPress: () ->  Unit ={}
){
    Surface(modifier = Modifier.clip(RoundedCornerShape(bottomEndPercent = radius, topStartPercent = radius)),
        color=Color(0xFF92CBDF)) {
        Column(modifier = Modifier
            .width(90.dp)
            .heightIn(40.dp)
            .clickable {
                onPress.invoke()
            },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Text(text = label, style = TextStyle(color = Color.White, fontSize = 15.sp))

        }

    }
}

@Preview
@Composable
fun ListCard(mBook: MBook = MBook(id="123", titles ="book title", authors = "book authors", notes = "notes" ),
             onPressDetails :(String) ->Unit = {}){

    val context = LocalContext.current
    val resource = context.resources
    val displayMetrics = resource.displayMetrics
    val screenWidth = displayMetrics.widthPixels / displayMetrics.density
    val spasing = 10.dp
    Card(shape = RoundedCornerShape(29.dp),
        backgroundColor = Color.White,
        elevation = 6.dp,
        modifier = Modifier
            .padding(16.dp)
            .width(202.dp)
            .height(242.dp)
            .clickable {
                onPressDetails.invoke(mBook.titles.toString())
            }
    ) {


        Column(modifier = Modifier.width(screenWidth.dp - (spasing*2)),
            horizontalAlignment = Alignment.Start) {

            Row(horizontalArrangement = Arrangement.Center) {
                Image(painter = rememberImagePainter(
                    data = "http://books.google.com/books/content?id=kb8dyR3hBnsC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"),
                    contentDescription = "book image" ,
                    modifier = Modifier
                        .width(100.dp)
                        .height(140.dp)
                        .padding(4.dp))

                Spacer(modifier =Modifier.width(50.dp))

                Column(modifier = Modifier.padding(top = 25.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(imageVector =Icons.Rounded.FavoriteBorder , contentDescription ="favorite icon",
                        modifier = Modifier.padding(bottom = 1.dp)
                    )

                    BookingRating(score=3.5)

                }

            }

            Text(text = mBook.titles.toString(),
                modifier = Modifier.padding(4.dp),
                fontWeight = FontWeight.Bold ,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis)

            Text(text = mBook.authors.toString(),
                modifier = Modifier.padding(4.dp),
                style = MaterialTheme.typography.caption)


        }
        Row(horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Bottom) {
            RoundedButton(radius = 70)

        }

    }

}
