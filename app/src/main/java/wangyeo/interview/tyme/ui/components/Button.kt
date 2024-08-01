package wangyeo.interview.tyme.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.material.composethemeadapter.MdcTheme
import wangyeo.interview.tyme.ui.components.theme.ComponentThemeColors

@Composable
fun Button(
    title: String,
    backgroundColor: Color,
    highlightColor: Color,
    disabledColor: Color = Color.Gray,
    textSizeInSp: Float = 14f,
    textColor: Color,
    cornerRadiusInDp: Float = 0f,
    borderColor: Color? = null,
    widthInDp: Float? = null,
    heightInDp: Float? = null,
    paddingInDp: Float? = null,
    isEnable: Boolean = true,
    onClickListener: () -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val emptyFunc = {}
    val backgroundByState = if (!isEnable) {
        disabledColor
    } else if (isPressed) {
        highlightColor
    } else {
        backgroundColor
    }

    androidx.compose.material.Button(
        interactionSource = interactionSource,
        onClick = if (isEnable) onClickListener else emptyFunc,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundByState,
            contentColor = textColor
        ),
        shape = RoundedCornerShape(corner = CornerSize(cornerRadiusInDp.dp)),
        border = BorderStroke(
            width = if (borderColor != null) 1.dp else 0.dp,
            color = borderColor ?: Color.Transparent
        ),
        contentPadding = if (paddingInDp != null) PaddingValues(paddingInDp.dp) else ButtonDefaults.ContentPadding,
        enabled = isEnable,
        modifier = getModifier(widthInDp, heightInDp)
    ) {
        Text(
            title,
            color = textColor,
            fontSize = textSizeInSp.sp,
            fontWeight = FontWeight.Bold)
    }
}

private fun getModifier(
    widthInDp: Float? = null,
    heightInDp: Float? = null
): Modifier {
    if (widthInDp != null && heightInDp != null) {
        return Modifier.size(widthInDp.dp, heightInDp.dp)
    }

    if (widthInDp != null) {
        if (widthInDp < 0) {
            return Modifier.fillMaxWidth()
        }
        return Modifier.width(widthInDp.dp)
    }

    if (heightInDp != null) {
        if (heightInDp < 0) {
            return Modifier.fillMaxHeight()
        }
        return Modifier.height(heightInDp.dp)
    }

    return Modifier.size(Dp.Unspecified)
}

@Preview()
@Composable
fun PrimaryButton(onClickListener: () -> Unit = {}) {
    MdcTheme {
        Button(
            title = "Primary Button",
            backgroundColor = ComponentThemeColors.PrimaryColor,
            highlightColor = ComponentThemeColors.SecondaryColor,
            textColor = Color.Black,
            cornerRadiusInDp = 8f,
            widthInDp = -1f,
            onClickListener = onClickListener
        )
    }
}

@Composable
fun PaddingButton(onClickListener: () -> Unit = {}) {
    MdcTheme {
        Button(
            title = "Padding Button",
            backgroundColor = ComponentThemeColors.PrimaryColor,
            highlightColor = ComponentThemeColors.SecondaryColor,
            textColor = Color.Black,
            cornerRadiusInDp = 8f,
            onClickListener = onClickListener
        )
    }
}

@Composable
fun SecondaryButton(onClickListener: () -> Unit = {}) {
    MdcTheme {
        Button(
            title = "Secondary Button",
            backgroundColor = Color.White,
            highlightColor = ComponentThemeColors.SecondaryColor,
            textColor = Color.Black,
            cornerRadiusInDp = 8f,
            borderColor = Color.Gray,
            widthInDp = -1f,
            onClickListener = onClickListener
        )
    }
}

@Composable
fun SecondaryPaddingButton(onClickListener: () -> Unit = {}) {
    MdcTheme {
        Button(
            title = "Secondary Padding Button",
            backgroundColor = Color.White,
            highlightColor = ComponentThemeColors.SecondaryColor,
            textColor = Color.Black,
            cornerRadiusInDp = 8f,
            borderColor = Color.Gray,
            onClickListener = onClickListener
        )
    }
}