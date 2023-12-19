package com.example.empaq.ui.screen.authentication

import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.empaq.R
import com.example.empaq.data.EmpaqRepository
import com.example.empaq.data.retrofit.ApiConfig
import com.example.empaq.ui.components.TabAuthLogin
import com.example.empaq.ui.components.TabAuthRegister
import com.example.empaq.ui.screen.ViewModelFactory
import com.example.empaq.ui.theme.BlueLight
import com.example.empaq.ui.theme.Whitebone
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    finishRegister: () -> Unit,
    navigateToLogin: () -> Unit,
    navigateToRemember: () -> Unit
) {
    val viewModel: AuthViewModel = viewModel(factory = ViewModelFactory(EmpaqRepository(ApiConfig().getApiService())))
//    val conversationId by viewModel.conversationId.collectAsState()

    val context = LocalContext.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var nama by remember { mutableStateOf("") }

    var showPassword by remember { mutableStateOf(false) }

    val forgotPassword = buildAnnotatedString {
        withStyle(style = SpanStyle(Color.Black)) {
            append("Forgot Password? ")
        }
        withStyle(
            style = SpanStyle(
                color = Color.Blue,
                textDecoration = TextDecoration.Underline
            )
        ) {
            append("Remember")
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
            text = "REGISTER",
            modifier = Modifier.padding(top = 100.dp, bottom = 47.dp),
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.37.sp,
        )

        TabAuthRegister()
        Spacer(modifier = Modifier.height(32.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                containerColor = Whitebone,
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email
            ),
        )

        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Password") },
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                containerColor = Whitebone,
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Password
            ),
            trailingIcon = {
                if (showPassword) {
                    IconButton(
                        onClick = { showPassword = false }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Visibility,
                            contentDescription = stringResource(R.string.hide_password)
                        )
                    }
                } else {
                    IconButton(
                        onClick = { showPassword = true }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.VisibilityOff,
                            contentDescription = stringResource(R.string.hide_password)
                        )
                    }
                }
            },
        )

        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = nama,
            onValueChange = { nama = it },
            label = { Text(text = "Nama Lengkap") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                containerColor = Whitebone,
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text
            ),
        )

        Box(
            modifier = Modifier
                .padding(vertical = 32.dp)
                .fillMaxWidth()
                .height(44.dp)
                .clip(RoundedCornerShape(8.dp))
                .clickable {
                    if (email.isEmpty() || password.isEmpty() || nama.isEmpty()) {
                        Toast.makeText(context, "Email, Password, Nama Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()
                    } else {
                        GlobalScope.launch(Dispatchers.Main) {
                            try {
//                                val authResult = FirebaseAuth
//                                    .getInstance()
//                                    .createUserWithEmailAndPassword(email, password).await()
//                                val user = authResult.user
//                                val db = FirebaseFirestore.getInstance()
//                                db.collection("users")
//                                    .document(user?.uid ?: "")
//                                    .set(mapOf("name" to nama))
                                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                                    .addOnCompleteListener { task ->
                                        if (task.isComplete) {
                                            val user = FirebaseAuth.getInstance().currentUser
                                            val profileUpdates = userProfileChangeRequest {
                                                this.displayName = nama
                                            }
                                            user?.updateProfile(profileUpdates)?.addOnCompleteListener { updateTask ->
                                                if (updateTask.isSuccessful) {
                                                    Log.d("UPDATE-REGIST", "REGISTER UPDATE SUCCESS")
                                                } else {
                                                    Log.d("UPDATE-REGIST", "REGISTER UPDATE FAILED")
                                                }
                                            }
                                            // cara untuk fungsi Create Conversation Jalan
                                            val token = user?.getIdToken(false)?.result?.token
                                            user?.sendEmailVerification()?.addOnCompleteListener { verificationTask ->
                                                if (verificationTask.isSuccessful) {
                                                    Log.d("REGISTER", "Email verification sent.")
                                                } else {
                                                    Log.e("REGISTER", "Failed to send email verification: ${verificationTask.exception?.message}")
                                                }
                                            }
                                        } else {
                                            Log.d("REGISTER", "REGISTER FAILED")
                                        }
                                    }.await()

                                val conversationResponse = viewModel.createConversation()
                                if (conversationResponse?.success == true) {
                                    Log.d("CREATE_CONVERSATION", "Conversation created successfully with ID: ${conversationResponse.id}")
                                } else if (conversationResponse?.success == false) {
                                    Log.d("CREATE_CONVERSATION", "Failed to create conversation: ${conversationResponse.message}")
                                } else {
                                    Log.d("CREATE_CONVERSATION", "Unknown error during conversation creation")
                                }

                                Log.d("REGISTER", "REGISTER SUCCESSFULL")
                                finishRegister()
                            } catch (e: Exception) {
                                if (e.message == "The email address is already in use by another account.") {
                                    Toast.makeText(context, "Email Telah Terdaftar", Toast.LENGTH_SHORT).show()
                                } else {
                                    Toast.makeText(context, "Email, Password, Nama Salah", Toast.LENGTH_SHORT).show()
                                }
                                Log.d("REGISTER", "REGISTER FAILED: ${e.message}")
                            }
                        }
                    }
                }
                .background(BlueLight),
        ) {
            Text(
                text = "Sign Up",
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                color = Color.White,
                )
        }

        Text(
            text = forgotPassword,
            modifier = Modifier
                .align(Alignment.Start)
                .clickable { navigateToRemember() }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = signin,
            modifier = Modifier
                .align(Alignment.Start)
                .clickable { navigateToLogin() }
        )
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(finishRegister = {}, navigateToRemember = {}, navigateToLogin = {})
}