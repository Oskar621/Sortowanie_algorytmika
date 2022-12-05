package com.example.sortowanka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val liczby: MutableList<Int> = mutableListOf()
        val czas_wstawianie = findViewById<TextView>(R.id.czas_wstawianie)
        val czas_bubble = findViewById<TextView>(R.id.czas_bubble)
        val czas_fast = findViewById<TextView>(R.id.czas_fast)
        val czas_heapsort = findViewById<TextView>(R.id.czas_heapsort)
        val czas_scal = findViewById<TextView>(R.id.czas_scal)
        val ilosc_razy = findViewById<EditText>(R.id.ile_razy).text
        val ilosc_elementow = findViewById<EditText>(R.id.ile_elementow).text
        val sortuj = findViewById<Button>(R.id.sortuj)

        sortuj.setOnClickListener {
            liczby.clear()
            if(ilosc_elementow.isNotEmpty() && ilosc_razy.isNotEmpty()) {
                if(ilosc_elementow.toString().toInt() >= 1)
                for (i in 1..ilosc_elementow.toString().toInt())
                    liczby.add(Random.nextInt(1,50))
                Toast.makeText(applicationContext, liczby.toString(), Toast.LENGTH_SHORT).show()
            }

        }

}}