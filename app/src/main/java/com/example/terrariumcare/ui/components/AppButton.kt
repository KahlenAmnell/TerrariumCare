package com.example.terrariumcare.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.terrariumcare.ui.theme.TerrariumCareTheme

@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(54.dp),
        enabled = enabled,
        shape = RoundedCornerShape(28.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)
        )
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppButtonPreview() {
    TerrariumCareTheme {
        AppButton(
            text = "Zapisz",
            onClick = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}
