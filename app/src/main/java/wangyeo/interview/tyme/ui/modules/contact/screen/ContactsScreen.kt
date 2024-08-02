package wangyeo.interview.tyme.ui.modules.contact.screen

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
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

    LoadListView<Contact>(
        viewModel = viewModel,
        modifier = Modifier.fillMaxSize(),
    ) {
        Card(
            modifier = Modifier
                .padding(paddingValues = PaddingValues(
                    top = 16.dp))
                .clickable {
                    val intent = Intent(context, ContactDetailActivity::class.java)
                    intent.putExtra("contactId", it.id.toString())
                    intent.putExtra("contactName", it.name)
                    context.startActivity(intent)
                },
            elevation = 14.dp,
            shape = RoundedCornerShape(16.dp),
            content = {
                Row {
                    Image(
                        painter = rememberImagePainter(
                            data = it.avatarUrl,
                            builder = {
                                crossfade(true)
                                placeholder(R.drawable.ic_launcher_background)
                                transformations(CircleCropTransformation())
                            },
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .size(160.dp)
                            .padding(8.dp))
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            it.name,
                            color = Color.Black,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(
                            buildAnnotatedString {
                                append("click to my link \n")
                                withStyle(style = SpanStyle(fontWeight = FontWeight.W900, color = Color.Blue)
                                ) {
                                    append(it.htmlUrl)
                                }
                            }
                        )
                    }
                }
            }
        )
    }
}
