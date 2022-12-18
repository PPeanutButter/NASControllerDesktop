// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import ui.LeftPanel

@Composable
@Preview
fun App() {
    MaterialTheme {
        Row {
            LeftPanel()
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
