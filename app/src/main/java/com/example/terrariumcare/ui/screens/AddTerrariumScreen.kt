package com.example.terrariumcare.ui.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.terrariumcare.ui.components.AppButton
import com.example.terrariumcare.ui.components.AppDropdownField
import com.example.terrariumcare.ui.components.AppTextField
import com.example.terrariumcare.ui.theme.TerrariumCareTheme
import com.example.terrariumcare.ui.viewmodel.AddTerrariumViewModel

@Composable
fun AddTerrariumScreen(
    viewModel: AddTerrariumViewModel,
    onNavigateBack: () -> Unit
) {
    val name by viewModel.name.collectAsState()
    val habitatType by viewModel.habitatType.collectAsState()
    val location by viewModel.location.collectAsState()
    val width by viewModel.width.collectAsState()
    val depth by viewModel.depth.collectAsState()
    val height by viewModel.height.collectAsState()
    val isError by viewModel.isError.collectAsState()
    val photoUri by viewModel.photoUri.collectAsState()

    AddTerrariumContent(
        name = name,
        habitatType = habitatType,
        location = location,
        width = width,
        depth = depth,
        height = height,
        photoUri = photoUri,
        isError = isError,
        onNameChange = viewModel::onNameChange,
        onHabitatTypeChange = viewModel::onHabitatTypeChange,
        onLocationChange = viewModel::onLocationChange,
        onWidthChange = viewModel::onWidthChange,
        onDepthChange = viewModel::onDepthChange,
        onHeightChange = viewModel::onHeightChange,
        onPhotoUriChange = viewModel::onPhotoUriChange,
        onSaveClick = {
            viewModel.saveTerrarium {
                onNavigateBack()
            }
        }
    )
}

@Composable
fun AddTerrariumContent(
    name: String,
    habitatType: String,
    location: String,
    width: String,
    depth: String,
    height: String,
    photoUri: String?,
    isError: Boolean,
    onNameChange: (String) -> Unit,
    onHabitatTypeChange: (String) -> Unit,
    onLocationChange: (String) -> Unit,
    onWidthChange: (String) -> Unit,
    onDepthChange: (String) -> Unit,
    onHeightChange: (String) -> Unit,
    onPhotoUriChange: (String?) -> Unit,
    onSaveClick: () -> Unit
) {
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            onPhotoUriChange(uri?.toString())
        }
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "ADD TERRARIUM",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(24.dp))
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                        .clickable {
                            photoPickerLauncher.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        },
                    contentAlignment = Alignment.Center
                ) {
                    if (!photoUri.isNullOrBlank()) {
                        AsyncImage(
                            model = photoUri,
                            contentDescription = "Terrariumphoto",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Dodaj zdjęcie",
                                tint = MaterialTheme.colorScheme.onSecondaryContainer,
                                modifier = Modifier.size(36.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Add terrarium photo",
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        }
                    }
                }
            }

            item {
                AppTextField(
                    value = name,
                    onValueChange = onNameChange,
                    label = "Terrarium name",
                    placeholder = "Rainforest retreat",
                    isError = isError,
                    errorMessage = if (isError) "Name cannot be empty" else null
                )
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        AppDropdownField(
                            selectedValue = habitatType,
                            options = listOf("Tropical", "Arid", "Temperate", "Paludarium", "Other"),
                            label = "Habitat type",
                            onValueChanged = onHabitatTypeChange
                        )
                    }
                    Box(modifier = Modifier.weight(1f)) {
                        AppTextField(
                            value = location,
                            onValueChange = onLocationChange,
                            label = "Location",
                            placeholder = "Study",
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Place,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        )
                    }
                }
            }

            item {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "Dimensions",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(modifier = Modifier.weight(1f)) {
                            AppTextField(
                                value = width,
                                onValueChange = onWidthChange,
                                label = "Width",
                                trailingText = "cm",
                                singleLine = true
                            )
                        }
                        Box(modifier = Modifier.weight(1f)) {
                            AppTextField(
                                value = depth,
                                onValueChange = onDepthChange,
                                label = "Depth",
                                trailingText = "cm",
                                singleLine = true
                            )
                        }
                        Box(modifier = Modifier.weight(1f)) {
                            AppTextField(
                                value = height,
                                onValueChange = onHeightChange,
                                label = "Height",
                                trailingText = "cm",
                                singleLine = true
                            )
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                AppButton(
                    text = "Create terrarium",
                    onClick = onSaveClick
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddTerrariumScreenPreview() {
    TerrariumCareTheme {
        AddTerrariumContent(
            name = "Rainforest retreat",
            habitatType = "Tropical",
            location = "Study",
            width = "60",
            depth = "45",
            height = "60",
            photoUri = null,
            isError = false,
            onNameChange = {},
            onHabitatTypeChange = {},
            onLocationChange = {},
            onWidthChange = {},
            onDepthChange = {},
            onHeightChange = {},
            onPhotoUriChange = {},
            onSaveClick = {}
        )
    }
}
