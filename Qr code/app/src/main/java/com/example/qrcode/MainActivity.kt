package com.example.qrcode

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.qrcode.ui.theme.QrCodeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    lateinit var barCodeScanner: BarCodeScanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        barCodeScanner = BarCodeScanner(this)

        enableEdgeToEdge()
        setContent {
            QrCodeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    var valueScanned by remember {
                        mutableStateOf("")
                    }

                    val scope = rememberCoroutineScope()

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(35.dp)
                    ) {
                        Text(
                            text = valueScanned, modifier = Modifier.align(Alignment.Center)
                        )

                        Button(
                            onClick = {
                                scope.launch {
                                    valueScanned = barCodeScanner.startScan().toString()
                                }
                            },
                            modifier = Modifier.align(Alignment.BottomCenter)
                        ) {

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    QrCodeTheme {
        Greeting("Android")
    }
}