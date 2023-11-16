package com.pmdm.imcapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat

class ImcCalculatorActivity : AppCompatActivity() {

    private var isMaleSelected: Boolean = true
    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var rsHeight: RangeSlider
    private lateinit var tvHeight: TextView
    private lateinit var btnaddAge: FloatingActionButton
    private lateinit var btnaddPeso: FloatingActionButton
    private lateinit var btnremoveAge: FloatingActionButton
    private lateinit var btnremovePeso: FloatingActionButton
    private lateinit var tvAge: TextView
    private lateinit var tvPeso: TextView
    private var weight: Double = 60.0
    private var Age: Int = 18
    private var altura: Double = 120.0
    private lateinit var btnCalc: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_calculator)
        initComponents()
        initListeners()
        initUI()
        setAge()
        setPeso()
    }

    private fun initListeners() {
        viewMale.setOnClickListener {
            isMaleSelected = true
            setGenderColor()
        }

        viewFemale.setOnClickListener {
            isMaleSelected = false
            setGenderColor()
        }

        rsHeight.addOnChangeListener { _, value, _ ->
            altura = value.toDouble()
            tvHeight.text = "$altura cm"
            //tvHeight.text = value.toString() + " cm"
            //tvHeight.text = DecimalFormat("%.Of").format(value) + " cm"
        }

        btnremovePeso.setOnClickListener{
            weight -= 1
            setPeso()
        }

        btnaddPeso.setOnClickListener{
            weight += 1
            setPeso()
        }

        btnremoveAge.setOnClickListener{
            Age -= 1
            setAge()
        }

        btnaddAge.setOnClickListener{
            Age += 1
            setAge()
        }

        btnCalc.setOnClickListener{
            navigate2result(calculateIMC(weight, altura))
        }
    }

    private fun calculateIMC(weight: Double, height: Double): Double {
        val heightInMeters = height / 100.0
        return weight / (heightInMeters * heightInMeters)
    }
    private fun setPeso(){
        tvPeso.setText(weight.toString() + " Kg")
    }

    private fun setAge(){
        tvAge.setText(Age.toString() + " años")
    }

    private fun initComponents() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        rsHeight = findViewById(R.id.rsHeight)
        tvHeight = findViewById(R.id.tvHeight)
        btnaddAge = findViewById(R.id.btnPlusAge)
        btnaddPeso = findViewById(R.id.btnPlusPeso)
        btnremoveAge = findViewById(R.id.btnSubstractAge)
        btnremovePeso = findViewById(R.id.btnSubstractPeso)
        tvAge = findViewById(R.id.tvAge)
        tvPeso = findViewById(R.id.tvPeso)
        btnCalc = findViewById(R.id.btnCalcular)
    }

    private fun setGenderColor() {
        val bg_color_male = getBackgroundColor(isMaleSelected)
        val bg_color_female = getBackgroundColor(!isMaleSelected)
        viewMale.setCardBackgroundColor(bg_color_male)
        viewFemale.setCardBackgroundColor(bg_color_female)
    }

    private fun getBackgroundColor(isMaleSelected: Boolean): Int {
        val colorReference = if (isMaleSelected) {
            R.color.bg_comp_sel
        } else {
            R.color.bg_comp
        }
        return ContextCompat.getColor(this, colorReference)
    }

    private fun initUI(){
        setGenderColor()
    }

    private fun navigate2result(resultado: Double) {

        var texto: String
        var titulo: String

        when {
            resultado < 18.5 -> {
                titulo = getString(R.string.Pesoiferior)
                texto = getString(R.string.Pesoiferiordesc)
            }
            resultado in 18.5..24.9 -> {
                titulo = getString(R.string.Pesonormal)
                texto = getString(R.string.Pesonormaldesc)
            }
            resultado in 25.0..29.9 -> {
                titulo = getString(R.string.Sobrepeso)
                texto = getString(R.string.Sobrepesodesc)
            }
            resultado > 30.0 -> {
                titulo = getString(R.string.Obesidad)
                texto = getString(R.string.Obesidaddesc)
            }
            else -> {
                titulo = ""
                texto = ""
            }
        }

        val intent = Intent(this, ResultActivity::class.java)

        intent.putExtra("RESULTADO_EXTRA", resultado)
        intent.putExtra("TITULO_EXTRA", titulo)
        intent.putExtra("TEXTO_EXTRA", texto)
        startActivity(intent)
    }
}

