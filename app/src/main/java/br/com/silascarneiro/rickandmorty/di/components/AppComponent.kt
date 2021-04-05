package br.com.silascarneiro.rickandmorty.di.components

import br.com.silascarneiro.rickandmorty.di.modules.NetworkModule
import br.com.silascarneiro.rickandmorty.ui.home.HomeActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent {
    fun inject(activity: HomeActivity)
}