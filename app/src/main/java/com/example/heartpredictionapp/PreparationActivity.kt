package com.example.heartpredictionapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.widget.ImageView
import android.widget.TextView

class PreparationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preparation)

        val toolbar: Toolbar = findViewById(R.id.toolbar_preparation)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Data Preparation"

        val imgMissingValues: ImageView = findViewById(R.id.img_missing_values)
        val txtMissingValues: TextView = findViewById(R.id.txt_missing_values)
        imgMissingValues.setImageResource(R.drawable.prep_missing_values)
        txtMissingValues.text = "Pengecekan nilai yang hilang (Missing Values) menunjukkan bahwa tidak ada data yang hilang di setiap kolom, yang berarti data sudah bersih dan siap untuk tahap selanjutnya tanpa memerlukan imputasi."

        val imgFeatureSelection: ImageView = findViewById(R.id.img_feature_selection)
        val txtFeatureSelection: TextView = findViewById(R.id.txt_feature_selection)
        imgFeatureSelection.setImageResource(R.drawable.prep_feature_selection)
        txtFeatureSelection.text = "Pemilihan fitur dilakukan untuk mengidentifikasi atribut-atribut yang paling relevan untuk memprediksi risiko gagal jantung. Fitur-fitur yang dipilih meliputi 'age', 'ejection_fraction', 'serum_creatinine', 'serum_sodium', 'time', 'anaemia', 'high_blood_pressure', dan 'sex'. Ini penting untuk mengurangi kompleksitas model dan meningkatkan akurasi."

        val imgTrainTestSplit: ImageView = findViewById(R.id.img_train_test_split)
        val txtTrainTestSplit: TextView = findViewById(R.id.txt_train_test_split)
        imgTrainTestSplit.setImageResource(R.drawable.prep_train_test_split)
        txtTrainTestSplit.text = "Data dibagi menjadi set pelatihan (training set) dan set pengujian (testing set) dengan rasio 80:20. Pembagian ini menggunakan 'random_state=42' untuk memastikan konsistensi hasil setiap kali kode dijalankan, serta 'stratify=y' untuk menjaga proporsi kelas target (DEATH_EVENT) di kedua set."

        val imgScaling: ImageView = findViewById(R.id.img_scaling)
        val txtScaling: TextView = findViewById(R.id.txt_scaling)
        imgScaling.setImageResource(R.drawable.prep_scalling)
        txtScaling.text = "Normalisasi data dilakukan menggunakan StandardScaler untuk memastikan bahwa semua fitur memiliki skala yang seragam. Ini penting terutama untuk model yang sensitif terhadap skala fitur, seperti SVM atau regresi logistik, sehingga tidak ada fitur yang mendominasi hanya karena nilainya lebih besar."
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
