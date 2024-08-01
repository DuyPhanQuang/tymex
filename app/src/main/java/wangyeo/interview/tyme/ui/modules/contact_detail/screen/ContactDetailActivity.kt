package wangyeo.interview.tyme.ui.modules.contact_detail.screen

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import wangyeo.interview.tyme.app_state.AppState
import wangyeo.interview.tyme.ui.modules.common.BaseActivity
import wangyeo.interview.tyme.ui.modules.common.viewModelFactory
import wangyeo.interview.tyme.ui.modules.contact_detail.viewmodel.ContactDetailViewModel
import wangyeo.interview.repository.model.Contact

class ContactDetailActivity : BaseActivity<ContactDetailViewModel>() {
    private lateinit var contactId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        contactId = intent.getStringExtra("contactId")!!

        super.onCreate(savedInstanceState)
    }

    override fun viewModelBuilder(): ViewModelProvider.Factory {
        return viewModelFactory {
            ContactDetailViewModel(
                contactId = contactId,
                contactManager = AppState.instance.contactManager(),
            )
        }
    }

    override fun screenTitle(): String {
        val contact = viewModel.contact
        if (contact is Contact) {
            return contact.fullName()
        }
        return "Contact Detail"
    }

    @Composable
    override fun buildScreen() {
        ContactDetailScreen(viewModel = viewModel)
    }

    override fun onStart() {
        super.onStart()

        viewModel.loadContact()
    }
}
