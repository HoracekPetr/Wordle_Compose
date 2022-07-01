package com.example.wordlecompose.ui.theme

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp),
)

val Shapes.ExtraLarge: CornerBasedShape
    get() = RoundedCornerShape(16.dp)




