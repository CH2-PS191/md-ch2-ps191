package com.example.empaq.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.empaq.ui.theme.Bluedish
import com.example.empaq.ui.theme.Purple40

@Composable
fun PakarAhliOption(
    modifier: Modifier = Modifier,
    title: String,
    addPakar: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(328.dp)
            .height(136.dp)
            .clip(RoundedCornerShape(15.dp))
            .border(1.dp, Color.LightGray, RoundedCornerShape(15.dp)) // Tambahkan border di sini

    ) {
        Column(
            modifier = Modifier.padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                modifier = Modifier.padding(top = 10.dp, bottom = 15.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )

            PakarAhliButton(
                text = "Join Sekarang",
                modifier = Modifier
            ) {
                addPakar()
            }
        }
    }
}

@Composable
fun PakarAhliButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .semantics(mergeDescendants = true) {
                contentDescription = "Join Button"
            },
        colors = ButtonDefaults.buttonColors(
            containerColor = Bluedish
        )

    ) {
        Text(
            text = text,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun PakarAhliOptionPreview() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        PakarAhliOption(title = "PAKAR AHLI 1", addPakar = {})
        Spacer(modifier = Modifier.height(20.dp))
        PakarAhliOption(title = "PAKAR AHLI 2", addPakar = {})
    }
}