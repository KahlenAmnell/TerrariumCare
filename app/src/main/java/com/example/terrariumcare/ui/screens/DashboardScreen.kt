package com.example.terrariumcare.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.terrariumcare.data.local.entities.TerrariumEntity
import com.example.terrariumcare.ui.components.AppButton
import com.example.terrariumcare.ui.components.TerrariumCard
import com.example.terrariumcare.ui.theme.TerrariumCareTheme
import com.example.terrariumcare.ui.viewmodel.TerrariumListViewModel

@Composable
fun DashboardScreen(
    viewModel: TerrariumListViewModel,
    onNavigateToAddTerrarium: () -> Unit,
    onTerrariumClick: (Long) -> Unit
) {
    val terrariums by viewModel.terrariums.collectAsState()

    DashboardContent(
        terrariums = terrariums,
        onNavigateToAddTerrarium = onNavigateToAddTerrarium,
        onTerrariumClick = onTerrariumClick
    )
}

@Composable
fun DashboardContent(
    terrariums: List<TerrariumEntity>,
    onNavigateToAddTerrarium: () -> Unit,
    onTerrariumClick: (Long) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Your terrariums",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(16.dp))

            AppButton(
                text = "+ Add terrarium",
                onClick = onNavigateToAddTerrarium
            )

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(terrariums) { terrarium ->
                    TerrariumCard(
                        name = terrarium.name,
                        habitat = terrarium.habitatType,
                        residents = emptyList(),
                        temperature = "24.6 °C",
                        humidity = "76%",
                        onClick = { onTerrariumClick(terrarium.id) },
                        photoUri = terrarium.photoUri
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    val sampleTerrariums = listOf(
        TerrariumEntity(
            id = 1L,
            name = "Terrarium Tropikalne",
            habitatType = "Tropikalne",
            location = "Salon",
            width = 60,
            depth = 45,
            height = 90
        ),
        TerrariumEntity(
            id = 2L,
            name = "Terrarium Pustynne",
            habitatType = "Pustynne",
            location = "Sypialnia",
            width = 90,
            depth = 45,
            height = 45
        )
    )

    TerrariumCareTheme {
        DashboardContent(
            terrariums = sampleTerrariums,
            onNavigateToAddTerrarium = {},
            onTerrariumClick = {}
        )
    }
}
