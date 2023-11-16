package com.pmdm.imcapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
class ResultActivity : AppCompatActivity() {

    private lateinit var btnRecalc: AppCompatButton
    private lateinit var tvtextview1: TextView
    private lateinit var tvresult: TextView
    private lateinit var tvdesc: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        initComponents()
        initListeners()
        initUI()

        var initialResult = intent.getDoubleExtra("RESULTADO_EXTRA", 0.0)
        var initialTitle = intent.getStringExtra("TITULO_EXTRA") ?: ""
        var initialTextResult = intent.getStringExtra("TEXTO_EXTRA") ?: ""

        tvresult.text = initialResult.toString()
        tvtextview1.text = initialTitle
        tvdesc.text = initialTextResult
    }

    private fun initListeners() {
        btnRecalc.setOnClickListener {
            navigate2result()
        }
    }

    private fun initComponents() {
        btnRecalc = findViewById(R.id.btnRecalcular)
        tvtextview1 = findViewById(R.id.tvtext1)
        tvresult = findViewById(R.id.tvresult)
        tvdesc = findViewById(R.id.tvdesc)
    }

    private fun initUI() {
        // Puedes realizar cualquier inicialización de la interfaz de usuario aquí si es necesario
    }
    private fun navigate2result() {
        val intent = Intent(this, ImcCalculatorActivity::class.java)
        startActivity(intent)
    }
}
