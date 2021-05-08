package br.com.silascarneiro.rickandmorty.di

import java.lang.annotation.RetentionPolicy
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationContext()
