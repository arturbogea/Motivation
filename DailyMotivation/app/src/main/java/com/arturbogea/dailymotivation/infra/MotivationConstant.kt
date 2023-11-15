package com.arturbogea.dailymotivation.infra

class MotivationConstant private constructor(){ //para ninguem estanciar o construtor

    object key{
        //Utilizando uma constante, para guardar o nome da variavel
        const val PERSON_NAME = "USER_NAME"
    }

    object Filter{
        const val ALL = 1
        const val HAPPY = 2
        const val SUNNY = 3
    }


}