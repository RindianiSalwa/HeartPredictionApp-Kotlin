package com.example.heartpredictionapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.widget.ImageView
import android.widget.TextView

class ModelSimulationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_model_simulation)

        val toolbar: Toolbar = findViewById(R.id.toolbar_simulation)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.toolbar_title_simulation)

        val imgModelPrediction: ImageView = findViewById(R.id.img_model_prediction)
        val txtModelPrediction: TextView = findViewById(R.id.txt_model_prediction)
        imgModelPrediction.setImageResource(R.drawable.sim_model_prediction)
        txtModelPrediction.text = getString(R.string.text_model_prediction)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
