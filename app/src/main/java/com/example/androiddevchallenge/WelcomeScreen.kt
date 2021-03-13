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
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonDefaults.OutlinedBorderSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.ui.theme.ThirdDevChallengeTheme
import com.example.androiddevchallenge.ui.theme.buttonElevation
import com.example.androiddevchallenge.ui.theme.yellow

@Composable
fun WelcomeScreen() {
    val vm: MainViewModel = viewModel()

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
//            imageVector = ImageVector.vectorResource(id = R.drawable.welcome_bg),
            painter = painterResource(id = R.drawable.welcome_bg),
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.fillMaxSize(),
            contentDescription = ""
        )
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (logo, buttonRow) = createRefs()

            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.logo),
                contentDescription = "WeTrade",
                modifier = Modifier.constrainAs(logo) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
            )

            ConstraintLayout(
                modifier = Modifier
                    .constrainAs(buttonRow) {
                        start.linkTo(parent.start, 8.dp)
                        end.linkTo(parent.end, 8.dp)
                        bottom.linkTo(parent.bottom, 32.dp)
                    }
                    .height(48.dp)
                    .fillMaxWidth()
            ) {
                val (getStarted, loginButton) = createRefs()
                TradeButton(
                    text =
                    stringResource(R.string.GET_STARTED),
                    modifier = Modifier
                        .constrainAs(getStarted) {
                            start.linkTo(parent.start)
                            end.linkTo(loginButton.start)
                            top.linkTo(parent.top)
                        }
                        .width(176.dp),
                    onClick = { TODO("figure out where to go when get started is clicked") }
                )

                TraderOutlinedButton(
                    text = stringResource(R.string.LOG_IN),
                    modifier = Modifier
                        .height(48.dp)
                        .width(176.dp)
                        .constrainAs(loginButton) {
                            start.linkTo(getStarted.end, 8.dp)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                        },
                    contentColor = yellow,
                    onClick =
                    {
                        AppAction.takeAction(
                            vm,
                            AppActions.ChangeScreen(Screens.Login)
                        )
                    }
                )
            }
        }
    }
}

// }
@Composable
fun TraderOutlinedButton(
    text: String,
    contentColor: Color,
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.medium,
    style: TextStyle = MaterialTheme.typography.button,
    onClick: () -> Unit,
    content: @Composable () -> Unit = {}
) {
    OutlinedButton(
        colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
        border = BorderStroke(
            OutlinedBorderSize,
            contentColor
        ),
        modifier = modifier,
        elevation = ButtonDefaults.elevation(buttonElevation, buttonElevation, buttonElevation),
        shape = shape,
        onClick = onClick
    ) {
        Text(
            text,
            color = contentColor,
            style = style
        )
        content()
    }
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
private fun myPreview() {
    ThirdDevChallengeTheme() {
        WelcomeScreen()
    }
}
