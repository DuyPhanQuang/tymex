package wangyeo.interview.tyme.ui.modules.launching

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import wangyeo.interview.tyme.ui.modules.contact.screen.ContactsActivity

@Composable
fun SplashScreen() {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
            val intent = Intent(context, ContactsActivity::class.java)
            context.startActivity(intent)
            },
            colors = ButtonDefaults.textButtonColors(backgroundColor = Color.Blue)
        ) {
            Text(
                "Goto Contacts",
                color = Color.White
            )
        }
    }
}