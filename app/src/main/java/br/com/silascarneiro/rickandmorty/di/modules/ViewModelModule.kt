package br.com.silascarneiro.rickandmorty.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.silascarneiro.rickandmorty.di.ViewModelKey
import br.com.silascarneiro.rickandmorty.viewmodel.character.CharacterViewModel
import br.com.silascarneiro.rickandmorty.viewmodel.factory.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CharacterViewModel::class)
    abstract fun bindViewModel(viewModel: CharacterViewModel): ViewModel

    @Binds
    abstract fun bindFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}