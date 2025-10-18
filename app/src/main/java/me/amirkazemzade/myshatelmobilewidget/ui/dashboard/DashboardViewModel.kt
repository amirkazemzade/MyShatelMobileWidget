package me.amirkazemzade.myshatelmobilewidget.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.amirkazemzade.myshatelmobilewidget.domain.models.Status
import me.amirkazemzade.myshatelmobilewidget.domain.usecases.LogoutUseCase
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase,
) : ViewModel() {

    private val _logoutState = MutableStateFlow<Status<Unit>>(Status.Idle)
    val logoutState = _logoutState.asStateFlow()


    fun logout() {
        viewModelScope.launch {
            logoutUseCase()
                .collect {
                    _logoutState.update { it }
                }
        }
    }
}
