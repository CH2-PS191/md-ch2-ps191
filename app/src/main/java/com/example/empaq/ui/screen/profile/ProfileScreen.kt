package com.example.empaq.ui.screen.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.empaq.R
import com.example.empaq.model.ProfileDummy
import com.example.empaq.ui.components.ProfileOption
import org.intellij.lang.annotations.Language

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: () -> Unit
) {
    Column(
        modifier = Modifier.padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(30.dp))
        ProfileCard(photo = ProfileDummy.picture, name = ProfileDummy.name, onClick = navigateToDetail)
        Spacer(modifier = Modifier.height(25.dp))

        ProfileOption(
            modifier = Modifier,
            logo = R.drawable.ic_logout,
            name = stringResource(R.string.logout),
            cardShape = RoundedCornerShape(12.dp),
            onClick = { /*TODO*/ }

        )

        Text(
            text = stringResource(R.string.preferences),
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 35.dp, bottom = 15.dp)
        )

        ProfileOption(
            logo = R.drawable.ic_language,
            name = stringResource(R.string.language),
            cardShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
            onClick = { /*TODO*/ }
        )
        ProfileOption(
            logo = R.drawable.ic_darkmode,
            name = stringResource(R.string.dark_mode),
            cardShape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp),
            onClick = { /*TODO*/ }
        )

        Text(
            text = stringResource(R.string.more),
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 35.dp, bottom = 15.dp)
        )

        ProfileOption(
            logo = R.drawable.ic_bell,
            name = stringResource(R.string.help_support),
            cardShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
            onClick = { /*TODO*/ }
        )
        ProfileOption(
            logo = R.drawable.ic_about,
            name = stringResource(R.string.about_app),
            cardShape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp),
            onClick = { /*TODO*/ }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileCard(
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = photo,
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))
            Text(text = name, fontWeight = FontWeight.Medium, fontSize = 24.sp)

            Spacer(modifier = Modifier.weight(1f))
            Icon(imageVector = Icons.Default.Edit, contentDescription = null, modifier = Modifier.size(24.dp))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(navigateToDetail = {})
}
