package com.arturbogea.dailymotivation.data

import com.arturbogea.dailymotivation.infra.MotivationConstant
import kotlin.random.Random

class Mock {
    //Mock é uma aplicação que não é real. Ou seja, são frases que já estão no app, não irão ser buscadas em outro lugar

    //abaixo, vou passar as contastantes criadas, em variaveis, para que possamos utilizar e também deixar o codigo mais simplicado e simples
    private val all = MotivationConstant.Filter.ALL // o all, por mais que seja uma categoria, ele engloba as outras 2. Ele puxa qualquer outra categoria
    private val sunny = MotivationConstant.Filter.SUNNY
    private val happy = MotivationConstant.Filter.HAPPY


    private val mListPhrase = listOf<Phrase>(
        Phrase("Não sabendo que era impossível, foi lá e fez.", happy),
        Phrase("Você não é derrotado quando perde, você é derrotado quando desiste!", happy),
        Phrase("Quando está mais escuro, vemos mais estrelas!", happy),
        Phrase("Insanidade é fazer sempre a mesma coisa e esperar um resultado diferente.", happy),
        Phrase("Não pare quando estiver cansado, pare quando tiver terminado.", happy),
        Phrase("O que você pode fazer agora que tem o maior impacto sobre o seu sucesso?", happy),
        Phrase("A melhor maneira de prever o futuro é inventá-lo.", sunny),
        Phrase("Você perde todas as chances que você não aproveita.", sunny),
        Phrase("Fracasso é o condimento que dá sabor ao sucesso.", sunny),
        Phrase(" Enquanto não estivermos comprometidos, haverá hesitação!", sunny),
        Phrase("Se você não sabe onde quer ir, qualquer caminho serve.", sunny),
        Phrase("Se você acredita, faz toda a diferença.", sunny),
        Phrase("Riscos devem ser corridos, porque o maior perigo é não arriscar nada!", sunny)
    )

    fun getPhrase(value: Int): String{
        //abaixo crio uma variavel onde receber o filtro
        val filtered = mListPhrase.filter { it.categoryId == value  || value == all} // como na lista não tem nenhum all, ele vai puxar o valor padrão e também tem acesso a lista toda
        //abaixo como retorna uma string, passo o return filtered que ta recebendo um index da lista, que vai passar uma frase
        return filtered[Random.nextInt(filtered.size)].description



    }


}

data class Phrase(val description: String, val categoryId: Int)