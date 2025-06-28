package com.example.heartpredictionapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.card.MaterialCardView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Heart Failure"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val cardAboutApp: MaterialCardView = findViewById(R.id.card_about_app)
        val cardDataset: MaterialCardView = findViewById(R.id.card_dataset)
        val cardFeatures: MaterialCardView = findViewById(R.id.card_features)
        val cardModel: MaterialCardView = findViewById(R.id.card_model)
        val cardSimulation: MaterialCardView = findViewById(R.id.card_simulation)

        cardAboutApp.setOnClickListener {
            val intent = Intent(this, AboutAppActivity::class.java)
            startActivity(intent)
        }

        cardDataset.setOnClickListener {
            val intent = Intent(this, DatasetActivity::class.java)
            startActivity(intent)
        }

        cardFeatures.setOnClickListener {
            val intent = Intent(this, FeatureActivity::class.java)
            startActivity(intent)
        }

        cardModel.setOnClickListener {
            val intent = Intent(this, ModelActivity::class.java)
            startActivity(intent)
        }

        cardSimulation.setOnClickListener {
            val intent = Intent(this, SimulationActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                val intent = Intent(this, Welcome_Page::class.java)
                startActivity(intent)
                finish()
                true
            }
            R.id.action_prevention -> {
                val intent = Intent(this, PreventionActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_treatment -> {
                val intent = Intent(this, TreatmentActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}