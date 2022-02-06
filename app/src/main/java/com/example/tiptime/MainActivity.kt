package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.lang.Math.ceil
import java.lang.NumberFormatException
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener{ formattingIntoTips() }
    }

    private fun calculateTip() : Double {
        // Getting the text from TextView and converting to the Double
        val cost = binding.costOfService.text.toString().toDouble()

        // Getting the radio group value via ID
        val tipPercentage = when(binding.tipOptions.checkedRadioButtonId){
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        // Returning Final tips with or without rounding
        return when(binding.roundUpSwitch.isChecked) {
            true -> kotlin.math.ceil(cost * tipPercentage)
            else -> cost * tipPercentage
        }
    }
    private fun formattingIntoTips(tips : Double = calculateTip()){
        val formattedTips = NumberFormat.getCurrencyInstance().format(tips)
        binding.tipAnswer.text = getString(R.string.tip_amount, formattedTips)
    }

}