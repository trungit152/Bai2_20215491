package com.example.bai2_20215491

import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val editTextNumber = findViewById<EditText>(R.id.editTextNumber)
        val radioEven = findViewById<RadioButton>(R.id.radioEven)
        val radioOdd = findViewById<RadioButton>(R.id.radioOdd)
        val radioPerfectSquare = findViewById<RadioButton>(R.id.radioPerfectSquare)
        val buttonShow = findViewById<Button>(R.id.buttonShow)
        val textViewError = findViewById<TextView>(R.id.textViewError)
        val listViewResults = findViewById<ListView>(R.id.listViewResults)

        buttonShow.setOnClickListener {
            // Hide error text by default
            textViewError.visibility = TextView.GONE

            val inputText = editTextNumber.text.toString()

            // Validate input
            val n = inputText.toIntOrNull()
            if (n == null || n <= 0) {
                textViewError.text = "Please enter a valid positive integer."
                textViewError.visibility = TextView.VISIBLE
                return@setOnClickListener
            }

            // Determine the selected radio button and generate the appropriate list
            val results = when {
                radioEven.isChecked -> getEvenNumbers(n)
                radioOdd.isChecked -> getOddNumbers(n)
                radioPerfectSquare.isChecked -> getPerfectSquares(n)
                else -> emptyList()
            }

            // Display the results in the ListView
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, results)
            listViewResults.adapter = adapter
        }
    }
    // Function to get a list of even numbers from 0 to n
    private fun getEvenNumbers(n: Int): List<String> {
        return (0..n step 2).map { it.toString() }
    }

    // Function to get a list of odd numbers from 1 to n
    private fun getOddNumbers(n: Int): List<String> {
        return (1..n step 2).map { it.toString() }
    }

    // Function to get a list of perfect squares from 0 to n
    private fun getPerfectSquares(n: Int): List<String> {
        val results = mutableListOf<String>()
        var i = 0
        while (i * i <= n) {
            results.add((i * i).toString())
            i++
        }
        return results
    }
}