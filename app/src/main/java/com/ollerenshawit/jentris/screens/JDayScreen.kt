package com.ollerenshawit.jentris.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ollerenshawit.jentris.R
import com.ollerenshawit.jentris.ui.theme.JentrisTheme

@Composable
fun JDayScreen(navController: NavController) {
    JentrisTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ShowJDayContent()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JDayScreenPreview() {
    JentrisTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            ShowJDayContent()
        }
    }
}

@Composable
fun ShowJDayContent() {
    val uriHandler = LocalUriHandler.current

    val jdaybold = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append("J Day")
        }
    }

    val jentrisBoysBold = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append("Jentris Boys")
        }
    }

    val jentrisdaybold = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append("Jentris Day")
        }
    }

    val goalsList = listOf(
        "Drink",
        "That is all"
    )

    val agendaList = listOf(
        "Golf on The Balgove",
        "Beers and lunch at a venue of your choosing",
        "Drinks at Hams Hame",
        "Eat rubbish soup and steak",
        "Awards and general abuse",
        "Oblivion"
    )

    Column(
        modifier = Modifier.padding(16.dp)
            .verticalScroll(rememberScrollState())) {
        Text(text = buildAnnotatedString {
            append("Jentris Day is a celebration of the underlying goals of the ")
            append(jentrisBoysBold)
            append(":\n")
            append(CreateListString(false, goalsList))
            append("\nIt is also commonly known as ")
            append(jdaybold)
            append(".\nIn keeping with tradition, ")
            append(jentrisdaybold)
            append(" occurs each year on the first Friday of November.\n\nThe day pans out as follows:\n")
            append(CreateListString(true, agendaList))
            append("\n")
        })
        Image(
            painter = painterResource(id = R.drawable.jentris),
            contentDescription = "Jentris Boys 1",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(8.dp))
        )
    }
}
