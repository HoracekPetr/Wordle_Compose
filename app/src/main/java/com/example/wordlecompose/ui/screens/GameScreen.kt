package com.example.wordlecompose.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.wordlecompose.ui.components.LetterRow

@Composable
fun GameScreen(word: String, enabled: Boolean, onButtonClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LetterRow(word = word, enabled = enabled)
        Spacer(modifier = Modifier.size(16.dp))
        Button(onClick = onButtonClick) {
            Text(text = "Flip")
        }
    }
}