package com.ollerenshawit.jentris.screens

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ollerenshawit.jentris.R
import com.ollerenshawit.jentris.formatLocalDateToString
import com.ollerenshawit.jentris.getNextJday
import com.ollerenshawit.jentris.getSleepsBetweenDates
import com.ollerenshawit.jentris.ui.theme.JentrisTheme
import java.time.LocalDate

@Composable
fun HomeScreen(navController: NavController) {
    JentrisTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            CalculateAndShowJentris()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    JentrisTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            CalculateAndShowJentris()
        }
    }
}

@Composable
fun CalculateAndShowJentris(modifier: Modifier = Modifier) {
    val currentDateFormatted = formatLocalDateToString(LocalDate.now())
    val jdayDateFormatted = formatLocalDateToString(getNextJday())
    val numSleeps = getSleepsBetweenDates(LocalDate.now(), getNextJday())

    val numSleepsText: String = if(numSleeps == 0) {
        "Happy J Day!"
    }
    else {
        numSleeps.toString()
    }

    ShowMainContent(currentDateFormatted = currentDateFormatted,
        jdayDateFormatted = jdayDateFormatted,
        numSleepsText = numSleepsText,
        modifier = modifier
    )
}

@Composable
fun ShowMainContent(currentDateFormatted: String, jdayDateFormatted: String, numSleepsText: String, modifier: Modifier = Modifier) {
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(8.dp)
    ){
        // Today's date box
        ShowDateContent(titleText = "Today is:", formattedDateString = currentDateFormatted)
        Spacer(modifier = Modifier.size(30.dp))
        // Number of sleeps box
        ShowSleepsContent(numSleepsText = numSleepsText)
        // Next J Day Box
        ShowDateContent(titleText = "Next J Day is:", formattedDateString = jdayDateFormatted)
    }
}

@Composable
fun ShowSleepsContent(numSleepsText: String, modifier: Modifier = Modifier) {

    val infiniteTransition = rememberInfiniteTransition(label = "infinite transition")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Reverse),
        label = "scale"
    )

    Box(
        modifier = modifier
            .height(420.dp)
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.padding(8.dp)
        )
        {
            Text(
                text = "Number of sleeps until J Day:",
                style = MaterialTheme.typography.bodyLarge,
                color = contentColorFor(MaterialTheme.colorScheme.background),
                textAlign = TextAlign.Center,
            )
            Box(
                contentAlignment = Alignment.Center
            )
            {
                Image(
                    painter = painterResource(id = R.drawable.jentrisbeermat),
                    contentDescription = "Jentris Beermat",
                    alpha = 0.3f,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(shape = RoundedCornerShape(8.dp))
                )

                Text(
                    text = numSleepsText,
                    modifier = Modifier
                        .graphicsLayer {
                            scaleX = scale
                            scaleY = scale
                            transformOrigin = TransformOrigin.Center}
                        .align(Alignment.Center),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Composable
fun ShowDateContent(titleText: String, formattedDateString: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .height(100.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = titleText,
                style = MaterialTheme.typography.bodyLarge,
                color = contentColorFor(MaterialTheme.colorScheme.primary),
                textAlign = TextAlign.Center,
            )
            Text(
                text = formattedDateString,
                style = MaterialTheme.typography.headlineMedium,
                color = contentColorFor(MaterialTheme.colorScheme.primary),
                textAlign = TextAlign.Center,
            )
        }
    }
}

