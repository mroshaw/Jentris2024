package com.ollerenshawit.jentris

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.TextStyle
import java.time.temporal.ChronoUnit
import java.util.Locale

/* Returns the next 'first Friday in November' as the day of J */
fun getNextJday(): LocalDate {
    val todayDate = LocalDate.now()

    val firstFridayThisYear = getFirstFridayInNovember(todayDate.year)

    return if(firstFridayThisYear >= todayDate) {
        firstFridayThisYear
    }
    else
    {
        (getFirstFridayInNovember(todayDate.year+1))
    }
}

/* Returns the 'first Friday in November' of the given year */
fun getFirstFridayInNovember(year: Int): LocalDate {
    // Start from the first day of November
    var date = LocalDate.of(year, 11, 1)

    // Find the first Friday
    while (date.dayOfWeek != DayOfWeek.FRIDAY) {
        date = date.plusDays(1)
    }

    print("First Friday: $date")

    return date
}

/* Formats the given date into a 'user friendly' date string */
fun formatLocalDateToString(date: LocalDate): String {
    // Extract parts of the date
    val dayOfWeek = date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
    val dayOfMonth = date.dayOfMonth
    val month = date.month.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
    val year = date.year

    // Determine the suffix for the day of the month
    val daySuffix = getDayOfMonthSuffix(dayOfMonth)

    // Return the formatted string
    return "$dayOfWeek the $dayOfMonth$daySuffix of $month, $year"
}

/* Returns a suffix appropriate to the number given. For example 1st, 2nd, etc */
fun getDayOfMonthSuffix(day: Int): String {
    return when (day) {
        1, 21, 31 -> "st"
        2, 22 -> "nd"
        3, 23 -> "rd"
        else -> "th"
    }
}

/* Returns the number of 'nights' between the two given dates */
fun getSleepsBetweenDates(startDate: LocalDate, endDate: LocalDate): Int {
    // Calculate the number of days between the two dates
    return ChronoUnit.DAYS.between(startDate, endDate).toInt()
}