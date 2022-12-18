package ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.Aria2Task
import kotlinx.coroutines.launch
import model.RequestStore
import model.SharedViewModel

@Composable
fun RepeatRequest(sharedViewModel: SharedViewModel) {
    val scope = rememberCoroutineScope()
    LaunchedEffect(true){
        scope.launch {
            sharedViewModel.getAllAria2Tasks()
        }
    }
    Column {
        ActiveTasks {
            val r = sharedViewModel.activeTasks.value
            if (r is RequestStore.Success) {
                AnimatedVisibility(r._datas.size > 0){
                    LazyColumn {
                        items(items = r._datas, key = { task: Aria2Task -> task.gid.hashCode() }) {
                            Aria2Item(task = it, onSelected = { _, _ -> })
                        }
                    }
                }
                AnimatedVisibility(r._datas.size == 0){
                    NoActiveTasks()
                }
            }
        }
        OtherTasks {
            val other = sharedViewModel.otherTasks.value
            if (other is RequestStore.Success) {
                AnimatedVisibility(other._datas.size > 0) {
                    LazyColumn {
                        items(items = other._datas, key = { task: Aria2Task -> task.gid.hashCode() }) {
                            Aria2Item(task = it, onSelected = { _, _ -> })
                        }
                    }
                }
                AnimatedVisibility(other._datas.size == 0){
                    NoActiveTasks()
                }
            }
        }
    }

}

@Composable
fun ActiveTasks(content: @Composable () -> Unit) {
    Column {
        Text(text = "Active Tasks", fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        SeparateLine()
        content()
        SeparateLine()
    }
}

@Composable
fun NoActiveTasks(){
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier =
    Modifier.clickable {  }.fillMaxWidth()) {
        Text(text = "No Active Tasks", fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(8.dp))
    }

}

@Composable
fun SeparateLine(){
    Spacer(modifier = Modifier
        .height(1.dp).fillMaxWidth().background(Color.Gray))
}

@Composable
fun OtherTasks(content: @Composable () -> Unit) {
    Column {
        Spacer(Modifier.height(8.dp))
        Text(text = "Other Tasks", fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        SeparateLine()
        content()
    }
}

@Preview
@Composable
fun ActiveTasksPreview() {
    ActiveTasks {

    }
}
