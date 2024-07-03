package com.ollerenshawit.jentris.screens

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

// val RainbowRed = Color(0xFFDA034E)
val RainbowOrange = Color(0xFFFF9800)
val RainbowYellow = Color(0xFFFFEB3B)
val RainbowGreen = Color(0xFF4CAF50)
// val RainbowBlue = Color(0xFF2196F3)
// val RainbowIndigo = Color(0xFF3F51B5)
// val RainbowViolet = Color(0xFF9C27B0)

val PastelRainbow = listOf(
    // RainbowRed,
    RainbowOrange,
    RainbowYellow,
    RainbowGreen,
    // RainbowBlue,
    // RainbowIndigo,
    // RainbowViolet
)


@Composable
fun SmoothRainbowText(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = LocalTextStyle.current,
    rainbow: List<Color> = PastelRainbow,
    startColor: Int = 0,
    duration: Int = 1200
) {
    Row(modifier) {
        var index = startColor
        for (letter in text) {
            MultiColorSmoothText(
                text = letter.toString(),
                style = style,
                rainbow = rainbow,
                startIndex = index,
                duration = duration
            )
            index++
            if (index == rainbow.size) index = 0
        }
    }
}

@Composable
fun MultiColorSmoothText(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = LocalTextStyle.current,
    rainbow: List<Color> = PastelRainbow,
    startIndex: Int = 0,
    duration: Int
) {
    val infiniteTransitionScale = rememberInfiniteTransition(label = "infinite transition scale")
    val scale by infiniteTransitionScale.animateFloat(
        initialValue = 1f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Reverse),
        label = "scale"
    )
    val infiniteTransitionColor = rememberInfiniteTransition(label = "infinite transition color")
    val interval = duration / rainbow.size

    val color by infiniteTransitionColor.animateColor(
        initialValue = rainbow[0],
        targetValue = rainbow.last(),
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = duration
                delayMillis = startIndex * interval / 2
                var i = 0
                // set the keyframes from the rainbow with code
                for (color in rainbow) { // this is the crux  of setting the keyframes
                    color at i // at is an infix method in the KeyframesSpec class
                    i += interval
                }
            },
            repeatMode = RepeatMode.Restart
        ), label = ""
    )
    Text(
        text = text,
        textAlign = TextAlign.Center,
        color = color,
        style = style,
        modifier = modifier .graphicsLayer {
            scaleX = scale
            scaleY = scale
            transformOrigin = TransformOrigin.Center
        },
    )
}

@Preview
@Composable
fun Test() {
    MultiColorSmoothText(
        duration = 1200,
        text = "Happy\nJ Day!",
        //.align(Alignment.Center),
        style = MaterialTheme.typography.titleMedium,
    )
}