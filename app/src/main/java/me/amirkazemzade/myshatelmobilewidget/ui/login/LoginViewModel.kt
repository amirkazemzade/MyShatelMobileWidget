package me.amirkazemzade.myshatelmobilewidget.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import me.amirkazemzade.myshatelmobilewidget.domain.models.AuthenticatedResult
import me.amirkazemzade.myshatelmobilewidget.domain.models.RequestStatus
import me.amirkazemzade.myshatelmobilewidget.domain.repositories.AuthRepository
import javax.inject.Inject

@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _fetchCaptcha = Channel<Unit>(capacity = Channel.CONFLATED)
    val captchaState = _fetchCaptcha.consumeAsFlow().flatMapLatest { authRepository.getCaptcha() }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(500), RequestStatus.Loading)

    private val _loginRequestState =
        MutableStateFlow<RequestStatus<AuthenticatedResult<Unit>>>(RequestStatus.Loading)
    val loginRequestState = _loginRequestState.asStateFlow()

    init {
        fetchCaptcha()
    }

    fun fetchCaptcha() {
        _fetchCaptcha.trySend(Unit)
    }

    fun loginRequest(username: String, captchaResult: String) {
        val currentCaptchaState = captchaState.value
        if (currentCaptchaState !is RequestStatus.Success) return
        viewModelScope.launch {
            authRepository.requestLoginForUser(
                currentCaptchaState.data.cookie,
                username,
                captchaResult
            ).collectLatest {
                _loginRequestState.value = it
            }
        }
    }

}