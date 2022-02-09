package com.junemon.compose_stable

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.junemon.compose_stable.Constant.FILTER_ITEM
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetScreen(modifier: Modifier = Modifier) {
    var selectedItem by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetScaffoldState = rememberModalBottomSheetState(
        ModalBottomSheetValue.Hidden
    )

    ModalBottomSheetLayout(
        sheetState = bottomSheetScaffoldState,
        sheetContent = {
            Column(modifier = Modifier.fillMaxWidth()) {
                IconButton(modifier = Modifier
                    .padding(vertical = 8.dp)
                    .align(Alignment.End), onClick = {
                    coroutineScope.launch {
                        if (bottomSheetScaffoldState.isVisible) {
                            bottomSheetScaffoldState.hide()
                        }
                    }
                }, content = {
                    Image(imageVector = Icons.Default.Close, contentDescription = "Close Item")
                })

                LazyColumn {
                    items(FILTER_ITEM) { singleItem ->
                        BottomSheetItemScreen(filterItem = singleItem, selectedFilterItem = {
                            coroutineScope.launch {
                                if (bottomSheetScaffoldState.isVisible) {
                                    bottomSheetScaffoldState.hide()
                                }
                            }
                            selectedItem = it.filterText
                        })
                    }
                }
            }
        }) {
        OutlinedButton(onClick = {
            coroutineScope.launch {
                if (!bottomSheetScaffoldState.isVisible) {
                    bottomSheetScaffoldState.show()
                }
            }
        }) {
            Text(text = "Show bottom sheet")
        }

        if (selectedItem.isNotEmpty()) {
            Toast.makeText(context, selectedItem, Toast.LENGTH_SHORT).show()
        }
    }
}


@Composable
fun BottomSheetItemScreen(
    modifier: Modifier = Modifier,
    filterItem: FilterItem,
    selectedFilterItem: (FilterItem) -> Unit
) {

    ConstraintLayout(
        modifier = modifier
            .clickable {
                selectedFilterItem(filterItem)
            }
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        val (filterImage, filterText, closeIcon) = createRefs()
        Image(
            modifier = Modifier
                .size(50.dp)
                .constrainAs(filterImage) {
                    top.linkTo(closeIcon.bottom)
                    start.linkTo(parent.start)
                    centerVerticallyTo(parent)
                },
            painter = painterResource(id = filterItem.filterIcon),
            contentDescription = "filter 0"
        )

        Text(
            modifier = Modifier.constrainAs(filterText) {
                start.linkTo(filterImage.end, margin = 16.dp)
                centerVerticallyTo(parent)
            },
            text = filterItem.filterText,
            style = MaterialTheme.typography.subtitle2
        )
    }

}