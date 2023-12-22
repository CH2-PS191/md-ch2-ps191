package com.example.empaq.ui.screen.authentication

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.empaq.ui.components.TabAuthForgetPassword
import com.example.empaq.ui.theme.BlueLight
import com.example.empaq.ui.theme.Whitebone
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(
    modifier: Modifier = Modifier,
    navigateToRegister: () -> Unit,
    navigateToLogin: () -> Unit,
) {
    var email by remember { mutableStateOf("") }

    val register = buildAnnotatedString {
        withStyle(style = SpanStyle(Color.Black)) {
            append("Belum Punya Akun? ")
        }
        withStyle(
            style = SpanStyle(
                color = Color.Blue,
                textDecoration = TextDecoration.Underline
            )
        ) {
            append("Register")
        }
    }

    val signin = buildAnnotatedString {
        withStyle(style = SpanStyle(Color.Black)) {
            append("Sudah Punya Akun? ")
        }
        withStyle(
            style = SpanStyle(
                color = Color.Blue,
                textDecoration = TextDecoration.Underline
            )
        ) {
            append("Login")
        }
    }

    Column(
        modifier = Modifier.padding(horizontal = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "FORGOT PASSWORD",
            modifier = Modifier.padding(top = 100.dp, bottom = 47.dp),
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.37.sp,
            lineHeight = 41.sp
        )

        TabAuthForgetPassword()
        Spacer(modifier = Modifier.height(32.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                containerColor = Whitebone,
            ),
        )

        Box(
            modifier = Modifier
                .padding(vertical = 32.dp)
                .fillMaxWidth()
                .height(44.dp)
                .clip(RoundedCornerShape(8.dp))
                .clickable {
                    try {
                        FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Log.d("REMEMBER", "REMEMBER PASSWORD SUCCESSFUL")
                            } else {
                                Log.d("REMEMBER", "REMEMBER PASSWORD FAILED")
                            }
                        }
                    } catch (e: Exception) {
                        Log.d("REMEMBER", "${e.message}")
                    }
                }
                .background(BlueLight),
        ) {
            Text(
                text = "Reset Password",
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                color = Color.White,

                )
        }

        Text(
            text = register,
            modifier = Modifier
                .align(Alignment.Start)
                .clickable {
                    navigateToRegister()
                }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = signin,
            modifier = Modifier
                .align(Alignment.Start)
                .clickable {
                    navigateToLogin()
                }
        )
    }
}