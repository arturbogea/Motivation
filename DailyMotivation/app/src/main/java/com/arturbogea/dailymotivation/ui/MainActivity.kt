package com.arturbogea.dailymotivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.arturbogea.dailymotivation.R
import com.arturbogea.dailymotivation.data.Mock
import com.arturbogea.dailymotivation.infra.MotivationConstant
import com.arturbogea.dailymotivation.infra.SecurityPreferences
import com.arturbogea.dailymotivation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var securityPreferences: SecurityPreferences
    private var filter = MotivationConstant.Filter.ALL
    private val mock: Mock = Mock()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Remover a suopportActionBar
        supportActionBar?.hide()

        // Inicializar variaveis
        securityPreferences = SecurityPreferences(this)

        // Adicionar eventos
        setListeners()

        // Inicializar
        //zerar as cores
        //aqui vou fingir o click, ele vai chamar o metodo abaixo, vai colocar cores em todos no metodo e quando entrar no primeiro when, vai deixar ele branco.
        handlerFilter(R.id.image_all)
        refreshPhrase()
        //showUserName() -> iremos tirar o showUserName daqui e iremos colocar no onResume





    }

    /*
        Tinha um erro. Quando eu clicava no nome, ele ia para outra activity, mas quando eu voltava para a activity principal.
        Ao voltar para a main activity, ele não grava o nome. Como não tinha o onPause ou onDestroy.
        Então a main activity nunca parava, sempre ficava em segundo plano e não gravava o nome.
        Abaixo, com o onResume, conseguimos corrigir isso, colocando dentro do onResume, o showUserName
     */

    override fun onResume() {
        super.onResume()
        showUserName()
    }

    //acima foi criado os metodos para click, onde tem o this.
    //abaixo foi criado uma sobrescrita para os clicks.
    //abaixo vou pegar os clicks das imagens onde vou selecionar uma e escurecer as outras

    /*
        Tratar eventos de click
     */

    override fun onClick(view: View) {
        val id: Int = view.id

        val listId = listOf(
            R.id.image_all,
            R.id.image_happy,
            R.id.image_sunny
        )

        if (id in listId){
            handlerFilter(id)
        }else if(id == R.id.btn_new_prhase){
            refreshPhrase()
        }else if(id == R.id.txt_user_name){
            startActivity(Intent(this, UserActivity::class.java))
        }
    }

    //abaixo, tem o metodo dos clicks que vai interligar no override acima
    //quando clicar nas imagens a função abaixo é ativada, fazendo a imagens escolhida ficar na cor informada.
    //antes do click, elas estão nas cores informadas

    /*
        Trata o filtro aplicado para as frases
     */

    private fun handlerFilter(id: Int){
        //abaixo vou colocar as cores nas imagens.
        //criado uma varivel categoryId, para separar as escolhas das imagens por númeração
        binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))

        //o categoryid, quando a imagens for selecionada, ele será atualizado e puxará conforme a categoria que a imagem está. Ele passa como parametro
        //criado uma constante, onde será passado os valores para o categoryid receber, sem precisar passar apenas valores sem um sentindo, deixando o codigo bem organizado
        when (id) {
            R.id.image_all -> {
                binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.white))
                filter = MotivationConstant.Filter.ALL
            }
            R.id.image_happy -> {
                binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))
                filter = MotivationConstant.Filter.HAPPY
            }
            R.id.image_sunny -> {
                binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.white))
                filter = MotivationConstant.Filter.SUNNY
            }
        }
    }

    /*
        Atualiza a frase de motivação
     */

    private fun refreshPhrase(){
        binding.txtPhrase.text = mock.getPhrase(filter)
    }

    /*
        Busca o nome do usuário
     */

    private fun showUserName(){
        val name = securityPreferences.getStoredString(MotivationConstant.key.PERSON_NAME)
        binding.txtUserName.text = "Olá, $name!"
    }


    private fun handleUserName(){



        //como está olá kotlin, vamos concatenar com o Olá + o nome que vai receber abaixo.
        //val name = SecurityPreferences(this).getString("USER_NAME")

        //abaixo, vou fazer como uma constante, utilizando o mesmo codigo acima.
        val name = SecurityPreferences(this).getStoredString(MotivationConstant.key.PERSON_NAME)


        binding.txtUserName.text = "Olá, $name!"
    }

    private fun handleNextPhrase(){
        //aqui crio uma função, que quando clicar em nova frase, ele vai puxar uma frase nova.
        //abaixo estamos estanciando a classe Mock, onde estão a frase, estamos estanciando o metodo
        val phrase = Mock().getPhrase(filter)
        binding.txtPhrase.text = phrase
    }



    fun setListeners(){
        //eventos
        binding.btnNewPrhase.setOnClickListener(this)
        binding.imageAll.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)
        binding.txtUserName.setOnClickListener(this)
    }

}