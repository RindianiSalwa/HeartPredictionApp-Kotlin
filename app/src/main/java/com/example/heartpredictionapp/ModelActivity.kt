package com.example.heartpredictionapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.appcompat.widget.Toolbar

class ModelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_model)

        val toolbar = findViewById<Toolbar>(R.id.toolbar_model)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Model"
            setDisplayHomeAsUpEnabled(true)
        }
        toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        val visualisasi = findViewById<CardView>(R.id.cardVisualisasi)
        val preparation = findViewById<CardView>(R.id.cardPreparation)
        val modeling = findViewById<CardView>(R.id.cardModeling)
        val evaluation = findViewById<CardView>(R.id.cardEvaluation)
        val simulation = findViewById<CardView>(R.id.cardSimulation)

        visualisasi.setOnClickListener {
            startActivity(Intent(this, VisualisasiActivity::class.java))
        }

        preparation.setOnClickListener {
            startActivity(Intent(this, PreparationActivity::class.java))
        }

        modeling.setOnClickListener {
            startActivity(Intent(this, ModelingActivity::class.java))
        }

        evaluation.setOnClickListener {
            startActivity(Intent(this, EvaluationActivity::class.java))
        }

        simulation.setOnClickListener {
            startActivity(Intent(this, ModelSimulationActivity::class.java))
        }
    }
}
