package com.codetron.schoolattendanceapp.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.codetron.schoolattendanceapp.R
import com.codetron.schoolattendanceapp.controller.ForgotPasswordController
import com.codetron.schoolattendanceapp.event.ForgotPasswordEvent
import com.codetron.schoolattendanceapp.model.message.InputFieldMessage
import com.codetron.schoolattendanceapp.model.validator.InputFieldValidator
import com.codetron.schoolattendanceapp.services.ForgotPasswordServices
import com.codetron.schoolattendanceapp.services.message.MessageServices
import com.codetron.schoolattendanceapp.ui.component.InputField
import com.codetron.schoolattendanceapp.ui.component.MyAlertDialog
import com.codetron.schoolattendanceapp.ui.component.MyClickableDialog
import com.codetron.schoolattendanceapp.ui.theme.SchoolAttendanceAppTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ForgotPasswordScreen(
    modifier: Modifier = Modifier,
    controller: ForgotPasswordController = remember {
        ForgotPasswordController(
            fieldValidator = InputFieldValidator(),
            services = ForgotPasswordServices()
        )
    },
    navController: NavController = rememberNavController()
) {
    val state by controller.state.collectAsState()

    val nisn = state.nisn
    val errNisn = state.errorNisn
    val loading = state.loading

    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current

    val event = state.event

    val serviceMessage = state.serviceMessage
    val student = state.student

    LaunchedEffect(event) {
        when (event) {
            ForgotPasswordEvent.Idle -> return@LaunchedEffect
            ForgotPasswordEvent.NavBack -> {
                focusManager.clearFocus()
                navController.popBackStack()
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

    if (student != null) {
        val studentNisn = student.nisn.orEmpty()
        val password = student.password.orEmpty()

        val annotatedString = buildAnnotatedString {
            append("Your NISN is ")

            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            ) {
                pushStringAnnotation(tag = studentNisn, annotation = studentNisn)
                append(studentNisn)
            }

            append(", and your password is ")

            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
            ) {
                pushStringAnnotation(tag = password, annotation = password)
                append(password)
            }

            append(".")
        }

        MyClickableDialog(
            icon = painterResource(id = R.drawable.ic_info),
            dialogTitle = stringResource(id = R.string.information),
            dialogText = annotatedString,
            textConfirm = stringResource(id = R.string.ok),
            onConfirmation = { controller.clearStudent() },
            onDismissRequest = { controller.clearStudent() },
            textOnClicked = { value ->
                clipboardManager.setText(AnnotatedString(value))

                Toast.makeText(
                    context,
                    context.getString(R.string.copy_value, value),
                    Toast.LENGTH_SHORT
                ).show()
            }
        )
    }

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(dimensionResource(id = R.dimen.layout_padding))
        ) {

            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = null,
                modifier = Modifier.clickable { controller.onBackClicked() }
            )

            Spacer(modifier = Modifier.weight(1F))

            Text(
                text = stringResource(id = R.string.forgot_password),
                fontSize = 24.sp,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(16.dp))

            InputField(
                value = nisn,
                label = stringResource(id = R.string.nisn),
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

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    focusManager.clearFocus()
                    controller.onSubmitClicked()
                },
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(
                    stringResource(id = R.string.submit),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.weight(2F))
        }

        if (loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }


}

@Preview(showBackground = true)
@Composable
fun ForgotPasswordScreenPreview() {
    SchoolAttendanceAppTheme {
        ForgotPasswordScreen()
    }
}