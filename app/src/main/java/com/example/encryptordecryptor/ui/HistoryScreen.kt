package com.example.encryptordecryptor.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.encryptordecryptor.R
import com.example.encryptordecryptor.ui.theme.EncryptorDecryptorTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryCard(modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .padding(top = 8.dp)
        ) {
            Icon(painter = painterResource(R.drawable.baseline_lock_24), contentDescription = null)
            Spacer(Modifier.width(8.dp))
            Surface(shadowElevation = 8.dp) { Text(text = "Encrypted") }
            Spacer(Modifier.width(8.dp))
            Surface(shadowElevation = 8.dp) { Text(text = "RAS") }
            Spacer(Modifier.weight(1f))
            Surface(shadowElevation = 8.dp) { Text(text = "23:07 PM") }
            Spacer(Modifier.width(8.dp))
        }
        Spacer(Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .padding(bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = "Encrypted Message", onValueChange = {},
                singleLine = true,
                readOnly = true,
                enabled = false,
                modifier = Modifier.weight(4.5f),

                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
            Icon(painter = painterResource(R.drawable.baseline_content_copy_24), contentDescription = null)
            Spacer(Modifier.weight(1f))
            Icon(Icons.Rounded.ArrowDropDown, contentDescription = null,
                )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HistoryCardPreview() {
    EncryptorDecryptorTheme {
        HistoryCard()
    }
}

