@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
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
import kotlinx.coroutines.delay
import java.time.LocalDate

@Composable
fun HomeScreen(navController: NavController) {
    JentrisTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ShowMainContent(
                modifier = Modifier
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    JentrisTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            ShowMainContent(
                modifier = Modifier
            )
        }
    }
}

@Composable
fun ShowMainContent(
    modifier: Modifier = Modifier
) {

    var currentDateFormatted by remember {
        mutableStateOf(formatLocalDateToString(LocalDate.now()))
    }

    var jdayDateFormatted by remember {
        mutableStateOf(formatLocalDateToString(getNextJday()))
    }

    var numSleeps by remember {
        mutableIntStateOf(getSleepsBetweenDates(LocalDate.now(), getNextJday()))
    }

    val pullRefreshState = rememberPullToRefreshState()
    if (pullRefreshState.isRefreshing) {
        LaunchedEffect(true) {
            delay(1000)
            currentDateFormatted = formatLocalDateToString(LocalDate.now())
            jdayDateFormatted = formatLocalDateToString(getNextJday())
            numSleeps = getSleepsBetweenDates(LocalDate.now(), getNextJday())
            pullRefreshState.endRefresh()
        }
    }

    Box(Modifier.nestedScroll(pullRefreshState.nestedScrollConnection)) {

        LazyColumn(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.padding(8.dp)
        ) {
            item {
                // Today's date box
                ShowDateContent(titleText = "Today is:", formattedDateString = currentDateFormatted)
                Spacer(modifier = Modifier.size(30.dp))
                // Number of sleeps box
                ShowSleepsContent(numSleeps = numSleeps)
                // Next J Day Box
                ShowDateContent(
                    titleText = "Next J Day is:",
                    formattedDateString = jdayDateFormatted
                )
            }
        }
        if (pullRefreshState.progress > 0 || pullRefreshState.isRefreshing) {
            PullToRefreshContainer(
                modifier = Modifier.align(Alignment.TopCenter),
                state = pullRefreshState,
            )
        }
    }
}

@Composable
fun ShowSleepsContent(numSleeps: Int, modifier: Modifier = Modifier) {

    val isTesting = false
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
                // If numSleeps is 0, then it's J DAY!
                if (numSleeps == 0 || isTesting) {
                    MultiColorSmoothText(
                        duration = 1200,
                        text = "Happy\nJ Day!",
                        modifier = Modifier
                            .graphicsLayer {
                                scaleX = scale
                                scaleY = scale
                                transformOrigin = TransformOrigin.Center
                            }
                            //.fillMaxWidth()
                            .align(Alignment.Center),
                        style = MaterialTheme.typography.titleMedium,
                    )
                    /*
                    Text(
                        text = "Happy\nJ Day!",
                        modifier = Modifier
                            .graphicsLayer {
                                scaleX = scale
                                scaleY = scale
                                transformOrigin = TransformOrigin.Center
                            }
                            .align(Alignment.Center),
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                    )
                     */
                } else {
                    Text(
                        text = numSleeps.toString(),
                        modifier = Modifier
                            .graphicsLayer {
                                scaleX = scale
                                scaleY = scale
                                transformOrigin = TransformOrigin.Center
                            }
                            .align(Alignment.Center),
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}


