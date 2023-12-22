package com.example.empaq.ui.screen.profile

import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import coil.compose.AsyncImage
import com.example.empaq.R
import com.example.empaq.model.Profile
import com.example.empaq.ui.components.ProfileOption
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: () -> Unit,
    logout: () -> Unit,
    helpAndSupport: () -> Unit,
    aboutApp: () -> Unit,
) {
    val user = FirebaseAuth.getInstance().currentUser
    var showLogoutDialog by remember { mutableStateOf(false) }

    if (showLogoutDialog) {
        LogoutConfirmationDialog(
            onConfirm = {
                // Perform logout
                FirebaseAuth.getInstance().signOut()
                logout()
                Log.d("LOGOUT", "LOGOUT SUCCESSFULL")

                // Dismiss the dialog
                showLogoutDialog = false
            },
            onDismiss = {
                // Dismiss the dialog
                showLogoutDialog = false
            }
        )
    }

    LazyColumn(
        modifier = Modifier.padding(start = 30.dp, end = 30.dp, bottom = 1.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(modifier = Modifier.height(30.dp))
            ProfileCard(photo = user?.photoUrl.toString(), name = user?.displayName.toString(), onClick = navigateToDetail)
            Spacer(modifier = Modifier.height(25.dp))
        }

        item {
            ProfileOption(
                modifier = Modifier,
                logo = R.drawable.ic_logout,
                name = stringResource(R.string.logout),
                cardShape = RoundedCornerShape(12.dp),
                onClick = {
                    showLogoutDialog = true
                }

            )
        }

        item {
            PreferencesContent()
        }

        item {
            MoreContent(navigateToHelpSupport = helpAndSupport, navigateToAbout = aboutApp)

            Spacer(modifier = modifier.height(10.dp))
        }

    }

//    Column(
//        modifier = Modifier.padding(horizontal = 30.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//
//        Spacer(modifier = Modifier.height(30.dp))
//        ProfileCard(photo = ProfileDummy.picture, name = ProfileDummy.name, onClick = navigateToDetail)
//        Spacer(modifier = Modifier.height(25.dp))
//
//        ProfileOption(
//            modifier = Modifier,
//            logo = R.drawable.ic_logout,
//            name = stringResource(R.string.logout),
//            cardShape = RoundedCornerShape(12.dp),
//            onClick = { /*TODO*/ }
//
//        )
//
//        Text(
//            text = stringResource(R.string.preferences),
//            fontWeight = FontWeight.SemiBold,
//            fontSize = 18.sp,
//            modifier = Modifier
//                .align(Alignment.Start)
//                .padding(top = 35.dp, bottom = 15.dp)
//        )
//
//        ProfileOption(
//            logo = R.drawable.ic_language,
//            name = stringResource(R.string.language),
//            cardShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
//            onClick = { /*TODO*/ }
//        )
//        ProfileOption(
//            logo = R.drawable.ic_darkmode,
//            name = stringResource(R.string.dark_mode),
//            cardShape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp),
//            onClick = { /*TODO*/ }
//        )
//
//        Text(
//            text = stringResource(R.string.more),
//            fontWeight = FontWeight.SemiBold,
//            fontSize = 18.sp,
//            modifier = Modifier
//                .align(Alignment.Start)
//                .padding(top = 35.dp, bottom = 15.dp)
//        )
//
//        ProfileOption(
//            logo = R.drawable.ic_bell,
//            name = stringResource(R.string.help_support),
//            cardShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
//            onClick = { /*TODO*/ }
//        )
//        ProfileOption(
//            logo = R.drawable.ic_about,
//            name = stringResource(R.string.about_app),
//            cardShape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp),
//            onClick = { /*TODO*/ }
//        )
//
//        Spacer(modifier = modifier.height(50.dp))
//    }
}

@Composable
fun LogoutConfirmationDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = {
            onDismiss()
        },
        title = {
            Text(text = "Logout", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        },
        text = {
            Column {
                Text(
                    text = "Are you sure you want to logout?",
                    modifier = Modifier.padding(5.dp),
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 15.sp),
                )
                Spacer(modifier = Modifier.height(15.dp))
                // Additional content for the dialog if needed
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onConfirm()
                }
            ) {
                Text("Yes")
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    onDismiss()
                }
            ) {
                Text("No")
            }
        }
    )
}

@Composable
fun PreferencesContent() {
    val context = LocalContext.current

    Text(
        text = stringResource(R.string.preferences),
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 35.dp, bottom = 15.dp)
    )

    ProfileOption(
        logo = R.drawable.ic_language,
        name = stringResource(R.string.language),
        cardShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        onClick = { openLanguageSettings(context) }
    )
    ProfileOption(
        logo = R.drawable.ic_darkmode,
        name = stringResource(R.string.dark_mode),
        cardShape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp),
        onClick = { openDarkModeSettings(context) }
    )
}
private fun openLanguageSettings(context: Context) {
    val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
    intent.resolveActivity(context.packageManager)?.let {
        startActivity(context, intent, null)
    }
}
private fun openDarkModeSettings(context: Context) {
    val intent = Intent(Settings.ACTION_NIGHT_DISPLAY_SETTINGS)
    intent.resolveActivity(context.packageManager)?.let {
        startActivity(context, intent, null)
    }
}

@Composable
fun MoreContent(
    navigateToHelpSupport: () -> Unit,
    navigateToAbout: () -> Unit,
) {
    Text(
        text = stringResource(R.string.more),
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 35.dp, bottom = 15.dp)
    )

    ProfileOption(
        logo = R.drawable.ic_bell,
        name = stringResource(R.string.help_support),
        cardShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        onClick = { navigateToHelpSupport() }
    )
    ProfileOption(
        logo = R.drawable.ic_about,
        name = stringResource(R.string.about_app),
        cardShape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp),
        onClick = { navigateToAbout() }
    )
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
                    .clip(CircleShape)
                    .background(Color.Gray),
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
    ProfileScreen(navigateToDetail = {}, logout = {}, helpAndSupport = {}, aboutApp = {})
}
