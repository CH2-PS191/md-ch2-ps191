package com.example.empaq.ui.screen.pakar

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.empaq.data.EmpaqRepository
import com.example.empaq.data.retrofit.ApiConfig
import com.example.empaq.ui.components.PakarAhliOption
import com.example.empaq.ui.screen.ViewModelFactory

val apiService = ApiConfig().getApiService()
@Composable
fun SpecialistScreen(
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val viewModel: SpecialistViewModel = viewModel(factory = ViewModelFactory(EmpaqRepository(ApiConfig().getApiService())))
    val groupedSpecialist by viewModel.groupedSpecialist.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        groupedSpecialist.forEach { (initial, specialists) ->
            items(specialists) {specialist ->
                PakarAhliOption(
                    title = specialist?.displayName.orEmpty(),
                    addPakar = {
                        viewModel.createConversationAndAddSpecialist(specialist?.uid.toString())
                        Toast.makeText(context,"Roomchat Berhasil Dibuat", Toast.LENGTH_SHORT).show()
                    }
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SpecialistPreview() {
//    SpecialistScreen()
}
