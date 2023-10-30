package com.codetron.schoolattendanceapp.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codetron.schoolattendanceapp.R

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    error: String? = null,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit,
    actionContent: @Composable (RowScope.() -> Unit)? = null,
) {

    var focusInputState by remember { mutableStateOf(false) }

    val focusColor = if (focusInputState) {
        MaterialTheme.colorScheme.primary
    } else {
        Color.Black
    }

    Column(modifier = modifier) {
        Text(
            label,
            color = focusColor,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(2.dp))

        BasicTextField(
            value = value,
            enabled = enabled,
            onValueChange = onValueChange,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            maxLines = 1,
            singleLine = true,
            visualTransformation = visualTransformation,
            cursorBrush = SolidColor(MaterialTheme.colorScheme.secondary),
            decorationBox = { innerTextField ->
                val strokeWidth = if (focusInputState) {
                    2.dp
                } else {
                    1.dp
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .border(strokeWidth, color = focusColor, shape = RoundedCornerShape(4.dp))
                        .padding(horizontal = 8.dp, vertical = 10.dp)
                ) {
                    Box(modifier = Modifier.weight(1F)) {
                        innerTextField()
                    }

                    if (actionContent != null) {
                        Spacer(modifier = Modifier.width(16.dp))
                        actionContent()
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    focusInputState = it.hasFocus
                }
        )

        if (error != null) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(error, color = MaterialTheme.colorScheme.error)
        }
    }


}

@Preview(showBackground = true)
@Composable
fun InputFieldPreview() {
    InputField(
        value = "NISN",
        label = "NISN",
        onValueChange = {}
    )
}