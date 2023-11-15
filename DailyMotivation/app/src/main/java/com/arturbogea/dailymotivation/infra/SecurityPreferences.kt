package com.arturbogea.dailymotivation.infra

import android.content.Context
import android.content.SharedPreferences

class SecurityPreferences(context: Context) {

    private val mSharedPreferences: SharedPreferences =
        context.getSharedPreferences("Motivation", Context.MODE_PRIVATE)

    //o contexto se passa como context e passa no construtor
    private val security: SharedPreferences = context.getSharedPreferences("Motivation", Context.MODE_PRIVATE)

    //Aqui é um metodo para salvar o valor
    fun storeString(key: String, value: String){ // estou passando uma string qualquer, para que posso deixar no put string
        //criei uma chave key, que salva o que eu quiser
        //o edit estou diatando o security preferences.
        this.mSharedPreferences.edit().putString(key, value).apply()

    }

    //como eu salvei usando uma chave, vou recuperar utilizando uma chave. No caso a key
    fun getStoredString(key: String): String{
        //abaixo, para não me preocupar com o retorno vazio, estou criando um elvis operator para retornar vazio
        return this.mSharedPreferences.getString(key, "") ?: ""
    }
}