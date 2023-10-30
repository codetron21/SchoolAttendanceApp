package com.codetron.schoolattendanceapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codetron.schoolattendanceapp.ui.component.InputField
import com.codetron.schoolattendanceapp.ui.theme.SchoolAttendanceAppTheme

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    var nisn by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.layout_padding))
            .verticalScroll(rememberScrollState())
    ) {

        Spacer(modifier = Modifier.weight(1F))

        Text(
            text = stringResource(id = R.string.app_title),
            fontSize = 36.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
        )

        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = stringResource(id = R.string.login),
            fontSize = 24.sp,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(16.dp))

        InputField(
            value = nisn,
            label = stringResource(id = R.string.nisn),
            onValueChange = { nisn = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                autoCorrect = false,
                imeAction = ImeAction.Next,
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        InputField(
            value = password,
            label = stringResource(id = R.string.password),
            onValueChange = { password = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = if (showPassword) KeyboardType.Text else KeyboardType.Password,
                autoCorrect = false, imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = if (showPassword) R.drawable.ic_visibility_off else R.drawable.ic_visibility),
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = null,
                modifier = Modifier.clickable {
                    showPassword = !showPassword
                }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(stringResource(id = R.string.forgot_password),
            color = MaterialTheme.colorScheme.primary,
            fontSize = 12.sp,
            modifier = Modifier
                .align(Alignment.End)
                .clickable { }
                .padding(4.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { },
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text(
                stringResource(id = R.string.login),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.weight(2F))
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    SchoolAttendanceAppTheme {
        LoginScreen()
    }
}