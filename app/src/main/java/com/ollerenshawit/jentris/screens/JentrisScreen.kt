package com.ollerenshawit.jentris.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ollerenshawit.jentris.R
import com.ollerenshawit.jentris.ui.theme.JentrisTheme

@Composable
fun JentrisScreen(navController: NavController) {
    JentrisTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ShowAboutJentrisContent()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JentrisScreenPreview() {
    JentrisTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            ShowAboutJentrisContent()
        }
    }
}

@Composable
fun ShowAboutJentrisContent() {

    val jBoysList = listOf(
        "Alan 'Flett' Flett",
        "Ewan 'Fergus' Ferguson",
        "Iain 'Oli' Ollerenshaw",
        "John 'Fenny' Ferry",
        "Richard 'Ric' Cockbain",
        "Neil 'Collie' Collie"
    )

    val uriHandler = LocalUriHandler.current
    val stAndrewsUrl = buildAnnotatedString {
        pushStringAnnotation(tag = "URL", annotation = "http://en.wikipedia.org/wiki/St_Andrews")
        withStyle(
            style = SpanStyle(
                color = Color.Blue,
                textDecoration = TextDecoration.Underline
            )
        ) {
            append("St Andrews")
        }
        pop()
    }

    val jentrisBold = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append("Jentris")
        }
    }

    val jentrisBoysBold = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append("Jentris Boys")
        }
    }

    val stAndrewsBold = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append("St Andrews")
        }
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        ShowTitleBox("About Jentris")
        Spacer(modifier = Modifier.size(20.dp))
        Text(
            text = buildAnnotatedString
            {
                append("The year was 1995, the town ")
                append(stAndrewsBold)
                append(", Scotland. Six young undergraduates from across the UK met one fateful day in ")
                append(jentrisBold)
                append(" on South Street, for a Friday afternoon drink:\n")
                append(createListString(items = jBoysList, isNumbered = false))
                append("\nAnd so the ")
                append(jentrisBoysBold)
                append(" were formed and to this very day their goal to bring peace and unity to drinkers across the world remains true.")
                append("\n\nTheir mission: earn their PhD's (Professional Heavy Drinker) and rid the world of the demon booze by consuming it all themselves.\n")
            }
        )
        Image(
            painter = painterResource(id = R.drawable.jentris2),
            contentScale = ContentScale.FillWidth,
            contentDescription = "Jentris Boys 2",
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(8.dp))
        )
    }
}

