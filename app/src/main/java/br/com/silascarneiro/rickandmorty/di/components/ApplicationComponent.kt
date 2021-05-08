package br.com.silascarneiro.rickandmorty.di.components

import android.app.Application
import android.content.Context
import br.com.silascarneiro.rickandmorty.BaseApplication
import br.com.silascarneiro.rickandmorty.di.ApplicationContext
import br.com.silascarneiro.rickandmorty.di.modules.ApplicationModule
import br.com.silascarneiro.rickandmorty.di.modules.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class,
    NetworkModule::class
])
interface ApplicationComponent {

    fun inject(app: BaseApplication)

    @ApplicationContext
    fun context(): Context

    fun application(): Application
}