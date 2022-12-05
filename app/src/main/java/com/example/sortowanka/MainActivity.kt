package com.example.sortowanka

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val liczby: MutableList<Int> = mutableListOf()
        val czasWstawianie = findViewById<TextView>(R.id.czas_wstawianie)
        val czasBubble = findViewById<TextView>(R.id.czas_bubble)
        val czasFast = findViewById<TextView>(R.id.czas_fast)
        val czasHeapsort = findViewById<TextView>(R.id.czas_heapsort)
        val czasScal = findViewById<TextView>(R.id.czas_scal)
        val iloscRazy = findViewById<EditText>(R.id.ile_razy).text
        val iloscElementow = findViewById<EditText>(R.id.ile_elementow).text
        val sortuj = findViewById<Button>(R.id.sortuj)

        sortuj.setOnClickListener {
            //czyszczenie listy
            liczby.clear()
            //losowanie liczb do listy
            if(iloscElementow.isNotEmpty() && iloscRazy.isNotEmpty()) {
                if(iloscElementow.toString().toInt() >= 1)
                for (i in 1..iloscElementow.toString().toInt())
                    liczby.add(Random.nextInt(1,50))

            //sortowanie przez wstawianie
            for(i in 1 .. liczby.size+1)
            {
                val pom = liczby[i]
                var j = i-1
                while (j>=0 && liczby[j]>pom)
                {
                    liczby[j+1] = liczby[j]
                    j-=1
                }
                liczby[j+1] = pom
            }
                Toast.makeText(applicationContext, liczby.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}