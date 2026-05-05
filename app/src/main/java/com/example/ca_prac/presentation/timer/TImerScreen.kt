package com.example.ca_prac.presentation.timer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ca_prac.domain.model.TimerType

@Composable
fun TimerScreen(
    viewModel: TimerViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = when(uiState.type) {
                TimerType.WORK -> "Attention Mode"
                TimerType.BREAK -> "Short Break Mode"
                TimerType.LONG_BREAK -> "Long Break Mode"
            },
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = uiState.formattedTime,
            fontSize = 80.sp,
            style = MaterialTheme.typography.displayLarge
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row {
            if (uiState.isRunning) {
                Button(onClick = {
                    viewModel.pauseTimer()
                }) {
                    Text(
                        text = "Pause"
                    )
                }

            } else {
                Button(onClick = {
                    viewModel.startTimer()
                }) {
                    Text(
                        text = "Start"
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {viewModel.resetTimer()}
            ) {
                Text(
                    text = "Reset"
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row {
            TextButton(
                onClick = {viewModel.updateTimerType(TimerType.WORK)}
            ) {
                Text(
                    text = "Attention"
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            TextButton(
                onClick = {viewModel.updateTimerType(TimerType.BREAK)}
            ) {
                Text(
                    text = "Short Break"
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            TextButton(
                onClick = {viewModel.updateTimerType(TimerType.LONG_BREAK)}
            ) {
                Text(
                    text = "Long Break"
                )
            }
        }
    }
}