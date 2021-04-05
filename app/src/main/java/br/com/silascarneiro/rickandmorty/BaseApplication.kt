package br.com.silascarneiro.rickandmorty

import android.app.Application
import br.com.silascarneiro.rickandmorty.di.components.AppComponent
import br.com.silascarneiro.rickandmorty.di.components.DaggerAppComponent

open class BaseApplication: Application() {

    private var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }

    fun getAppComponent(): AppComponent? {
        return appComponent
    }
}