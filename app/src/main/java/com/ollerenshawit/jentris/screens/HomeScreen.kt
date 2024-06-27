package com.ollerenshawit.jentris.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
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

    SleepsUntilJentris(currentDateFormatted = currentDateFormatted,
        jdayDateFormatted = jdayDateFormatted,
        numSleepsText = numSleepsText,
        modifier = modifier
    )
}

@Composable
fun SleepsUntilJentris(currentDateFormatted: String, jdayDateFormatted: String, numSleepsText: String, modifier: Modifier = Modifier) {
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(8.dp)
    ){
        Text(
            text = "Today is:",
            fontSize = 20.sp,
            lineHeight = 30.sp,
            textAlign = TextAlign.Center,
        )
        Text(
            text = currentDateFormatted,
            fontSize = 24.sp,
            lineHeight = 30.sp,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.size(30.dp))
        Text(
            text = "Next J Day is:",
            fontSize = 20.sp,
            lineHeight = 30.sp,
            textAlign = TextAlign.Center,
        )
        Text(
            text = jdayDateFormatted,
            fontSize = 24.sp,
            lineHeight = 30.sp,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.size(30.dp))
        Text(
            text = "Number of sleeps until J Day:",
            fontSize = 20.sp,
            lineHeight = 30.sp,
            textAlign = TextAlign.Center,
        )
        Text (
            text = numSleepsText,
            lineHeight = 160.sp,
            fontSize = 160.sp,
            textAlign = TextAlign.Center,
        )
    }
}