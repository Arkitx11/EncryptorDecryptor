package com.example.encryptordecryptor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.encryptordecryptor.ui.theme.EncryptorDecryptorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            EncryptorDecryptorPotraitApp()
        }
    }
}


@Composable
fun OptionToggler(modifier: Modifier = Modifier) {
    var value by remember { mutableStateOf(false) }
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Encryptor"
        )
        Switch(checked = value,
            onCheckedChange = { value = !value })
        Text(
            text = "Decryptor"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OptionTogglerPreview() {
    EncryptorDecryptorTheme {
        OptionToggler()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EncryptionSelector(modifier: Modifier = Modifier) {
    var selectedIndex by remember { mutableIntStateOf(1) }
    val options = listOf("ROT13", "AES", "RSA")
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(8.dp)
    ) {
        SingleChoiceSegmentedButtonRow {
            options.forEachIndexed { index, label ->
                SegmentedButton(
                    shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size),
                    onClick = { selectedIndex = index },
                    selected = index == selectedIndex
                ) {
                    Text(label)
                }
            }
        }
        Spacer(Modifier.width(16.dp))
        if (selectedIndex != 0)
            Icon(
                painter = painterResource(R.drawable.baseline_settings_24),
                contentDescription = ""
            )
    }
}

@Preview(showBackground = true)
@Composable
fun EncryptionSelectorPreview(modifier: Modifier = Modifier) {
    EncryptorDecryptorTheme { EncryptionSelector() }
}

@Composable
fun EncryptedMessageField(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }
    TextField(value = text,
        onValueChange = { text = it },
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.baseline_lock_24),
                contentDescription = ""
            )
        },
        singleLine = true,
        label = { Text(text = "Encrypted Message") },
        trailingIcon = {
            Icon(
                painter = painterResource(R.drawable.baseline_content_paste_24),
                contentDescription = ""
            )
        }
    )
}

@Composable
fun DecryptedMessageField(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }
    TextField(value = text,
        onValueChange = { text = it },
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.baseline_lock_open_24),
                contentDescription = ""
            )
        },
        readOnly = true,
        singleLine = true,
        label = { Text(text = "Decrypted Message") },
        trailingIcon = {
            Icon(
                painter = painterResource(R.drawable.baseline_content_copy_24),
                contentDescription = ""
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun EncryptedMessageFieldPreview() {
    EncryptorDecryptorTheme { EncryptedMessageField() }
}

@Preview(showBackground = true)
@Composable
fun DecryptedMessageFieldPreview() {
    EncryptorDecryptorTheme { DecryptedMessageField() }
}

@Composable
fun InputField(modifier: Modifier = Modifier) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        EncryptedMessageField()
        Spacer(Modifier.padding(4.dp))
        EncryptionSelector(Modifier.padding(start = 32.dp))
        Spacer(Modifier.padding(4.dp))
        DecryptedMessageField()
    }
}

@Preview(showBackground = true)
@Composable
fun InputFieldPreview() {
    EncryptorDecryptorTheme { InputField() }
}

@Composable
fun EncryptorDecryptorHomeScreen(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        OptionToggler()
        Spacer(Modifier.padding(8.dp))
        InputField()
    }
}

@Composable
fun EncryptorDecryptorAppLandscape() {
    EncryptorDecryptorTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Row {
                EncryptorDecryptorNavigationRail()
                EncryptorDecryptorHomeScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EncryptorDecryptorAppLandscapePreview() {
    EncryptorDecryptorAppLandscape()
}

@Preview(showBackground = true)
@Composable
fun EncryptorDecryptorHomeScreenPreview() {
    EncryptorDecryptorTheme {
        EncryptorDecryptorHomeScreen()
    }
}

@Composable
fun EncryptorDecryptorPotraitApp() {
    EncryptorDecryptorTheme {
        Scaffold(bottomBar = { BottomNavigation() }) { padding ->
            EncryptorDecryptorHomeScreen(
                Modifier
                    .padding(padding)
                    .fillMaxSize()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EncryptorDecryptorAppPreview() {
    EncryptorDecryptorPotraitApp()

}

@Composable
fun BottomNavigation(modifier: Modifier = Modifier) {
    NavigationBar(
        modifier = modifier
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.home_label)
                )
            },
            selected = true,
            onClick = {}
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(R.drawable.baseline_history_24),
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.history)
                )
            },
            selected = false,
            onClick = {}
        )
    }
}

@Composable
fun EncryptorDecryptorNavigationRail(modifier: Modifier = Modifier) {
    NavigationRail {
        Column(
            modifier = modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = stringResource(R.string.home_label)
                    )
                },
                selected = true,
                onClick = {}
            )

            NavigationRailItem(
                icon = {
                    Icon(
                        painter = painterResource(R.drawable.baseline_history_24),
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = stringResource(R.string.history)
                    )
                },
                selected = false,
                onClick = {}
            )
        }
    }
}

@Preview
@Composable
fun EncryptorDecryptorNavigationRailPreview() {
    EncryptorDecryptorTheme {
        EncryptorDecryptorNavigationRail()
    }
}

