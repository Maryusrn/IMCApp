package com.pmdm.imcapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton

class ResultActivity : AppCompatActivity() {

    private lateinit var btnRecalc: AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        initComponents()
        initListeners()
        initUI()
    }

    private fun initListeners(){
        btnRecalc.setOnClickListener{
            navigate2result()
        }
    }
    private fun initComponents() {
        btnRecalc = findViewById(R.id.btnRecalcular)
    }

    private fun initUI(){

    }

    private fun navigate2result() {
        val intent = Intent(this, ImcCalculatorActivity::class.java)
        startActivity(intent)
    }
}