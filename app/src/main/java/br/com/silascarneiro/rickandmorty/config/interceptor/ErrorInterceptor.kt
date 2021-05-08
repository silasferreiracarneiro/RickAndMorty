package br.com.silascarneiro.rickandmorty.config.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class ErrorInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val response = chain.proceed(request)

        when (response.code) {
            400 -> {
                //Show Bad Request Error Message
                // Para enviar para tela de erro, posso mandar via interface
            }
            401 -> {
                //Show UnauthorizedError Message
                // Ou criar um callback para pegar o novo token
            }

            403 -> {
                //Show Forbidden Message
            }

            404 -> {
                //Show NotFound Message
            }
        }
        return response
    }
}