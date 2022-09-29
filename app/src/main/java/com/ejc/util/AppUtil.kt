package com.ejc.util

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.ejc.R

@Composable
fun Progress() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(30.dp))
        CircularProgressIndicator()
    }
}

@Composable
fun AppError(error: String?) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(30.dp))
        Text(
            text = context.getString(R.string.text_attention),
            style = MaterialTheme.typography.h6,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = error ?: context.getString(R.string.text_api_error),
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(start = 20.dp, end = 20.dp, top = 5.dp)

        )
    }
}