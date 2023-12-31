package com.example.empaq.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.empaq.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeOption(
    modifier: Modifier = Modifier,
    photo: Int,
    photoSize: Dp = 200.dp,
    title: String,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .height(130.dp)
            .width(330.dp),
        shape = RoundedCornerShape(15.dp),
        onClick = { onClick() }
    ) {
        Row {
            Box(
                modifier = Modifier,
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(150.dp)
                        .wrapContentHeight(Alignment.CenterVertically)
                        .align(Alignment.CenterEnd),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 32.sp,
                    lineHeight = 40.sp,
                )

                Image(
                    painter = painterResource(photo),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                )

            }
        }
    }
}

@Preview
@Composable
fun HomeOptionPreview() {
    Column {
        HomeOption(photo = R.drawable.chatbot_photo, title = stringResource(id = R.string.chatbot), photoSize = 200.dp) {}
        Spacer(modifier = Modifier.height(40.dp))
        HomeOption(photo = R.drawable.pakar_ahli_photo, title = "PAKAR AHLI", photoSize = 210.dp, onClick = {})
        Spacer(modifier = Modifier.height(40.dp))
        HomeOption(photo = R.drawable.konselor_photo, title = "Konselor Sebaya", photoSize = 210.dp, onClick = {})
    }
}
