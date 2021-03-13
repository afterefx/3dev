package com.example.androiddevchallenge.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = FontFamily(Font(resId = R.font.montserrat_bold)),//, weight = FontWeight.ExtraBold)),
        fontWeight = FontWeight.ExtraBold,
        fontSize = 40.sp,
        letterSpacing = 1.25.sp
    ),
    h2 = TextStyle(
        fontFamily = FontFamily(Font(resId = R.font.montserrat_bold)),//, weight = FontWeight.ExtraBold)),
        fontWeight = FontWeight.ExtraBold,
        fontSize = 36.sp,
        letterSpacing = 0.sp
    ),
    h3 = TextStyle(
        fontFamily = FontFamily(Font(resId = R.font.montserrat_bold)),//, weight = FontWeight.ExtraBold)),
        fontWeight = FontWeight.SemiBold,
        fontSize = 13.sp,
        letterSpacing = 0.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = FontFamily(Font(resId = R.font.montserrat)),//, weight = FontWeight.ExtraBold)),
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp,
        letterSpacing = 0.sp
    ),
    body1 = TextStyle(
        fontFamily = FontFamily(Font(resId = R.font.montserrat)),//, weight = FontWeight.ExtraBold)),
        fontWeight = FontWeight.Light,
        fontSize = 13.sp,
        letterSpacing = 0.sp
    ),
    button = TextStyle(
        fontFamily = FontFamily(Font(resId = R.font.montserrat_bold)),//, weight = FontWeight.ExtraBold)),
        fontWeight = FontWeight.Bold,
        fontSize = 13.sp,
        letterSpacing = 1.25.sp
    ),

    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)