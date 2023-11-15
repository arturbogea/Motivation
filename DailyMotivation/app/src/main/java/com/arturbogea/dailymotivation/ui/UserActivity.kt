package com.arturbogea.dailymotivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.arturbogea.dailymotivation.infra.MotivationConstant
import com.arturbogea.dailymotivation.R
import com.arturbogea.dailymotivation.infra.SecurityPreferences
import com.arturbogea.dailymotivation.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityUserBinding
    private lateinit var securityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        // Inicializa variáveis da classe
        securityPreferences = SecurityPreferences(this)

        // Acesso aos elementos de interface
        binding.btnSave.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        val id: Int? = view?.id
        if (id == R.id.btn_save){
            handleSave()
        }
    }

    /*
          Para utlizar o ciclo de vida, vou colocar o onDestroy abaixo, para que assim que eu colocar o meu nome na activity, não vou precisar usar mais ela, então para destruir a aplicação.
          Caso eu precise de novo, eu entro nela e ela é criada com o onCreate. Mas ela não fica mais em segundo plano.
     */

    override fun onDestroy() {
        super.onDestroy()
    }

    /*
        Salvar o nome do usuário para atualizações futuras
     */

    private fun handleSave(){

        val name = binding.editName.text.toString()

        //Verificar se o usuário preencheu o nome
        if (name == ""){
            Toast.makeText(this, "Informe o seu nome.", Toast.LENGTH_SHORT).show()
        }else{
            securityPreferences.storeString(MotivationConstant.key.PERSON_NAME, name)

            // Impede que seja possível voltar a Activity
            finish()
        }
    }


}