package com.example.heuredepriere

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.TextField


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                MyScreenContent()
            }
            //setContentView(R.layout.activity_main)
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(R.string.app_name)) }
                )
            },
            content = content
        )
    }
}

@Composable
fun MyScreenContent() {

    //definir ici la liste des alarm (depuis la base de donnée)
    var alarmList by remember {
        mutableStateOf(
            listOf(
                Alarm(id = 1, time = "08:00"),
                Alarm(id = 2, time = "12:00"),
                Alarm(id = 3, time = "18:00")
            )
        )
    }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = "Liste des alarmes")
        AlarmList(alarms = alarmList)
        AddAlarmButton(onAddAlarm = { time ->
            alarmList = alarmList + Alarm(id = alarmList.size + 1, time = time)
        })
        // TODO : Ajouter un bouton pour supprimer une alarme
        // TODO : Ajouter un bouton pour modifier une alarme
    }
}

@Composable
fun AlarmList(alarms: List<Alarm>) {
    LazyColumn {
        items(alarms) { alarm ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text(text = alarm.time, modifier = Modifier.weight(1f))
                // TODO : Ajouter un bouton pour supprimer cette alarme
                // TODO : Ajouter un bouton pour modifier cette alarme
            }
        }
    }
}

@Composable
fun AddAlarmButton(onAddAlarm: (String) -> Unit) {
    var showDialog by remember { mutableStateOf(false) }
    var time by remember { mutableStateOf("") }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Nouvelle alarme") },
            text = {
                TextField(
                    value = time,
                    onValueChange = { time = it },
                    label = { Text(text = "Heure de l'alarme") }
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        onAddAlarm(time)
                        showDialog = false
                    }
                ) {
                    Text(text = "Ajouter")
                }
            },
            dismissButton = {
                Button(
                    onClick = { showDialog = false }
                ) {
                    Text(text = "Annuler")
                }
            }
        )
    }

    Button(onClick = { showDialog = true }) {
        Text(text = "Ajouter une alarme")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
        MyScreenContent()
    }
}

