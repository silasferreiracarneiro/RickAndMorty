package br.com.silascarneiro.rickandmorty.ui.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders.of
import br.com.silascarneiro.rickandmorty.R
import br.com.silascarneiro.rickandmorty.ui.home.HomeActivity
import br.com.silascarneiro.rickandmorty.viewmodel.splash.SplashViewModel
import br.com.silascarneiro.rickandmorty.viewmodel.splash.states.SplashState

class SplashActivity : AppCompatActivity() {

    companion object {
        fun newInstance(context: Context) = Intent(context, SplashActivity::class.java)
    }

    lateinit var viewmodel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        setupViewModel()
        initSplash()
        setupObserver()
    }

    private fun setupViewModel() {
        viewmodel = of(this).get(SplashViewModel::class.java)
    }

    private fun initSplash() {
        viewmodel.initSplash()
    }

    private fun setupObserver() {
        viewmodel.viewState.observe(this, Observer {
            if (it is SplashState.SpashFinish) {
                nextActivityHome()
            }
        })
    }

    private fun nextActivityHome() {
        startActivity(HomeActivity.newInstance(this))
        finish()
    }
}