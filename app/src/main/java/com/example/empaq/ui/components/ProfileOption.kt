package com.example.empaq.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.empaq.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileOption(
    modifier: Modifier = Modifier,
    logo: Int,
    name: String,
    onClick: () -> Unit,
    cardShape: Shape = RoundedCornerShape(0.dp)
) {
    Card(
        modifier = Modifier,
        shape = cardShape,
        onClick = { onClick() },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = logo),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .padding(10.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.width(16.dp))
            Text(text = name, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.weight(1f))
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null, modifier = Modifier)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ProfileOptionPreview() {
    Column {
        ProfileOption(logo = R.drawable.ic_language, name = "Settings", onClick = {}, cardShape = RoundedCornerShape(topEnd = 12.dp, topStart = 12.dp))
        ProfileOption(logo = R.drawable.logo_w_empaq_white_text, name = "Pengaturan", onClick = {})
        ProfileOption(logo = R.drawable.logo_b_empaq_black_text, name = "Settings", onClick = {}, cardShape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp))
    }
}