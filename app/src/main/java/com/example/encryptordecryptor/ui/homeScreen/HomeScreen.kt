package com.example.encryptordecryptor.ui.homeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.encryptordecryptor.R
import com.example.encryptordecryptor.ui.homeScreen.model.HomeScreenModel


@Composable
fun OptionToggler(
    modifier: Modifier = Modifier,
    optionTogglerState: Boolean,
    onPress: (Boolean) -> Unit
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Encryptor"
        )
        Switch(checked = optionTogglerState,
            onCheckedChange = { onPress(it) })
        Text(
            text = "Decryptor"
        )
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


@Composable
fun EncryptedMessageField(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }
    TextField(value = text,
        modifier = modifier,
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
        modifier = modifier,
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


@Composable
fun InputField(
    modifier: Modifier = Modifier,
    homeScreenState: HomeScreenState,
    homeScreenModel: HomeScreenModel
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        EncryptedMessageField()
        Spacer(Modifier.padding(4.dp))
        EncryptionSelector(Modifier.padding(start = 32.dp))
        Spacer(Modifier.padding(4.dp))
        DecryptedMessageField()
    }
}


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeScreenModel: HomeScreenModel = HomeScreenModel()
) {
    val homeScreenState by homeScreenModel.homeScreenState.collectAsState()
    Box(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        OptionToggler(
            Modifier
                .align(Alignment.Center)
                .offset(y = (-128).dp),
            optionTogglerState = homeScreenState.optionToggler,
            onPress = homeScreenModel::onPressingOptionToggler
        )
        InputField(Modifier.align(Alignment.Center), homeScreenState, homeScreenModel)


    }
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

