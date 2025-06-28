package com.example.heartpredictionapp

import android.content.res.AssetFileDescriptor
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.card.MaterialCardView
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.io.IOException
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel
import java.util.*

class SimulationActivity : AppCompatActivity() {

    private lateinit var etAge: EditText
    private lateinit var etEjectionFraction: EditText
    private lateinit var etSerumCreatinine: EditText
    private lateinit var etSerumSodium: EditText
    private lateinit var etFollowUpTime: EditText
    private lateinit var spinnerAnaemia: Spinner
    private lateinit var spinnerHighBloodPressure: Spinner
    private lateinit var spinnerSex: Spinner
    private lateinit var btnPredict: Button
    private lateinit var cardPredictionResult: MaterialCardView
    private lateinit var ivPredictionIcon: ImageView
    private lateinit var tvPredictionResult: TextView

    private var tflite: Interpreter? = null
    private val modelPath = "model_heart_failure.tflite"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simulation)

        val toolbar: Toolbar = findViewById(R.id.toolbar_simulation)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.menu_simulation_title)
        toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        etAge = findViewById(R.id.et_age)
        etEjectionFraction = findViewById(R.id.et_ejection_fraction)
        etSerumCreatinine = findViewById(R.id.et_serum_creatinine)
        etSerumSodium = findViewById(R.id.et_serum_sodium)
        etFollowUpTime = findViewById(R.id.et_follow_up_time)
        spinnerAnaemia = findViewById(R.id.spinner_anaemia)
        spinnerHighBloodPressure = findViewById(R.id.spinner_high_blood_pressure)
        spinnerSex = findViewById(R.id.spinner_sex)
        btnPredict = findViewById(R.id.btn_predict)
        cardPredictionResult = findViewById(R.id.card_prediction_result)
        ivPredictionIcon = findViewById(R.id.iv_prediction_icon)
        tvPredictionResult = findViewById(R.id.tv_prediction_result)

        setupSpinner(spinnerAnaemia, R.array.anaemia_options)
        setupSpinner(spinnerHighBloodPressure, R.array.high_blood_pressure_options)
        setupSpinner(spinnerSex, R.array.sex_options)

        setupNumberInput(etAge, findViewById(R.id.btn_age_minus), findViewById(R.id.btn_age_plus), 0f, 100f, 1f, 0)
        setupNumberInput(etEjectionFraction, findViewById(R.id.btn_ejection_fraction_minus), findViewById(R.id.btn_ejection_fraction_plus), 0f, 100f, 1f, 0)
        setupNumberInput(etSerumCreatinine, findViewById(R.id.btn_serum_creatinine_minus), findViewById(R.id.btn_serum_creatinine_plus), 0.5f, 10.0f, 0.01f, 2)
        setupNumberInput(etSerumSodium, findViewById(R.id.btn_serum_sodium_minus), findViewById(R.id.btn_serum_sodium_plus), 100f, 200f, 1f, 0)
        setupNumberInput(etFollowUpTime, findViewById(R.id.btn_follow_up_time_minus), findViewById(R.id.btn_follow_up_time_plus), 0f, 365f, 1f, 0)

        try {
            tflite = Interpreter(loadModelFile(this, modelPath))
        } catch (e: IOException) {
            Toast.makeText(this, getString(R.string.toast_model_load_failed, e.message), Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }

        btnPredict.setOnClickListener {
            performPrediction()
        }
    }

    private fun setupSpinner(spinner: Spinner, arrayResId: Int) {
        val adapter = ArrayAdapter.createFromResource(this, arrayResId, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    private fun setupNumberInput(
        editText: EditText,
        minusButton: ImageButton,
        plusButton: ImageButton,
        minValue: Float,
        maxValue: Float,
        stepValue: Float,
        decimalPlaces: Int
    ) {
        val initialValue = editText.text.toString().toFloatOrNull()
        if (initialValue == null || initialValue < minValue || initialValue > maxValue) {
            editText.setText(String.format(Locale.getDefault(), "%.${decimalPlaces}f", minValue))
        }

        minusButton.setOnClickListener {
            var currentValue = editText.text.toString().toFloatOrNull() ?: minValue
            currentValue -= stepValue
            if (currentValue < minValue) currentValue = minValue
            editText.setText(String.format(Locale.getDefault(), "%.${decimalPlaces}f", currentValue))
        }

        plusButton.setOnClickListener {
            var currentValue = editText.text.toString().toFloatOrNull() ?: minValue
            currentValue += stepValue
            if (currentValue > maxValue) currentValue = maxValue
            editText.setText(String.format(Locale.getDefault(), "%.${decimalPlaces}f", currentValue))
        }
    }

    @Throws(IOException::class)
    private fun loadModelFile(activity: AppCompatActivity, modelPath: String): MappedByteBuffer {
        val fileDescriptor: AssetFileDescriptor = activity.assets.openFd(modelPath)
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }

    private fun performPrediction() {
        cardPredictionResult.visibility = View.GONE

        if (etAge.text.isNullOrBlank() ||
            etEjectionFraction.text.isNullOrBlank() ||
            etSerumCreatinine.text.isNullOrBlank() ||
            etSerumSodium.text.isNullOrBlank() ||
            etFollowUpTime.text.isNullOrBlank()
        ) {
            Toast.makeText(this, getString(R.string.toast_fill_all_fields), Toast.LENGTH_SHORT).show()
            return
        }

        if (tflite == null) {
            Toast.makeText(this, getString(R.string.toast_model_not_loaded), Toast.LENGTH_SHORT).show()
            return
        }

        try {
            val age = etAge.text.toString().toFloat()
            val ejectionFraction = etEjectionFraction.text.toString().toFloat()
            val serumCreatinine = etSerumCreatinine.text.toString().toFloat()
            val serumSodium = etSerumSodium.text.toString().toFloat()
            val followUpTime = etFollowUpTime.text.toString().toFloat()
            val anaemia = if (spinnerAnaemia.selectedItem.toString() == getString(R.string.option_ada)) 1f else 0f
            val highBloodPressure = if (spinnerHighBloodPressure.selectedItem.toString() == getString(R.string.option_ada)) 1f else 0f
            val sex = if (spinnerSex.selectedItem.toString() == getString(R.string.option_male)) 1f else 0f

            val inputBuffer = ByteBuffer.allocateDirect(8 * 4)
            inputBuffer.order(ByteOrder.nativeOrder())
            inputBuffer.putFloat(age)
            inputBuffer.putFloat(ejectionFraction)
            inputBuffer.putFloat(serumCreatinine)
            inputBuffer.putFloat(serumSodium)
            inputBuffer.putFloat(followUpTime)
            inputBuffer.putFloat(anaemia)
            inputBuffer.putFloat(highBloodPressure)
            inputBuffer.putFloat(sex)

            val outputBuffer = ByteBuffer.allocateDirect(1 * 4)
            outputBuffer.order(ByteOrder.nativeOrder())
            tflite?.run(inputBuffer, outputBuffer)
            outputBuffer.rewind()
            val predictionResult = outputBuffer.getFloat(0)
            displayPredictionResult(predictionResult)

        } catch (e: Exception) {
            Toast.makeText(this, getString(R.string.error_prediction_failed, e.message), Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }

    private fun displayPredictionResult(result: Float) {
        cardPredictionResult.visibility = View.VISIBLE
        val isDead = result >= 0.5f
        if (isDead) {
            tvPredictionResult.text = getString(R.string.prediction_result_dead_text)
            ivPredictionIcon.setImageResource(R.drawable.ic_cancel_white)
            cardPredictionResult.setCardBackgroundColor(resources.getColor(R.color.prediction_failure_background, theme))
            tvPredictionResult.setTextColor(resources.getColor(R.color.prediction_text_color_failure, theme))
        } else {
            tvPredictionResult.text = getString(R.string.prediction_result_not_dead_text)
            ivPredictionIcon.setImageResource(R.drawable.ic_check_circle_white)
            cardPredictionResult.setCardBackgroundColor(resources.getColor(R.color.prediction_success_background, theme))
            tvPredictionResult.setTextColor(resources.getColor(R.color.prediction_text_color_success, theme))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        tflite?.close()
    }
}
