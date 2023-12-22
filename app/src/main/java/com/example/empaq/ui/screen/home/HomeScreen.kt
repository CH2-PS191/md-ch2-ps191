package com.example.empaq.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.empaq.model.Profile
import com.example.empaq.ui.components.HomeOption
import com.example.empaq.R
import com.google.firebase.auth.FirebaseAuth


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToProfile: () -> Unit,
    navigateToChatbot: () -> Unit,
    navigateToSpecialist: () -> Unit,
    navigateToKonselor: () -> Unit,
    navigateToLogin: () -> Unit,
) {
    var user = FirebaseAuth.getInstance().currentUser

    if (user == null) {
        navigateToLogin()
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(modifier = Modifier.height(40.dp))
            ProfileCardTwo(photo = user?.photoUrl.toString(), name = user?.displayName.toString(), modifier = Modifier.padding(20.dp), onClick = navigateToProfile)
            Spacer(modifier = Modifier.height(50.dp))
        }

        item {
            HomeOption(photo = R.drawable.chatbot_photo, title = stringResource(id = R.string.chatbot), onClick = navigateToChatbot)
            Spacer(modifier = Modifier.height(40.dp))
        }

        item {
            HomeOption(photo = R.drawable.konselor_photo, title = stringResource(R.string.konselor_sebaya), onClick = navigateToKonselor)
            Spacer(modifier = Modifier.height(40.dp))
        }

        item {
            HomeOption(photo = R.drawable.pakar_ahli_photo, title = stringResource(R.string.pakar_ahli), onClick = navigateToSpecialist)
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileCardTwo(
    modifier: Modifier = Modifier,
    photo: String,
    name: String,
    onClick: () -> Unit,
    ) {
    Card(
        modifier = Modifier
            .width(300.dp)
            .shadow(elevation = 8.dp, RoundedCornerShape(15.dp))
            .clip(RoundedCornerShape(15.dp)),
        onClick = { onClick() }
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
    HomeScreen(navigateToProfile = { /*TODO*/ }, navigateToChatbot = { /*TODO*/ }, navigateToKonselor = {}, navigateToSpecialist = {}, navigateToLogin = {})
}