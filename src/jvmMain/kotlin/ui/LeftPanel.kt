package ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun LeftPanel(selected: Int, onClick: (Int) -> Unit) {
    var showActionTitle by remember { mutableStateOf(true) }
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxHeight().wrapContentWidth().background(theme.Color.leftPanelBackground)
    ) {
        Column {
            Aria2Actions(id = 0, showActionTitle, select = selected) {
                onClick(it)
            }
            ChangeNotificationActions(id = 1, showActionTitle, select = selected) {
                onClick(it)
            }
            NASActions(id = 2, showActionTitle, select = selected) {
                onClick(it)
            }
            SystemInfoActions(id = 3, showActionTitle, select = selected) {
                onClick(it)
            }
            FileManagerActions(id = 4, showActionTitle, select = selected) {
                onClick(it)
            }
            SettingsActions(id = 5, showActionTitle, select = selected) {
                onClick(it)
            }
        }
        HideTitleActions(showActionTitle) {
            showActionTitle = !showActionTitle
        }
    }

}

@Composable
fun Actions(
    iconPath: String,
    title: String,
    showText: Boolean,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.clickable { onClick() }
            .padding(16.dp)
    ) {
        if (selected)
            Icon(
                painter = painterResource(iconPath), contentDescription = title,
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colors.primary
            )
        else Icon(
            painter = painterResource(iconPath), contentDescription = title,
            modifier = Modifier.size(24.dp)
        )
        AnimatedVisibility(showText) {
            Text(
                text = title,
                modifier = Modifier.padding(horizontal = 16.dp).width(150.dp),
                color = if (selected) MaterialTheme.colors.primary else Color.Unspecified
            )
        }
    }
}

@Composable
fun Aria2Actions(id: Int, showText: Boolean = true, select: Int, onClick: (Int) -> Unit) {
    Actions(
        iconPath = "download_for_offline_black_24dp.svg",
        title = "Aria2 Web",
        showText = showText,
        selected = select == id,
    ) {
        onClick(id)
    }
}

@Composable
fun ChangeNotificationActions(id: Int, showText: Boolean = true, select: Int, onClick: (Int) -> Unit) {
    Actions(
        iconPath = "notifications_active_black_24dp.svg",
        title = "Change Notification",
        showText = showText,
        selected = select == id,
    ) {
        onClick(id)
    }
}

@Composable
fun FileManagerActions(id: Int, showText: Boolean = true, select: Int, onClick: (Int) -> Unit) {
    Actions(
        iconPath = "folder_black_24dp.svg",
        title = "File Manager",
        showText = showText,
        selected = select == id,
    ) {
        onClick(id)
    }
}

@Composable
fun SystemInfoActions(id: Int, showText: Boolean = true, select: Int, onClick: (Int) -> Unit) {
    Actions(
        iconPath = "info_black_24dp.svg",
        title = "Server Status",
        showText = showText,
        selected = select == id,
    ) {
        onClick(id)
    }
}

@Composable
fun NASActions(id: Int, showText: Boolean = true, select: Int, onClick: (Int) -> Unit) {
    Actions(
        iconPath = "play_circle_black_24dp.svg",
        title = "Peanut NAS",
        showText = showText,
        selected = select == id,
    ) {
        onClick(id)
    }
}

@Composable
fun SettingsActions(id: Int, showText: Boolean = true, select: Int, onClick: (Int) -> Unit) {
    Actions(
        iconPath = "settings_suggest_black_24dp.svg",
        title = "Settings",
        showText = showText,
        selected = select == id,
    ) {
        onClick(id)
    }
}

@Composable
fun HideTitleActions(showText: Boolean = true, onClick: () -> Unit) {
    Actions(
        iconPath = if (showText) "visibility_off_black_24dp.svg" else "visibility_black_24dp.svg",
        title = "",
        showText = false,
        selected = false
    ) {
        onClick()
    }
}