package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightText = findViewById<EditText>(R.id.etWeight)
        val heightText = findViewById<EditText>(R.id.etHeight)
        val calcButton = findViewById<Button>(R.id.btnCalculate)

        calcButton.setOnClickListener{
            val weight = weightText.text.toString()
            val height = heightText.text.toString()

            //Code to call and and implement validateInput function

            if (validateInput(weight,height)) {

                // Formula to get Body to mass Index.
                // The height was divided by 100 to get convert the height from Metres to Centimetres
                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))

                //To get the result into two decimal place, we write the code below:
                val bmi2Digit = String.format("%.2f", bmi).toFloat()

                //Calling the function to display the result
                displayResult(bmi2Digit)
            }



        }
    }

    // Code to validate user Input to prevent errors.
    private fun validateInput(weight:String?, height:String?):Boolean{
        return when{
            weight.isNullOrEmpty()->{
                Toast.makeText(this, "Weight is empty",Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty()->{
                Toast.makeText(this, "Height is empty",Toast.LENGTH_LONG).show()
                return false
            }
            else ->
                true
        }
    }
    // Code to tell what the calculate button will don when clicked.
    private fun displayResult(bmi:Float){
        val resultIndex = findViewById<TextView>(R.id.tvIndex)
        val resultDescription = findViewById<TextView>(R.id.tvresult)
        val info = findViewById<TextView>(R.id.tvInfo)

        //Code to display the result
        resultIndex.text = bmi.toString()
        //Code to display information
        info.text = "(Normal range is 18.4 - 24.9)"

        //Code to assign colours to each of the results
        var resultText = ""
        var color = 0

        when {
            bmi<18.50 -> {
               resultText = "Underweight"
                color = R.color.under_weight
            }
            bmi in 18.50..24.99 -> {
                resultText = "Healthy"
                color = R.color.normal
            }
            bmi in 25.00..29.99 -> {
                resultText = "Overweight"
                color = R.color.over_weight
            }
            bmi > 30.00 -> {
                resultText = "Obese"
                color = R.color.obese
            }

    }
        //Code to change the result description colour
        resultDescription.setTextColor(ContextCompat.getColor(this,color))
        resultDescription.text = resultText
    }
}