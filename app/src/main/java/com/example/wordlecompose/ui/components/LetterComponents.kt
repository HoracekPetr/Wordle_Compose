package com.example.wordlecompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.abs

@Composable
fun LetterBox(
    boxSize: Dp = 70.dp,
    textSize: TextUnit = 40.sp,
    text: String,
    textColor: Color = Color.Black,
    backgroundColor: Color = Color.Transparent,
    borderColor: Color = Color.DarkGray,
    borderWidth: Dp = 2.dp,
    letterBoxShape: Shape = RoundedCornerShape(2.dp)
) {
    Box(
        modifier = Modifier
            .clip(letterBoxShape)
            .background(color = backgroundColor)
            .size(width = boxSize, height = boxSize)
            .border(width = borderWidth, color = borderColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = textColor,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = textSize)
        )
    }
}

enum class FlipType {
    HORIZONTAL, VERTICAL,
}

@Composable
fun DoubleSide(
    translationX: Float = 0f,
    translationY: Float = 0f,
    rotationX: Float = 0f,
    rotationY: Float = 0f,
    rotationZ: Float = 0f,
    cameraDistance: Float = 8f,
    flipType: FlipType,
    front: @Composable () -> Unit,
    back: @Composable () -> Unit
){

    fun isHorizontallyFlip() = (abs(rotationX) % 360 > 90f && abs(rotationX) % 360 < 270f)
    fun isVerticallyFlip() = (abs(rotationY) % 360 > 90f && abs(rotationY) % 360 < 270f)

    fun isFlipped() = isVerticallyFlip() xor isHorizontallyFlip()


    if (isFlipped()) {
        val rotationXBack =
            if (flipType == FlipType.HORIZONTAL)
                rotationX - 180
            else
                rotationX

        val rotationYBack =
            if (flipType == FlipType.VERTICAL)
                rotationY - 180
            else
            // Need to negative value, suspecting a bug on rotation when a flipped
            // horizontally card is rotating left to right, it will reverse
            // hence need to negate it
                -rotationY
        Box(
            Modifier
                .graphicsLayer(
                    translationX = translationX,
                    translationY = translationY,
                    rotationX = rotationXBack,
                    rotationY = rotationYBack,
                    rotationZ = -rotationZ,
                    cameraDistance = cameraDistance
                )
        ) {
            back()
        }
    } else {
        Box(
            Modifier
                .graphicsLayer(
                    translationX = translationX,
                    translationY = translationY,
                    rotationX = rotationX,
                    rotationY = rotationY,
                    rotationZ = rotationZ,
                    cameraDistance = cameraDistance
                )
        ) {
            front()
        }
    }
}