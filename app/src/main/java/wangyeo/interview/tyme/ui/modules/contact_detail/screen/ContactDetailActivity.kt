package wangyeo.interview.tyme.ui.modules.contact_detail.screen

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import wangyeo.interview.tyme.app_state.AppState
import wangyeo.interview.tyme.ui.modules.common.BaseActivity
import wangyeo.interview.tyme.ui.modules.common.viewModelFactory
import wangyeo.interview.tyme.ui.modules.contact_detail.viewmodel.ContactDetailViewModel
import wangyeo.interview.repository.model.Contact
import wangyeo.interview.tyme.usecases.Usecase

class ContactDetailActivity : BaseActivity<ContactDetailViewModel>() {
    private lateinit var contactId: String
    private lateinit var contactName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        contactId = intent.getStringExtra("contactId")!!
        contactName = intent.getStringExtra("contactName")!!

        super.onCreate(savedInstanceState)
    }

    override fun viewModelBuilder(): ViewModelProvider.Factory {
        return viewModelFactory {
            ContactDetailViewModel(
                contactId = contactId,
                contactName = contactName,
                contactManager = AppState.instance.contactManager(),
                contactDetailUsecase = Usecase.contactDetailUsecase()
            )
        }
    }

    override fun screenTitle(): String {
        val contact = viewModel.contact
        if (contact is Contact) {
            return contact.name
        }
        return "Contact Detail"
    }

    @Composable
    override fun buildScreen() {
        ContactDetailScreen(viewModel = viewModel)
    }

    override fun onStart() {
        super.onStart()
        // load current contact detail get from list contact
        viewModel.loadContact()

        // fetch latest contact detail from repository
        viewModel.fetchLatestContact()
    }
}
