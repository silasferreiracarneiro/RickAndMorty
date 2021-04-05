package br.com.silascarneiro.rickandmorty.viewmodel.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.silascarneiro.rickandmorty.viewmodel.splash.states.SplashState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val TIME_SPLASH = 3000L

class SplashViewModel : ViewModel() {

    private val state = MutableLiveData<SplashState>()
    val viewState = state

    fun initSplash() {
        GlobalScope.launch(Dispatchers.IO) {
            delay(TIME_SPLASH)
            viewState.postValue(SplashState.SpashFinish)
        }
    }
}