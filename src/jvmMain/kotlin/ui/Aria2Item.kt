package ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.Aria2DownloadState
import data.Aria2Task

@Composable
fun Aria2Item(task: Aria2Task, onSelected: ((isSelected: Boolean, task: Aria2Task) -> Unit)? = null) {
    val state = task.state
    Box {
        var selected by remember { mutableStateOf(false) }
        LaunchedEffect(key1 = selected, block = {
            if (onSelected != null)
                onSelected(selected, task)
        })
        Checkbox(
            checked = selected, onCheckedChange = { selected = !selected }, modifier = Modifier
                .wrapContentSize()
                .clickable(interactionSource = MutableInteractionSource(),
                    indication = null, onClick = {})
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {  }
                .padding(start = 12.dp, end = 12.dp, top = 10.dp, bottom = 12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { selected = !selected },
                text = task.files[0].getFileName(),
                fontSize = 20.sp,
                maxLines = 2,
                lineHeight = 24.sp,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    textIndent = TextIndent(31.sp)
                )
            )
            if (state == Aria2DownloadState.ACTIVE){
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(painter = painterResource("downloading_black_24dp.svg"), contentDescription = null, modifier = Modifier.size(18.dp))
                    Text(text = "${task.downloadSize} / ${task.totalSize}")
                    Icon(painter = painterResource("timelapse_black_24dp.svg"), contentDescription = null, modifier = Modifier.size(18.dp))
                    Text(text = task.remainTimeDesc)
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                when (state) {
                    Aria2DownloadState.COMPLETE -> {
                        Icon(painter = painterResource("download_for_offline_black_24dp.svg"), contentDescription = null, modifier = Modifier.size(18.dp))
                        Text(text = task.totalSize)
                    }
                    Aria2DownloadState.ERROR -> {
                        Icon(painter = painterResource("error_black_24dp.svg"), tint = MaterialTheme.colors.error, contentDescription = null, modifier = Modifier.size(18.dp))
                        Text(text = task.errorMessage?:task.errorCodeMessage, color = MaterialTheme.colors.error)
                    }
                    Aria2DownloadState.WAITING -> {
                        Icon(painter = painterResource("schedule_black_24dp.svg"), contentDescription = null, modifier = Modifier.size(18.dp))
                    }
                    Aria2DownloadState.PAUSED -> {
                        Icon(painter = painterResource("pause_circle_outline_black_24dp.svg"), contentDescription = null, modifier = Modifier.size(18.dp))
                    }
                    else -> {}
                }
                ProgressBar(height = 26.dp, progress = task.downloadPercent, state = state)
            }
            //https://fonts.google.com/icons?icon.style=Rounded&icon.query=download
            if (state == Aria2DownloadState.ACTIVE) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(painter = painterResource("speed_black_24dp.svg"), contentDescription = null, modifier = Modifier.size(18.dp))
                    Text(text = task.downloadSpeedDesc)
                    Icon(painter = painterResource("signal_cellular_alt_black_24dp.svg"), contentDescription = null, modifier = Modifier.size(18.dp))
                    Text(text = task.connections)
                }
            }
        }
    }
}

@Composable
fun ProgressBar(height: Dp = 8.dp, progress: Float, state: Aria2DownloadState) {
    var mProgress = progress
    val progressColor = when(state){
        Aria2DownloadState.ACTIVE -> MaterialTheme.colors.primary
        Aria2DownloadState.ERROR -> MaterialTheme.colors.error
        Aria2DownloadState.COMPLETE -> theme.Color.aria2_download_success.also { mProgress = 1f }
        else -> Color.Gray
    }
    val backgroundColor = progressColor.copy(alpha = 0.15f)
    val corner = 16f
    Box(modifier = Modifier.fillMaxWidth()) {
        Surface(color = backgroundColor,
            shape = MaterialTheme.shapes.large.copy(all = CornerSize(corner)),
            modifier = Modifier
                .fillMaxWidth()
                .height(height)) {
            // fillMaxWidth(fraction = mProgress) doesn`t work somehow
        }
        Surface(color = progressColor,
            shape = MaterialTheme.shapes.large.copy(all = CornerSize(corner)),
            modifier = Modifier
                .fillMaxWidth(fraction = mProgress)
                .height(height)) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {
                Text(text = String.format("%.2f %%", mProgress * 100), maxLines = 1, overflow = TextOverflow.Clip, color = MaterialTheme.colors.onPrimary)
            }
        }
    }
}