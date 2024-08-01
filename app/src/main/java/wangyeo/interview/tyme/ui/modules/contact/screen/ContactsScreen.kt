package wangyeo.interview.tyme.ui.modules.contact.screen

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import wangyeo.interview.tyme.ui.modules.contact.viewmodel.ContactsViewModel
import wangyeo.interview.tyme.R
import wangyeo.interview.tyme.ui.modules.common.LoadListView
import wangyeo.interview.tyme.ui.modules.contact_detail.screen.ContactDetailActivity
import wangyeo.interview.repository.model.Contact

@Composable
fun ContactsScreen(
    viewModel: ContactsViewModel
) {
    val context = LocalContext.current

    LoadListView<Contact>(viewModel = viewModel) {
        Row(modifier = Modifier
            .clickable {
                val intent = Intent(context, ContactDetailActivity::class.java)
                intent.putExtra("contactId", it.id)
                context.startActivity(intent)
            }
        ) {
            Image(
                painter = rememberImagePainter(
                    data = it.avatar,
                    builder = {
                        crossfade(true)
                        placeholder(R.drawable.ic_launcher_background)
                        transformations(CircleCropTransformation())
                    },
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .padding(8.dp))
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    it.fullName(),
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold)
                Text(
                    it.fullAddress(),
                    color = Color.Gray,
                    fontSize = 12.sp)
            }
        }
    }
}
