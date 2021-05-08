package br.com.silascarneiro.rickandmorty.di.modules

import android.app.Application
import android.content.Context
import br.com.silascarneiro.rickandmorty.di.ApplicationContext
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(val application: Application) {

    @Provides
    @ApplicationContext
    fun provideContext(): Context {
        return application
    }

    @Provides
    fun provideApplication(): Application {
        return application
    }
}