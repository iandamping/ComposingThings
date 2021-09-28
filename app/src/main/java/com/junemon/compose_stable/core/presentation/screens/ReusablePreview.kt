package com.junemon.compose_stable.core.presentation.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.TextFieldValue
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
    var name = rememberSaveable { mutableStateOf("") }

    ComposingThingsTheme {
        SearchView(value = name.value, onValueChange = { name.value = it })
    }
}


@Composable
fun SearchView(value: String, onValueChange: (String) -> Unit) {
    TextField(
            value = value,
            modifier = Modifier
                    .fillMaxWidth(),
            onValueChange = {
                onValueChange.invoke(it)
            },
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = null,
                        modifier = Modifier
                                .size(40.dp)
                                .padding(8.dp))
            },
            trailingIcon = {
                if (value != TextFieldValue("").text) {
                    IconButton(onClick = {
                        onValueChange.invoke("")
                    }) {
                        Icon(Icons.Default.Close, contentDescription = null,
                                modifier = Modifier
                                        .size(40.dp)
                                        .padding(8.dp))
                    }
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.White,
                    cursorColor = Color.White,
                    leadingIconColor = Color.White,
                    trailingIconColor = Color.White,
                    backgroundColor = colorResource(id = R.color.purple_500),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
            ),
            singleLine = true,
            shape = RectangleShape
    )
}


