package com.example.heartpredictionapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class DatasetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dataset)

        val toolbar: Toolbar = findViewById(R.id.toolbar_dataset)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Dataset"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val descriptionText: TextView = findViewById(R.id.tv_dataset_description)
        descriptionText.text = getString(R.string.isi_deskripsi)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
