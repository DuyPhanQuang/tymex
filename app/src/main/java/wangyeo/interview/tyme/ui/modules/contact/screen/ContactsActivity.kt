package wangyeo.interview.tyme.ui.modules.contact.screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import wangyeo.interview.tyme.app_state.AppState
import wangyeo.interview.tyme.ui.modules.common.BaseActivity
import wangyeo.interview.tyme.ui.modules.common.viewModelFactory
import wangyeo.interview.tyme.ui.modules.contact.viewmodel.ContactsViewModel
import wangyeo.interview.tyme.usecases.Usecase

class ContactsActivity : BaseActivity<ContactsViewModel>() {
    override fun viewModelBuilder(): ViewModelProvider.Factory {
        return viewModelFactory {
            ContactsViewModel(
            Usecase.loadContactListUsecase(),
            AppState.instance.contactManager()
            )
        }
    }

    override fun screenTitle(): String {
        return "Contacts"
    }

    @Composable
    override fun buildScreen() {
        ContactsScreen(viewModel = viewModel)
    }

    override fun onStart() {
        super.onStart()

        viewModel.start()
    }
}