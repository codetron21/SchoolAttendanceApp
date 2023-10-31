package com.codetron.schoolattendanceapp.screens

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.codetron.schoolattendanceapp.R
import com.codetron.schoolattendanceapp.controller.ForgotPasswordController
import com.codetron.schoolattendanceapp.event.ForgotPasswordEvent
import com.codetron.schoolattendanceapp.model.message.InputFieldMessage
import com.codetron.schoolattendanceapp.model.validator.InputFieldValidator
import com.codetron.schoolattendanceapp.ui.component.InputField
import com.codetron.schoolattendanceapp.ui.theme.SchoolAttendanceAppTheme

@Composable
fun ForgotPasswordScreen(
    modifier: Modifier = Modifier,
    controller: ForgotPasswordController = remember {
        ForgotPasswordController(fieldValidator = InputFieldValidator())
    },
    navController: NavController = rememberNavController()
) {
    val state by controller.state.collectAsState()

    val nisn = state.nisn
    val errNisn = state.errorNisn

    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    val event = state.event

    LaunchedEffect(event) {
        when(event) {
            ForgotPasswordEvent.Idle -> return@LaunchedEffect
            ForgotPasswordEvent.NavBack -> navController.popBackStack()
            is ForgotPasswordEvent.ShowServicesMessage -> {
                val message = event.message
            }
        }

        controller.cleanEvent()
    }

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
            onClick = controller::onSubmitClicked,
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
}

@Preview(showBackground = true)
@Composable
fun ForgotPasswordScreenPreview() {
    SchoolAttendanceAppTheme {
        ForgotPasswordScreen()
    }
}