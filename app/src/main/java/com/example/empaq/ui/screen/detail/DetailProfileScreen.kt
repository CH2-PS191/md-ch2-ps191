package com.example.empaq.ui.screen.detail

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.empaq.R
import com.example.empaq.model.Gender
import com.example.empaq.model.ProfileDummy
import com.example.empaq.ui.theme.BlueLight
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar
import java.util.Date
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailProfileScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ProfileDummy.picture,
            contentDescription = null,
            modifier = Modifier
                .padding(top = 40.dp, bottom = 15.dp)
                .size(150.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Text(
            text = stringResource(R.string.edit_profile_picture),
            modifier = Modifier,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = BlueLight,
        )

        Spacer(modifier = Modifier.height(40.dp))
        ProfileList()

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileList(
    modifier: Modifier = Modifier,
) {
    var name by remember { mutableStateOf(ProfileDummy.name) }
    var email by remember { mutableStateOf(ProfileDummy.email) }

    var expanded by remember { mutableStateOf(false) }
    var selectedGender by remember { mutableStateOf(ProfileDummy.gender) }
    val genderOptions = listOf(Gender.MALE, Gender.FEMALE)

    val selectedDates = remember { mutableStateOf(ProfileDummy.dateOfBirth) }
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

//    var selectedDate by remember { mutableStateOf(Calendar.getInstance()) }
//
//    var isCalendarDialogVisible by remember { mutableStateOf(false) }

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
                value = name,
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

//            Box(modifier = Modifier
//                .padding(bottom = 10.dp)
//                .clip(RoundedCornerShape(10.dp))
//                ) {
//                Row(
//                    modifier = Modifier
//                        .background(Color.White)
//                        .fillMaxWidth()
//                        .height(56.dp),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Text(
//                        text = selectedGender,
//                        modifier = Modifier
//                            .padding(start = 15.dp)
//                            .weight(1f)
//                    )
//
//                    Icon(
//                        imageVector = Icons.Default.DateRange,
//                        contentDescription = "open date",
//                        modifier = Modifier
//                            .padding(end = 10.dp)
//                            .size(40.dp)
//                            .clickable {
//                            }
//                    )
//                }
//            }

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

//            TextField(
//                value = selectedDates.toString(),
//                onValueChange = {},
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 10.dp),
//                shape = RoundedCornerShape(10.dp),
//                colors = TextFieldDefaults.textFieldColors(
//                    cursorColor = Color.Black,
//                    focusedIndicatorColor = Color.Transparent,
//                    unfocusedIndicatorColor = Color.Transparent,
//                    containerColor = Color.White,
//                    disabledLabelColor = Color.Black
//                ),
//                keyboardOptions = KeyboardOptions.Default.copy(
//                    imeAction = ImeAction.Done
//                ),
//                trailingIcon = {
//                    Icon(
//                        imageVector = Icons.Default.DateRange,
//                        contentDescription = "Calendar",
//                        modifier = Modifier
//                            .size(40.dp)
//                            .padding(end = 10.dp)
//                            .clickable {
//                                calendarState.show()
//                            }
//                    )
//                }
//            )

//            if (isCalendarDialogVisible) {
//                CalendarSample1 {
//                    isCalendarDialogVisible = false
//                }
//            }

            ProfileListTitle(R.string.email)
            TextField(
                value = email,
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
        Button(onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.BottomCenter)) {
            Text(text = "UPDATE")
        }
    }
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