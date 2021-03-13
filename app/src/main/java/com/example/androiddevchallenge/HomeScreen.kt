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

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.androiddevchallenge.ui.theme.ThirdDevChallengeTheme
import com.example.androiddevchallenge.ui.theme.custom1
import com.example.androiddevchallenge.ui.theme.custom2
import com.example.androiddevchallenge.ui.theme.gray900
import com.example.androiddevchallenge.ui.theme.green
import com.example.androiddevchallenge.ui.theme.white
import dev.seniorandroid.thirddevchallenge.util.LocalSysUiController

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen() {
    val scaffoldState = rememberBottomSheetScaffoldState()

    val darkIcons = scaffoldState.bottomSheetState.isCollapsed.not()
    val darkSystem = isSystemInDarkTheme().not()
    LocalSysUiController.current.setStatusBarColor(
        Color.Transparent,
        darkIcons && darkSystem,
    )

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        backgroundColor = MaterialTheme.colors.background,
        sheetShape = RoundedCornerShape(0),
        sheetPeekHeight = 64.dp,
        sheetContent = { PositionScreen(scaffoldState.bottomSheetState.isExpanded) }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .paddingFromBaseline(48.dp)
                ) {
                    TopButton(text = "ACCOUNT", isSelected = true, onClick = { /*TODO*/ })
                    TopButton(text = "WATCHLIST", isSelected = false, onClick = { /*TODO*/ })
                    TopButton(text = "PROFILE", isSelected = false, onClick = { /*TODO*/ })
                }
                Spacer(Modifier.height(8.dp))
                Text(
                    "Balance",
                    modifier = Modifier
                        .paddingFromBaseline(32.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    "$73,589.01",
                    modifier = Modifier
                        .paddingFromBaseline(48.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.h1,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    "+412.35 today",
                    style = MaterialTheme.typography.subtitle1,
                    color = green,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(32.dp))
                TradeButton(
                    text = "TRANSACT",
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { /*TODO*/ }
                )
                Spacer(Modifier.height(16.dp))
            }
            LazyRow(modifier = Modifier.fillMaxWidth()) {

                item {
                    Spacer(Modifier.width(8.dp))
                }
                items(horizontalButtons) { item ->
                    Spacer(Modifier.width(8.dp))
                    TraderOutlinedButton(
                        text = item.name,
                        contentColor = white,
                        style = MaterialTheme.typography.body1,
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier.height(40.dp),
                        onClick = { /*TODO*/ }
                    ) {
                        if (item.drop)
                            Icon(
                                imageVector = Icons.Default.ExpandMore,
                                modifier = Modifier.size(16.dp),
                                tint = white,
                                contentDescription = ""
                            )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_home_illos),
                modifier = Modifier.fillMaxWidth(),
                contentDescription = "",
//                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

data class HB(val name: String, val drop: Boolean = false)

val horizontalButtons = listOf(
    HB("Week", true),
    HB("ETFs"),
    HB("Stocks"),
    HB("Funds"),
    HB("401k"),
    HB("IRA")
)

@Composable
fun TopButton(
    text: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) =
    TextButton(
        colors = ButtonDefaults.textButtonColors(
            contentColor = if (isSelected) white else white.copy(
                alpha = .6f
            )
        ),
        modifier = modifier,
        onClick = onClick
    ) {
        Text(
            text,
            style = MaterialTheme.typography.button,
        )
    }

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PositionScreen(isExpanded: Boolean = true, darkTheme: Boolean = isSystemInDarkTheme()) {
    Scaffold(
        backgroundColor = if (darkTheme) gray900.copy(alpha = .8f) else white,
        topBar = {
//            Column {
//                AnimatedVisibility(isExpanded) { Spacer(Modifier.height(24.dp)) }
                Text(
                    "Positions",
                    modifier = Modifier
                        .fillMaxWidth()
                        .paddingFromBaseline(40.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.subtitle1
                )
//            }
        }
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Spacer(Modifier.height(24.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(positions) { item ->
                    ConstraintLayout(
                        Modifier
                            .height(56.dp)
                            .fillMaxWidth()
                    ) {
                        val (
                            price,
                            percent,
                            tick,
                            name,
                            picture
                        ) = createRefs()

                        Text(
                            "$${item.price}",
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier
                                .paddingFromBaseline(24.dp)
                                .constrainAs(price) {
                                    start.linkTo(parent.start, 8.dp)
                                }
                        )
                        Text(
                            text = item.percentage,
                            color = if (item.numberAdj == numberAdj.POS) custom1 else custom2,
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier
                                .paddingFromBaseline(40.dp)
                                .constrainAs(percent) {
                                    start.linkTo(price.start)
                                }
                        )

                        Text(
                            text = item.ticker,
                            style = MaterialTheme.typography.h3,
                            modifier = Modifier
                                .paddingFromBaseline(24.dp)
                                .constrainAs(tick) {
                                    start.linkTo(parent.start, 72.dp)
                                }
                        )

                        Text(
                            text = item.company,
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier
                                .paddingFromBaseline(40.dp)
                                .constrainAs(name) {
                                    start.linkTo(tick.start)
                                }
                        )

                        Image(
                            imageVector = ImageVector.vectorResource(id = item.id),
                            contentDescription = null,
                            modifier = Modifier.constrainAs(picture) {
                                end.linkTo(parent.end)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(widthDp = 360, heightDp = 640, group = "home", showBackground = true)
@Composable
fun homeprev() {
    ThirdDevChallengeTheme {
        HomeScreen()
    }
}

@Preview(
    widthDp = 360,
    heightDp = 640,
    uiMode = UI_MODE_NIGHT_YES,
    group = "home",
    showBackground = true
)
@Composable
fun darkhomeprev() {
    ThirdDevChallengeTheme(true) {
        HomeScreen()
    }
}

@Preview(
    widthDp = 360,
    heightDp = 640,
    group = "Pos",
    showBackground = true
)
@Composable
fun prevPOs() {
    ThirdDevChallengeTheme {
        PositionScreen()
    }
}

@Preview(
    widthDp = 360,
    heightDp = 640,
    uiMode = UI_MODE_NIGHT_YES,
    group = "Pos",
    showBackground = true
)
@Composable
private fun darkprevPOs() {
    ThirdDevChallengeTheme(darkTheme = true) {
        PositionScreen()
    }
}
