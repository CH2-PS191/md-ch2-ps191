package com.example.empaq.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.empaq.model.ProfileDummy
import com.example.empaq.ui.components.HomeOption
import com.example.empaq.R


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        ProfileCardTwo(photo = ProfileDummy.picture, name = ProfileDummy.name, modifier = Modifier.padding(20.dp))
        Spacer(modifier = Modifier.height(50.dp))

        HomeOption(photo = R.drawable.chatbot_photo, title = stringResource(id = R.string.chatbot)) {}
        Spacer(modifier = Modifier.height(40.dp))
        HomeOption(photo = R.drawable.pakar_ahli_photo, title = stringResource(R.string.pakar_ahli), photoSize = 220.dp) {}
    }
}

@Composable
fun ProfileCardTwo(
    modifier: Modifier = Modifier,
    photo: String,
    name: String,

    ) {
    Card(
        modifier = Modifier
            .width(300.dp)
            .shadow(elevation = 8.dp, RoundedCornerShape(15.dp))
            .clip(RoundedCornerShape(15.dp)),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AsyncImage(
                    model = photo,
                    contentDescription = null,
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .fillMaxWidth()
                        .background(Color.Gray),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(16.dp))
                Text(text = name, fontWeight = FontWeight.Medium, fontSize = 24.sp)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}