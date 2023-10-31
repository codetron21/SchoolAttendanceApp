package com.codetron.schoolattendanceapp.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.codetron.schoolattendanceapp.R
import com.codetron.schoolattendanceapp.controller.LoginController
import com.codetron.schoolattendanceapp.event.LoginEvent
import com.codetron.schoolattendanceapp.model.message.InputFieldMessage
import com.codetron.schoolattendanceapp.model.validator.InputFieldValidator
import com.codetron.schoolattendanceapp.services.LoginServices
import com.codetron.schoolattendanceapp.services.message.MessageServices
import com.codetron.schoolattendanceapp.services.message.ServiceTypeMessage
import com.codetron.schoolattendanceapp.ui.component.InputField
import com.codetron.schoolattendanceapp.ui.component.MyAlertDialog
import com.codetron.schoolattendanceapp.ui.theme.SchoolAttendanceAppTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    controller: LoginController = remember {
        LoginController(
            fieldValidator = InputFieldValidator(),
            services = LoginServices()
        )
    },
    navController: NavController = rememberNavController()
) {

    val state by controller.state.collectAsState()

    val nisn = state.nisn
    val password = state.password
    val errNisn = state.errorNisn
    val errPass = state.errorPass
    val showPassword = state.showPassword
    val loading = state.loading
    val serviceMessage = state.serviceMessage

    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    val event = state.event

    LaunchedEffect(event) {
        when (event) {
            LoginEvent.Idle -> return@LaunchedEffect

            LoginEvent.NavToDashboard -> {
                Toast.makeText(context, "login", Toast.LENGTH_SHORT).show()
            }

            LoginEvent.NavToForgotPassword -> {
                navController.navigate("forgot-password")
            }
        }
        controller.cleanEvent()
    }

    if (serviceMessage != null) {
        val message = MessageServices.getMessage(context, serviceMessage)

        MyAlertDialog(
            icon = painterResource(id = R.drawable.ic_info),
            dialogTitle = stringResource(id = R.string.information),
            dialogText = message.orEmpty(),
            textConfirm = stringResource(id = R.string.ok),
            onConfirmation = { controller.clearMessage() },
            onDismissRequest = { controller.clearMessage() }
        )
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(dimensionResource(id = R.dimen.layout_padding))
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
                enabled = !loading,
                error = InputFieldMessage.getFieldMessage(context, errNisn),
                onValueChange = controller::onNisnChanged,
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
                enabled = !loading,
                error = InputFieldMessage.getFieldMessage(context, errPass),
                onValueChange = controller::onPasswordChanged,
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
                    modifier = Modifier.clickable(
                        enabled = !loading,
                    ) {
                        controller.onPasswordToggle()
                    }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(stringResource(id = R.string.forgot_password),
                color = MaterialTheme.colorScheme.primary,
                fontSize = 12.sp,
                modifier = Modifier
                    .align(Alignment.End)
                    .clickable(enabled = !loading) {
                        focusManager.clearFocus()
                        controller.onForgotPasswordClicked()
                    }
                    .padding(4.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    focusManager.clearFocus()
                    controller.onLoginClicked()
                },
                shape = RoundedCornerShape(4.dp),
                enabled = !loading,
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

        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    SchoolAttendanceAppTheme {
        LoginScreen()
    }
}