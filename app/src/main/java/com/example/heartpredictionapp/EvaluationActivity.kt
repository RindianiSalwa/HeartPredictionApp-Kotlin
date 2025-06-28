package com.example.heartpredictionapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.widget.ImageView
import android.widget.TextView

class EvaluationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evaluation)

        val toolbar: Toolbar = findViewById(R.id.toolbar_evaluation)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.toolbar_title_evaluation)

        val imgConfusionMatrix: ImageView = findViewById(R.id.img_confusion_matrix)
        val txtConfusionMatrix: TextView = findViewById(R.id.txt_confusion_matrix)
        imgConfusionMatrix.setImageResource(R.drawable.eval_confusion_matrix)
        txtConfusionMatrix.text = getString(R.string.text_confusion_matrix)

        val imgClassificationReport: ImageView = findViewById(R.id.img_classification_report)
        val txtClassificationReport: TextView = findViewById(R.id.txt_classification_report)
        imgClassificationReport.setImageResource(R.drawable.eval_classification_report)
        txtClassificationReport.text = getString(R.string.text_classification_report)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
