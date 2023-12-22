package com.example.empaq.ui.screen.detail

import android.content.Context
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.empaq.R
import com.example.empaq.data.EmpaqRepository
import com.example.empaq.data.pref.UserPreferences
import com.example.empaq.data.retrofit.ApiConfig
import com.example.empaq.model.Gender
import com.example.empaq.model.Profile
import com.example.empaq.ui.screen.ViewModelFactory
import com.example.empaq.ui.theme.BlueLight
import com.example.empaq.ui.theme.Bluedish
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.userProfileChangeRequest
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailProfileScreen(
    modifier: Modifier = Modifier,
    navigateToProfile: () -> Unit
) {
    val viewModel: DetailProfileViewModel = viewModel(factory = ViewModelFactory(EmpaqRepository(ApiConfig().getApiService())))

    var pictureUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val galleryLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { result: Uri? ->
        val uri: Uri? = result
        uri?.let {
            pictureUri = it
            viewModel.updateProfilePicture(getFileFromUri(context, uri))
            Toast.makeText(context, "Foto profil berhasil diperbarui!", Toast.LENGTH_SHORT).show();
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Image(
                painter = rememberImagePainter(data = pictureUri ?: Profile.picture),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 40.dp, bottom = 15.dp)
                    .size(150.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }
        item {
            Text(
                text = stringResource(R.string.edit_profile_picture),
                modifier = Modifier
                    .clickable {
                        galleryLauncher.launch("image/*")
                    },
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = BlueLight,
            )
        }

        item {
            Spacer(modifier = Modifier.height(40.dp))
            ProfileList(
                modifier = Modifier,
                navigateToProfile
            )
        }

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileList(
    modifier: Modifier = Modifier,
    finishUpdateProfile: () -> Unit,
) {
    val context = LocalContext.current
    val user = FirebaseAuth.getInstance().currentUser
    val userPreferences = UserPreferences(context)

    var name by remember { mutableStateOf(user?.displayName) }
    var email by remember { mutableStateOf(user?.email) }

    var expanded by remember { mutableStateOf(false) }
    var selectedGender by remember { mutableStateOf(userPreferences.getUserDetailsLocally().first) }
    val genderOptions = listOf(Gender.MALE, Gender.FEMALE)

    val jk = userPreferences.getUserDetailsLocally().second
    val selectedDates = remember { mutableStateOf(convertTextDate(jk) ) }
    Log.d("DITES", "${userPreferences.getUserDetailsLocally().second ?: "2000-02-02"}")
    val calendarState = rememberUseCaseState()
    CalendarDialog(
        state = calendarState,
        config = CalendarConfig(
            monthSelection = true,
            yearSelection = true,
        ),
        selection = CalendarSelection.Date {date ->
            selectedDates.value = date.toString()
        }
    )

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Card(
        modifier = Modifier
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            ProfileListTitle(R.string.nama_lengkap)
            TextField(
                value = name!!,
                onValueChange = { name = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    containerColor = Color.White,
                    disabledLabelColor = Color.Black
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
            )

            ProfileListTitle(R.string.jenis_kelamin)
            
            Box(modifier = Modifier
                .padding(bottom = 10.dp)
                .clip(RoundedCornerShape(10.dp))
                .clickable { expanded = true }) {
                Row(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth()
                        .height(56.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = selectedGender,
                        modifier = Modifier
                            .padding(start = 15.dp)
                            .weight(1f)
                    )

                    Icon(
                        imageVector = icon,
                        contentDescription = "arrow button",
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .size(40.dp)
                    )
                }
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.BottomEnd)) {
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    genderOptions.forEach { gender ->
                        DropdownMenuItem(
                            text = { Text(text = gender.label) },
                            onClick = {
                                selectedGender =  gender.label
                                expanded = false
                            }
                        )
                    }
                }
            }

            ProfileListTitle(R.string.date_of_birth)

            Box(modifier = Modifier
                .padding(bottom = 10.dp)
                .clip(RoundedCornerShape(10.dp))
                .clickable { calendarState.show() }) {
                Row(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth()
                        .height(56.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = selectedDates.value,
                        modifier = Modifier
                            .padding(start = 15.dp)
                            .weight(1f)
                    )

                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Calendar Button",
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .size(40.dp)
                    )
                }
            }

            ProfileListTitle(R.string.email)
            TextField(
                value = email!!,
                onValueChange = { email = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    containerColor = Color.White,
                    disabledLabelColor = Color.Black
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
            )

        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Button(onClick = {
            updateProfile(
                name = name.toString(),
                email = email.toString(),
                jenisKelamin = selectedGender,
                tanggalLahir = selectedDates.toString(),
                context = context
            )
            Toast.makeText(context, "DATA UPDATE SUCCESSFULL", Toast.LENGTH_SHORT).show()
            finishUpdateProfile()
        }, modifier = Modifier.align(Alignment.BottomCenter),
            colors = ButtonDefaults.buttonColors(
                containerColor = Bluedish
            )
        ) {
            Text(text = "UPDATE")
        }
    }
}

private fun updateProfile(
    name: String,
    email: String,
    jenisKelamin: String,
    tanggalLahir: String,
    context: Context,
) {
    val user = FirebaseAuth.getInstance().currentUser

    val profileUpdate = userProfileChangeRequest {
        this.displayName = name
    }
    user?.updateProfile(profileUpdate)?.addOnCompleteListener { updateTask ->
        if (updateTask.isSuccessful) {
            Log.d("DETAIL-PROFILE", "ACCOUNT UPDATE SUCCESS")
        } else {
            Log.d("DETAIL_PROFILE", "ACCOUNT UPDATE FAILED")
        }
    }

    user!!.updateEmail(email)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("DETAIL-PROFILE", "EMAIL UPDATE SUCCESS")
            } else {
                Log.d("DETAIL-PROFILE", "EMAIL UPDATE FAILED")
            }
        }

    val userPreferences = UserPreferences(context)
    userPreferences.saveUserDetailsLocally(jenisKelamin, tanggalLahir)
}

fun convertTextDate(Text: String): String {
    val inputText = Text
    val regex = Regex("=([^)]+)")

    val matchResult = regex.find(inputText)
    return matchResult?.groups?.get(1)?.value.toString()
}
fun getFileFromUri(context: Context, uri: Uri): File {
    val contentResolver = context.contentResolver
    val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    val fileName = "IMG_$timeStamp.jpg"

    val file = File(context.cacheDir, fileName)

    try {
        val inputStream = contentResolver.openInputStream(uri)
        val outputStream = FileOutputStream(file)
        inputStream?.copyTo(outputStream)
        inputStream?.close()
        outputStream.close()
    } catch (e: IOException) {
        e.printStackTrace()
    }

    return file
}

@Composable
fun ProfileListTitle(
    title: Int = R.string.title,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = title),
        modifier = Modifier.padding(bottom = 8.dp),
        fontSize = 12.sp
    )
}