package com.example.wordlecompose.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wordlecompose.util.BoxType
import com.example.wordlecompose.util.FlipType
import kotlin.math.abs



@Composable
fun LetterIconBox(
    boxType: BoxType,
    boxSize: Dp = 65.dp,
    textSize: TextUnit = 40.sp,
    text: String = "",
    icon: ImageVector? = null,
    textColor: Color = Color.Black,
    backgroundColor: Color = Color.Transparent,
    borderColor: Color = Color.DarkGray,
    borderWidth: Dp = 2.dp,
    letterBoxShape: Shape = RoundedCornerShape(2.dp),
    onKeyboardClick: (String) -> Unit = {},
    onBoxClick: () -> Unit = {}
) {
    if (boxType == BoxType.GAME) {
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

    if (boxType == BoxType.KEYBOARD) {
        Box(
            modifier = Modifier
                .clip(letterBoxShape)
                .background(color = backgroundColor)
                .size(width = boxSize, height = boxSize)
                .border(width = borderWidth, color = borderColor)
                .clickable { if (icon != null) onBoxClick() else onKeyboardClick(text) },
            contentAlignment = Alignment.Center
        ) {
            if (icon != null) {
                Icon(imageVector = icon, contentDescription = "Icon")
            } else {
                Text(
                    text = text,
                    color = textColor,
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = textSize)
                )
            }
        }
    }
}

@Composable
fun DoubleSideLetterBox(
    translationX: Float = 0f,
    translationY: Float = 0f,
    rotationX: Float = 0f,
    rotationY: Float = 0f,
    rotationZ: Float = 0f,
    cameraDistance: Float = 8f,
    flipType: FlipType,
    front: @Composable () -> Unit,
    back: @Composable () -> Unit
) {

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

@Composable
fun RotatingDoubleSide(
    delay: Int,
    letter: String,
    flipEnabled: Boolean,
    backgroundColor: Color,
    isError: Boolean
) {

    if(!isError){
        val transition = updateTransition(targetState = flipEnabled, label = "")

        val rotationX by transition.animateFloat(
            transitionSpec = { tween(delayMillis = delay, durationMillis = 200) },
            label = ""
        ) { enabled ->
            when (enabled) {
                true -> 180f
                false -> 0f
            }
        }

        DoubleSideLetterBox(
            flipType = FlipType.HORIZONTAL,
            rotationX = rotationX,
            front = {
                LetterIconBox(boxType = BoxType.GAME, text = letter)
            },
            back = {
                LetterIconBox(
                    boxType = BoxType.GAME,
                    text = letter,
                    backgroundColor = backgroundColor,
                    textColor = Color.White
                )
            })
    } else {

        val errorColor = animateColorAsState(
            targetValue = if(isError) Color(211, 47, 47, 255) else Color.Transparent
        )

        DoubleSideLetterBox(
            flipType = FlipType.HORIZONTAL,
            front = {
                LetterIconBox(boxType = BoxType.GAME, text = letter, backgroundColor = errorColor.value)
            },
            back = {
                LetterIconBox(
                    boxType = BoxType.GAME,
                    text = letter
                )
            })
    }
}
