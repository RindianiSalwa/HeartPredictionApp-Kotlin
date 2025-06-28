package com.example.heartpredictionapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class VisualisasiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visualisasi)

        val toolbar = findViewById<Toolbar>(R.id.toolbar_visualisasi)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Visualisasi Data"
            setDisplayHomeAsUpEnabled(true)
        }

        toolbar.setNavigationOnClickListener { onBackPressed() }

        findViewById<ImageView>(R.id.img1).setImageResource(R.drawable.visual_death_event)
        findViewById<TextView>(R.id.txt1).text = "Distribusi DEATH_EVENT: 0 = hidup, 1 = meninggal."

        findViewById<ImageView>(R.id.img2).setImageResource(R.drawable.visual_age)
        findViewById<TextView>(R.id.txt2).text = "Distribusi usia pasien. Dominan usia 50–70 tahun."

        findViewById<ImageView>(R.id.img3).setImageResource(R.drawable.visual_ejection_fraction)
        findViewById<TextView>(R.id.txt3).text = "Distribusi ejection fraction. Nilai rendah → risiko tinggi."

        findViewById<ImageView>(R.id.img4).setImageResource(R.drawable.box_age_death)
        findViewById<TextView>(R.id.txt4).text = "Boxplot usia berdasarkan DEATH_EVENT. Usia meninggal lebih tinggi."

        findViewById<ImageView>(R.id.img5).setImageResource(R.drawable.count_smoking)
        findViewById<TextView>(R.id.txt5).text = "Jumlah pasien merokok. Perbedaan kematian tidak signifikan."

        findViewById<ImageView>(R.id.img6).setImageResource(R.drawable.heatmap_korelasi)
        findViewById<TextView>(R.id.txt6).text = "Heatmap korelasi antar fitur. Korelasi negatif antara time dan ejection_fraction terhadap DEATH_EVENT."
    }
}
