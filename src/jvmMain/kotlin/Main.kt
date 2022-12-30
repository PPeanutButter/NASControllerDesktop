// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import model.SharedViewModel
import screens.Aria2Screen
import ui.LeftPanel
import ui.NotImplement

@Composable
@Preview
fun App() {
    val viewModel by remember { mutableStateOf(SharedViewModel()) }
    MaterialTheme {
        Row {
            var selected by remember { mutableStateOf(0) }
            LeftPanel(selected){
                selected = it
            }
            Surface(modifier = Modifier.weight(1f).fillMaxHeight()){
                when(selected){
                    0 -> Aria2Screen(sharedViewModel = viewModel)
                    else -> NotImplement()
                }
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication,
        title = "花生酱的NAS后台",
        state = rememberWindowState(position = WindowPosition.Aligned(Alignment.Center),
            height = 800.dp, width = 1200.dp)) {
        App()
    }
}
