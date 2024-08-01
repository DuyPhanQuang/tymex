package wangyeo.interview.tyme.ui.modules.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

inline fun <T : ViewModel> viewModelFactory(crossinline f: () -> T) =
    object : ViewModelProvider.Factory {
        override fun <S : ViewModel> create(aClass: Class<S>):S = f() as S
    }