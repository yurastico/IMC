package com.yurastico.imc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.yurastico.imc.databinding.ActivityMainBinding
import com.yurastico.imc.extensions.format
import com.yurastico.imc.extensions.hideKeyboard
import com.yurastico.imc.watchers.DecimalTextWatcher

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.btCalcular.setOnClickListener {
            binding.btCalcular.hideKeyboard()
            calcular()
        }
        binding.etPeso.addTextChangedListener(DecimalTextWatcher(binding.etPeso,1))
        binding.etAltura.addTextChangedListener(DecimalTextWatcher(binding.etAltura,2))
    }

    private fun calcular() {
        val peso = binding.etPeso.text.toString().toDouble()
        val altura = binding.etAltura.text.toString().toDouble()

        val imc = peso / (altura * altura)

        if (imc < 18.5) {
            configuraIMC(imc, R.drawable.masc_abaixo, R.string.magreza)
        } else if (imc < 24.9) {
            configuraIMC(imc, R.drawable.masc_ideal, R.string.peso_normal)
        } else if (imc < 29.9) {
            configuraIMC(imc, R.drawable.masc_sobre, R.string.sobre_peso)
        } else if (imc < 34.9) {
            configuraIMC(imc, R.drawable.masc_obeso, R.string.obesidade_grau_i)
        } else if (imc < 39.9) {
            configuraIMC(imc, R.drawable.masc_extremo_obeso, R.string.obesidade_grau_ii)
        } else {
            configuraIMC(imc, R.drawable.masc_extremo_obeso, R.string.obesidade_grau_iii)
        }

    }

    private fun configuraIMC(imc: Double, @DrawableRes drawableId: Int, @StringRes stringId: Int) {
        binding.tvIMC.text =  getString(R.string.seu_imc, imc.format(2))

        binding.ivIMCStatus.setImageDrawable(
            ContextCompat.getDrawable(this, drawableId)
        )

        binding.tvIMCStatus.text = getString(stringId)
    }

}