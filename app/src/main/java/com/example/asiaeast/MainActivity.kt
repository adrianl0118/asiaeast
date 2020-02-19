package com.example.asiaeast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.asiaeast.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //The two arrays for the spinner adapters. Cities is var because it depends on countries' selection.
        val countries = resources.getStringArray(R.array.countries)
        var cities = arrayOf("Please choose a country first")

        //Set the country spinner
        if (binding.countrySpinner != null) {
            val countryadapter = ArrayAdapter(this, R.layout.spinner_item, countries)
            countryadapter.setDropDownViewResource(R.layout.spinner_item)
            binding.countrySpinner.adapter = countryadapter
            binding.countrySpinner.setPrompt("Please Select")


            binding.countrySpinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    cities = when (countries[position]) {
                        "South Korea" -> resources.getStringArray(R.array.korean_cities)
                        "Japan" -> resources.getStringArray(R.array.japanese_cities)
                        "Taiwan" -> resources.getStringArray(R.array.taiwanese_cities)
                        "Thailand" -> resources.getStringArray(R.array.thai_cities)
                        "Vietnam" -> resources.getStringArray(R.array.vietnamese_cities)
                        "China" -> resources.getStringArray(R.array.chinese_cities)
                        else -> {
                            arrayOf("Please choose a country first")
                        }
                    }
                    Toast.makeText(this@MainActivity, getString(R.string.selected_item) + " " + "" + countries[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    Toast.makeText(this@MainActivity, "Please select a country", Toast.LENGTH_SHORT).show()
                }
            }
        }

        //Set the city spinner
        if (binding.citySpinner != null) {
            val cityadapter = ArrayAdapter(this, R.layout.spinner_item, countries)
            cityadapter.setDropDownViewResource(R.layout.spinner_item)
            binding.citySpinner.adapter = cityadapter
            binding.citySpinner.setPrompt("Please Select")

            binding.citySpinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    Toast.makeText(this@MainActivity, getString(R.string.selected_item) + " " + "" + cities[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    Toast.makeText(this@MainActivity, "Please select a city", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
