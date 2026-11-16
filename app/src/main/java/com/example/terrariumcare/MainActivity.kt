package com.example.terrariumcare

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.terrariumcare.ui.screens.MainContainerScreen
import com.example.terrariumcare.ui.theme.TerrariumCareTheme
import com.example.terrariumcare.ui.viewmodel.AddTerrariumViewModel
import com.example.terrariumcare.ui.viewmodel.TerrariumListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val terrariumListViewModel: TerrariumListViewModel by viewModels()
    private val addTerrariumViewModel: AddTerrariumViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TerrariumCareTheme {
                MainContainerScreen(
                    terrariumListViewModel = terrariumListViewModel,
                    addTerrariumViewModel = addTerrariumViewModel
                )
            }
        }
    }
}