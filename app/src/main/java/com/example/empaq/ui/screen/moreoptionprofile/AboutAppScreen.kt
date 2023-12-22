package com.example.empaq.ui.screen.moreoptionprofile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.empaq.R
import com.example.empaq.ui.theme.bluefeather

@Preview(showSystemUi = true)
@Composable
fun AboutAppScreen() {
    Column {
        Image(
            painter = painterResource(id = R.drawable.logo_b_empaq_black_text),
            contentDescription = "logo",
            modifier = Modifier.padding(top = 40.dp, start = 10.dp, end = 10.dp, bottom = 10.dp)
        )

        Text(
            text = "EMPAQ is an app that utilize Artificial Intelligence to provide a chatbot for people to talk to. Our main goal is to help prevent suicide by offering a supportive space for conversations.",
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(20.dp)
                .background(color = bluefeather, shape = RoundedCornerShape(10.dp)).padding(15.dp),
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium
        )
    }
}