package com.example.encryptordecryptor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.encryptordecryptor.ui.homeScreen.BottomNavigation
import com.example.encryptordecryptor.ui.homeScreen.EncryptorDecryptorNavigationRail
import com.example.encryptordecryptor.ui.homeScreen.HomeScreen
import com.example.encryptordecryptor.ui.theme.EncryptorDecryptorTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EncryptorDecryptorTheme { EncryptorDecryptorApp() }

        }
    }
}

@Composable
fun EncryptorDecryptorApp() {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    when (windowSizeClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> EncryptorDecryptorAppPotrait()
        WindowWidthSizeClass.EXPANDED -> EncryptorDecryptorAppLandscape()
    }
}

@Composable
fun EncryptorDecryptorAppPotrait() {
    Scaffold(bottomBar = { BottomNavigation() }) { HomeScreen(Modifier.padding(it)) }
}

@Composable
fun EncryptorDecryptorAppLandscape(modifier: Modifier = Modifier) {
    Surface {
        Row(Modifier.fillMaxSize()) {
            EncryptorDecryptorNavigationRail(Modifier.weight(1f))
            HomeScreen(Modifier.weight(2f))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AppPotraitPreview() {
    EncryptorDecryptorAppPotrait()
}

