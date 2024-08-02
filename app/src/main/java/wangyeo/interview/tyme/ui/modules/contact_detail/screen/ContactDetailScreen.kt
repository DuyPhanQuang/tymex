package wangyeo.interview.tyme.ui.modules.contact_detail.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import wangyeo.interview.tyme.ui.modules.contact_detail.viewmodel.ContactDetailViewModel
import wangyeo.interview.tyme.ui.components.Avatar
import wangyeo.interview.tyme.ui.components.PrimaryButton
import wangyeo.interview.repository.model.Contact

@Composable
fun ContactDetailScreen(
    viewModel: ContactDetailViewModel
) {
    val contact = viewModel.contact
    if (contact is Contact) {
        val name = remember {
            mutableStateOf(contact.name)
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Avatar(imageUrl = contact.avatarUrl, sizeInDp = 92F)
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier
                .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    modifier = Modifier.width(120.dp),
                    value = name.value,
                    onValueChange = {
                        name.value = it
                    },
                    label = { Text("name") })
                Spacer(modifier = Modifier.width(32.dp))
            }
            Spacer(modifier = Modifier.height(32.dp))
            PrimaryButton(
                onClickListener = {}
            )
            Spacer(modifier = Modifier.height(32.dp))
        }
    } else {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text("Not found contact id = ${viewModel.contactId()}")
        }
    }
}
