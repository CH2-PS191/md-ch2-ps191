package com.example.empaq.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TabAuthLogin(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .height(32.dp)
            .background(Color.LightGray, RoundedCornerShape(9.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Register",
            modifier = Modifier
                .wrapContentWidth(Alignment.CenterHorizontally)
                .weight(1f),
            fontSize = 13.sp,
        )

        Card(
            modifier = Modifier
                .padding(2.dp)
                .background(Color.White, RoundedCornerShape(9.dp))
                .weight(1f)
                .wrapContentWidth(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Login",
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .wrapContentHeight(Alignment.CenterVertically)
                    .wrapContentWidth(Alignment.CenterHorizontally),
                fontSize = 13.sp,
                fontWeight = FontWeight.Thin,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@Composable
fun TabAuthRegister(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .height(32.dp)
            .background(Color.LightGray, RoundedCornerShape(9.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            modifier = Modifier
                .padding(2.dp)
                .background(Color.White, RoundedCornerShape(9.dp))
                .weight(1f)
                .wrapContentWidth(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Register",
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .wrapContentHeight(Alignment.CenterVertically)
                    .wrapContentWidth(Alignment.CenterHorizontally),
                fontSize = 13.sp,
                fontWeight = FontWeight.Thin,
                fontStyle = FontStyle.Italic
            )
        }

        Text(
            text = "Login",
            modifier = Modifier
                .wrapContentWidth(Alignment.CenterHorizontally)
                .weight(1f),
            fontSize = 13.sp,
        )

    }
}

@Composable
fun TabAuthForgetPassword(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .height(32.dp)
            .background(Color.LightGray, RoundedCornerShape(9.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            modifier = Modifier
                .padding(2.dp)
                .background(Color.White, RoundedCornerShape(9.dp))
                .weight(1f)
                .wrapContentWidth(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Verifikasi Email",
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .wrapContentHeight(Alignment.CenterVertically)
                    .wrapContentWidth(Alignment.CenterHorizontally),
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                fontStyle = FontStyle.Italic
            )
        }

    }
}

@Preview
@Composable
fun TabAuthPreview() {
    TabAuthForgetPassword()
}