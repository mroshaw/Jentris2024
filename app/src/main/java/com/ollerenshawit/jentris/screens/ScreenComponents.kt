package com.ollerenshawit.jentris.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ShowTitleBox(titleText: String, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(8.dp)),
    )
    {
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = titleText,
            style = MaterialTheme.typography.headlineLarge,
            color = contentColorFor(MaterialTheme.colorScheme.primary),
            textAlign = TextAlign.Center
        )
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



fun createListString(isNumbered: Boolean, items: List<String>): AnnotatedString {
    val bullet = "\u2022"

    val paragraphStyle = ParagraphStyle(textIndent = TextIndent(restLine = 12.sp))
    val annotatedListString =
        buildAnnotatedString {
            items.forEachIndexed() { index, element ->
                withStyle(style = paragraphStyle) {
                    if (isNumbered) {
                        append((index + 1).toString())
                        append(".")
                    } else {
                        append(bullet)
                    }
                    append("\t\t")
                    append(element)
                }
            }
        }
    return annotatedListString
}