package me.amirkazemzade.myshatelmobilewidget.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.amirkazemzade.myshatelmobilewidget.domain.models.Remained
import me.amirkazemzade.myshatelmobilewidget.domain.models.Status
import me.amirkazemzade.myshatelmobilewidget.domain.usecases.FetchRemainedUseCase
import me.amirkazemzade.myshatelmobilewidget.domain.usecases.LogoutUseCase
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase,
    private val fetchRemainedUseCase: FetchRemainedUseCase,
) : ViewModel() {

    private val _logoutState = MutableStateFlow<Status<Unit>>(Status.Idle)
    val logoutState = _logoutState.asStateFlow()

    private val _remainedState = MutableStateFlow<Status<Remained>>(Status.Idle)
    val remainedState: StateFlow<Status<Remained>> = _remainedState.asStateFlow()

    init {
        refreshRemained()
    }

    fun logout() {
        viewModelScope.launch {
            logoutUseCase()
                .collect {
                    _logoutState.update { it }
                }
        }
    }

    fun refreshRemained() {
        viewModelScope.launch {
            fetchRemainedUseCase().collect { newState ->
                _remainedState.update { newState }
            }
        }
    }
}
