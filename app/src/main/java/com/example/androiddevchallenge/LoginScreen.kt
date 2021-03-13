/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Password
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.ui.theme.ThirdDevChallengeTheme
import com.example.androiddevchallenge.ui.theme.gray900
import com.example.androiddevchallenge.ui.theme.white

@Composable
fun LoginScreen() {
    val vm: MainViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.login_bg),
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxWidth(),
                    contentDescription = ""
                )
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Spacer(modifier = Modifier.height(40.dp))
                    LoginTextField(
                        text = email,
                        label = "Email address",
                        leadingIcon = Icons.Default.MailOutline,
                        onValueChange = { email = it }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    LoginTextField(
                        text = password,
                        label = "Password",
                        leadingIcon = Icons.Default.Password,
                        visualTransformation = PasswordVisualTransformation(),
                        onValueChange = { password = it }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    TradeButton(
                        text = stringResource(id = R.string.LOG_IN),
                        modifier = Modifier.fillMaxSize(),
                        onClick = {
                            AppAction.takeAction(
                                vm,
                                AppActions.ChangeScreen(Screens.Home)
                            )
                        }
                    )
                }
            }
            Text(
                text = "Welcome\nback",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h2,
                color = Color.White,
                modifier = Modifier
                    .paddingFromBaseline(152.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun LoginTextField(
    text: String,
    label: String,
    leadingIcon: ImageVector,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    contentDescription: String = label,
    onValueChange: (String) -> Unit
) {
    val contentColor = if (isSystemInDarkTheme()) white else gray900
    OutlinedTextField(
        value = text,
        label = {
            Text(label)
        },
        leadingIcon = {
            Image(
                imageVector = leadingIcon,
                contentDescription = contentDescription,
                colorFilter = ColorFilter.tint(contentColor)
            )
        },
        modifier = modifier.fillMaxWidth(),
        visualTransformation = visualTransformation,
        textStyle = MaterialTheme.typography.body1,
        colors =
        TextFieldDefaults.outlinedTextFieldColors(
            textColor = contentColor,
            leadingIconColor = contentColor,
            focusedBorderColor = contentColor,
            unfocusedBorderColor = contentColor,
            disabledBorderColor = contentColor,
            errorBorderColor = MaterialTheme.colors.error,
            focusedLabelColor = contentColor,
            unfocusedLabelColor = contentColor,
            disabledLabelColor = contentColor,
            errorLabelColor = MaterialTheme.colors.error,
            cursorColor = contentColor,
            errorCursorColor = contentColor,

        ),
        onValueChange = onValueChange
    )
}

@Preview(
    widthDp = 360,
    heightDp = 640,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Preview(
    widthDp = 360,
    heightDp = 640,
    showBackground = true
)
@Composable
private fun preview() {
    ThirdDevChallengeTheme {
        LoginScreen()
    }
}
