package com.example.ca_prac

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.ca_prac.presentation.timer.TimerScreen
import com.example.ca_prac.presentation.timer.TimerViewModel
import com.example.ca_prac.ui.theme.Ca_pracTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ca_pracTheme {
                val viewModel: TimerViewModel = hiltViewModel()
                TimerScreen(viewModel = viewModel)
            }
        }
    }
}
