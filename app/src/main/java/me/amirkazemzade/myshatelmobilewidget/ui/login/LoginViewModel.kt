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
import me.amirkazemzade.myshatelmobilewidget.domain.models.LoginRequest
import me.amirkazemzade.myshatelmobilewidget.domain.models.RequestStatus
import me.amirkazemzade.myshatelmobilewidget.domain.models.Status
import me.amirkazemzade.myshatelmobilewidget.domain.repositories.CookieRepository
import me.amirkazemzade.myshatelmobilewidget.domain.usecases.FetchCaptchaUseCase
import me.amirkazemzade.myshatelmobilewidget.domain.usecases.LoginWithPasswordUseCase
import me.amirkazemzade.myshatelmobilewidget.domain.usecases.RequestLoginUseCase
import javax.inject.Inject

@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModel @Inject constructor(
    private val fetchCaptchaUseCase: FetchCaptchaUseCase,
    private val requestLoginUseCase: RequestLoginUseCase,
    private val loginWithPasswordUseCase: LoginWithPasswordUseCase,
    private val cookieRepository: CookieRepository,
) : ViewModel() {
    private val _fetchCaptcha = Channel<Unit>(capacity = Channel.CONFLATED)
    val captchaState = _fetchCaptcha
        .consumeAsFlow().flatMapLatest { fetchCaptchaUseCase() }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(500), Status.Idle)

    private val _loginRequestState =
        MutableStateFlow<Status<LoginRequest>>(Status.Idle)
    val loginRequestState = _loginRequestState.asStateFlow()

    private val _loginWithPasswordState =
        MutableStateFlow<Status<Unit>>(Status.Idle)
    val loginWithPasswordState = _loginWithPasswordState.asStateFlow()

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
            requestLoginUseCase(
                loginRequest = LoginRequest(
                    username = username,
                    captchaResult = captchaResult
                )
            ).collectLatest {
                _loginRequestState.value = it
            }
        }
    }

    fun loginWithPassword(password: String) {
        val currentLoginRequestState = loginRequestState.value
        if (currentLoginRequestState !is RequestStatus.Success<LoginRequest>) return

        viewModelScope.launch {
            loginWithPasswordUseCase(
                username = currentLoginRequestState.data.username,
                password = password,
                captchaResult = currentLoginRequestState.data.captchaResult
            ).collectLatest {
                _loginWithPasswordState.value = it
                if (it is RequestStatus.Success) {
                    setLoggedIn()
                }
            }
        }
    }

    private fun setLoggedIn() {
        viewModelScope.launch {
            cookieRepository.setLoginState(true)
        }
    }

}