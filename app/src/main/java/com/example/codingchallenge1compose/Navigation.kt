package com.example.codingchallenge1compose

import android.text.TextUtils
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavHostController


@Composable
fun Screen1(navControler: NavHostController) {
    val context = LocalContext.current
    CreateNumberBox(stringResource(R.string.tvHintNum1)) { number ->
        if (TextUtils.isEmpty(number)) Utils.showError(context)
        else {
            navControler.navigate(Routes.Pantalla2.createRoute(number.toInt()))
        }
    }
}

@Composable
fun Screen2(navControler: NavHostController, number1: Int) {
    val context = LocalContext.current
    CreateNumberBox(stringResource(R.string.tvHintNum2)) { number ->
        if (TextUtils.isEmpty(number)) {
            Utils.showError(context)
        } else {
            navControler.popBackStack()
            navControler.navigate(Routes.Pantalla3.createRoute(number1, number.toInt()))
        }
    }
}

@Composable
fun Screen3(navControler: NavHostController, number1: Int, number2: Int) {
    ConstraintLayout(
        modifier = if ((number1 + number2) % 2 == 0) {
            Modifier.background(colorResource(id = R.color.bg1))
        } else {
            Modifier.background(colorResource(id = R.color.bg2))
        }.fillMaxSize()
    ) {
        val boxCard = createRef()

        Card(modifier = Modifier
            .constrainAs(boxCard) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            .width(200.dp)) {
            Column {
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = if ((number1 + number2) % 2 == 0) {
                        stringResource(id = R.string.par)
                    } else {
                        stringResource(id = R.string.impar)
                    },
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colorResource(id = R.color.blue))
                        .padding(8.dp)
                )
                Spacer(Modifier.size(8.dp))
                Text(
                    text = (number1 + number2).toString(),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.size(8.dp))
                Row(modifier = Modifier.padding(horizontal = 8.dp)) {
                    Button(
                        onClick = { navControler.popBackStack() },
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(text = stringResource(id = R.string.btnClose))
                    }
                }
            }

        }
    }
}

@Composable
fun CreateNumberBox(label: String, clic: (String) -> Unit) {
    var text by rememberSaveable { mutableStateOf("") }
    ConstraintLayout(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val card = createRef()
        Card(elevation = 8.dp, modifier = Modifier
            .constrainAs(card) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            .width(200.dp)) {
            Column(
                Modifier.padding(16.dp)
            ) {
                OutlinedTextField(
                    value = text,
                    onValueChange = {
                        text = if (it.isDigitsOnly()) it else text
                    },
                    label = { Text(text = label) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = TextStyle(textAlign = TextAlign.Center),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.size(8.dp))
                Button(modifier = Modifier.fillMaxWidth(), onClick = {
                    clic(text)
                    text = ""
                }) {
                    Text(text = stringResource(R.string.btnNext))
                }
            }
        }
    }
}
