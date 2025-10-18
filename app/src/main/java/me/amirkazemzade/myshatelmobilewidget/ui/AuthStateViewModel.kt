package me.amirkazemzade.myshatelmobilewidget.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import me.amirkazemzade.myshatelmobilewidget.domain.models.Status
import me.amirkazemzade.myshatelmobilewidget.domain.usecases.CheckAuthUseCase
import javax.inject.Inject

@HiltViewModel
class AuthStateViewModel @Inject constructor(
    checkAuthUseCase: CheckAuthUseCase,
) : ViewModel() {

    val authState = checkAuthUseCase()
        .stateIn(
            viewModelScope, SharingStarted.WhileSubscribed(500), Status.Idle
        )

}
