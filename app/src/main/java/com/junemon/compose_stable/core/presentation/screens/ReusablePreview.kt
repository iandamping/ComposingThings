package com.junemon.compose_stable.core.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import com.junemon.compose_stable.R
import com.junemon.compose_stable.ui.theme.ComposingThingsTheme

/**
 * Created by Ian Damping on 07,September,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */

@Preview(showBackground = true)
@ExperimentalUnitApi
@Composable
fun DefaultPreview() {
    ComposingThingsTheme {
        // ToolbarWidget()
        // LazyColumn(Modifier.padding(Dp(8f))) {
        //     items(items = DummyModel.LIST_DUMMY_NEWS) { singleNews ->
        //         Row(Modifier.fillMaxSize()) {
        //
        //             Column(modifier = Modifier
        //                 .weight(2.5f)
        //                 .height(Dp(150F)),
        //                 verticalArrangement = Arrangement.SpaceBetween) {
        //                 Text(
        //                     text = singleNews.newsTitle,
        //                     color = Color.Black,
        //                     fontSize = TextUnit(value = 16F, TextUnitType.Sp),
        //                     modifier = Modifier.padding(
        //                         Dp(4F)
        //                     )
        //                 )
        //                 // Spacer(Modifier = Modifier.height(Dp(8f)))
        //
        //                 Row {
        //                     Text(
        //                         text = singleNews.newsAuthor,
        //                         color = Color.Gray,
        //                         modifier = Modifier.padding(Dp(4F)).weight(3f)
        //                     )
        //
        //                     Image(
        //                         painter = painterResource(id = R.drawable.ic_baseline_more_vert_24),
        //                         contentDescription = null,
        //                         modifier = Modifier.padding(Dp(4f)).weight(1f)
        //                     )
        //                 }
        //
        //             }
        //
        //             Image(
        //                 painter = rememberImagePainter(singleNews.newsImage, builder = {
        //                     crossfade(true)
        //                 }), contentDescription = null,
        //                 contentScale = ContentScale.Crop,
        //                 modifier = Modifier
        //                     .height(Dp(150F))
        //                     .width(Dp(150F))
        //                     .clip(RoundedCornerShape(Dp(8F)))
        //                     .weight(1f)
        //
        //             )
        //
        //         }
        //         Spacer(modifier = Modifier.height(Dp(8f)))
        //
        //     }
        // }
    }
}

// @Composable
// fun ToolbarWidget() {
//     val expanded = remember { mutableStateOf(false)}
//     val result = remember { mutableStateOf("") }
//     // theme for our app.
//     Scaffold(
//         // below line we are
//         // creating a top bar.
//         topBar = {
//             TopAppBar(
//                 // in below line we are
//                 // adding title to our top bar.
//                 title = {
//                     // inside title we are
//                     // adding text to our toolbar.
//                     Text(
//                         text = "Geeks for Geeks",
//                         // below line is use
//                         // to give text color.
//                         color = Color.White
//                     )
//                 },
//                 navigationIcon = {
//                     // navigation icon is use
//                     // for drawer icon.
//                     IconButton(onClick = { }) {
//                         // below line is use to
//                         // specify navigation icon.
//                         Icon(
//                             Icons.Filled.ArrowBack,
//                             contentDescription = null
//                         )
//                     }
//                 },
//                 actions = {
//                     Box(
//                         Modifier
//                             .wrapContentSize(Alignment.TopEnd)
//                     ) {
//                         IconButton(onClick = {
//
//                         }) {
//                             Icon(
//                                 Icons.Filled.MoreVert,
//                                 contentDescription = "Localized description"
//                             )
//                         }
//
//                         DropdownMenu(
//                             expanded = expanded.value,
//                             onDismissRequest = { expanded.value = false },
//                         ) {
//                             DropdownMenuItem(onClick = {
//                                 expanded.value = false
//                                 result.value = "First item clicked"
//                             }) {
//                                 Text("First Item")
//                             }
//
//                             DropdownMenuItem(onClick = {
//                                 expanded.value = false
//                                 result.value = "Second item clicked"
//                             }) {
//                                 Text("Second item")
//                             }
//
//                             DropdownMenuItem(onClick = {
//                                 expanded.value = false
//                                 result.value = "Third item clicked"
//                             }) {
//                                 Text("Third item")
//                             }
//
//                             DropdownMenuItem(onClick = {
//                                 expanded.value = false
//                                 result.value = "Fourth item clicked"
//                             }) {
//                                 Text("Fourth item")
//                             }
//                         }
//                     }
//                 },
//                 // below line is use to give background color
//                 backgroundColor = colorResource(id = R.color.purple_200),
//
//                 // content color is use to give
//                 // color to our content in our toolbar.
//                 contentColor = Color.White,
//
//                 // below line is use to give
//                 // elevation to our toolbar.
//                 elevation = 12.dp
//             )
//         }){
//
//     }
// }


