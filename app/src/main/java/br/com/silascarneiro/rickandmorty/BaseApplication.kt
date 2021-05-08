package br.com.silascarneiro.rickandmorty

import android.app.Application
import android.content.Context
import br.com.silascarneiro.rickandmorty.di.components.AppComponent
import br.com.silascarneiro.rickandmorty.di.components.ApplicationComponent
import br.com.silascarneiro.rickandmorty.di.components.DaggerAppComponent
import br.com.silascarneiro.rickandmorty.di.components.DaggerApplicationComponent
import br.com.silascarneiro.rickandmorty.di.modules.ApplicationModule
import br.com.silascarneiro.rickandmorty.di.modules.NetworkModule

open class BaseApplication: Application() {

    private lateinit var component: ApplicationComponent
    private var appComponent: AppComponent? = null
    private lateinit var context: Context

    override fun onCreate() {
        super.onCreate()
        component = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        component.inject(this)

        appComponent = DaggerAppComponent.builder().networkModule(NetworkModule(this)).build()
        context = this
    }

    fun getAppComponent(): AppComponent? {
        return appComponent
    }

    fun getContext(): Context{
        return context
    }
}