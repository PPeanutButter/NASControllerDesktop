package screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import model.SharedViewModel
import ui.RepeatRequest

@Composable
fun Aria2Screen(sharedViewModel: SharedViewModel){
    Column(modifier = Modifier.padding(16.dp).fillMaxSize()) {
        Aria2Header()
        Spacer(Modifier.height(16.dp))
        RepeatRequest(sharedViewModel)
    }
}

@Composable
fun Aria2Header(version: String = "1.36.0", uploadSpeed: String = "0 KB/s", downloadSpeed: String = "0 KB/s"){
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
        Text(modifier = Modifier.weight(1f), text = "Yet Another Aria2 Web Frontend", fontSize = 26.sp, maxLines = 1, overflow = TextOverflow.Ellipsis, fontWeight = FontWeight.Bold)
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Aria2 $version")
            Row(verticalAlignment = Alignment.CenterVertically){
                Icon(painter = painterResource("download_for_offline_black_24dp.svg"), contentDescription = null, modifier = Modifier.padding(end = 8.dp).size(16.dp))
                Text(text = uploadSpeed)
                Text(text = " / ")
                Icon(painter = painterResource("download_for_offline_black_24dp.svg"), contentDescription = null, modifier = Modifier.padding(end = 8.dp).size(16.dp).rotate(180f))
                Text(text = downloadSpeed)
            }
        }
    }
}