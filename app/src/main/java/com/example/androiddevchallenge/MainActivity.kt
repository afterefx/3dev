package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.AppActions.*
import com.example.androiddevchallenge.ui.theme.ThirdDevChallengeTheme
import com.example.androiddevchallenge.ui.theme.buttonElevation
import dev.seniorandroid.thirddevchallenge.util.LocalSysUiController
import dev.seniorandroid.thirddevchallenge.util.SystemUiController
import androidx.lifecycle.viewmodel.compose.viewModel as viewModel

sealed class Screens {
    object Welcome : Screens()
    object Login : Screens()
    object Home : Screens()
}

sealed class AppActions {
    class ChangeScreen(val screen: Screens) : AppActions()
}

val defaultScreen = Screens.Welcome

data class MainViewState(var currentScreen: Screens = defaultScreen)

class MainViewModel : ViewModel() {
    private var _currentScreen: MutableLiveData<Screens> = MutableLiveData(defaultScreen)
    val currentScreen: LiveData<Screens>
        get() = _currentScreen

    private val _decorWindow = MutableLiveData(false)
    val decorWindow: MutableLiveData<Boolean>
        get() = _decorWindow

    fun changeScreen(screen: Screens) {
        _currentScreen.value = screen
    }

    fun toggleDecor() {
        _decorWindow.value = _decorWindow.value?.not() ?: false
    }
}

object AppAction {
    fun takeAction(vm: MainViewModel, action: AppActions) {
        when (action) {
            is ChangeScreen -> {
                vm.changeScreen(action.screen)
            }
        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val systemUiController = remember { SystemUiController(window) }

            val vm: MainViewModel = viewModel()
            val decorWindow by vm.decorWindow.observeAsState(false)
            WindowCompat.setDecorFitsSystemWindows(window, decorWindow)
            CompositionLocalProvider(LocalSysUiController provides systemUiController) {
                WeTradeApp()
            }
        }
    }

}


@Composable
private fun WeTradeApp() {
    val vm: MainViewModel = viewModel()
    val screen by vm.currentScreen.observeAsState(MainViewState())
    when (screen) {
        Screens.Home -> {
            ThirdDevChallengeTheme {
                HomeScreen()
            }
        }
        Screens.Login -> {
            ThirdDevChallengeTheme {
                LoginScreen()
            }
        }
        Screens.Welcome -> {
            ThirdDevChallengeTheme(true) {
                WelcomeScreen()
            }
        }
    }
}

@Composable
fun TradeButton(text: String, modifier: Modifier = Modifier, onClick: () -> Unit) =
    Button(
        modifier = Modifier
            .height(48.dp)
            .then(modifier),
        shape = MaterialTheme.shapes.medium,
        elevation = ButtonDefaults.elevation(buttonElevation, buttonElevation, buttonElevation),
        onClick = onClick
    ) {
        Text(
            text,
            style = MaterialTheme.typography.button
        )
    }

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

