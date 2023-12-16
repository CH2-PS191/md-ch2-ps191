package com.example.empaq.ui.screen.specialist

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.empaq.data.EmpaqRepository
import com.example.empaq.ui.components.PakarAhliOption
import com.example.empaq.ui.screen.ViewModelFactory

//@Composable
//fun SpecialistScreen(
//    modifier: Modifier = Modifier,
//    viewModel: SpecialistViewModel = viewModel(factory = ViewModelFactory(EmpaqRepository())),
//) {
//    val groupedSpecialist by viewModel.groupedSpecialist.collectAsState()
//
//    LazyColumn(
//        modifier = Modifier.fillMaxSize().padding(top = 20.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        groupedSpecialist.forEach { (initial, specialists) ->
//            items(specialists) {specialist ->
//                PakarAhliOption(title = specialist.nama)
//                Spacer(modifier = Modifier.height(20.dp))
//            }
//        }
//    }
//}
//
//@Preview(showSystemUi = true)
//@Composable
//fun SpecialistPreview() {
//    SpecialistScreen()
//}
