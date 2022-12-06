package com.example.sortowanka

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
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
            if (iloscElementow.isNotEmpty() && iloscRazy.isNotEmpty()) {
                if (iloscElementow.toString().toInt() >= 1)
                    for (i in 1..iloscElementow.toString().toInt())
                        liczby.add(Random.nextInt(1, 50))

                //sortowanie przez wstawianie
                val c1: Calendar = Calendar.getInstance()
                c1.set(Calendar.MONTH, 11)
                c1.set(Calendar.DATE, 5)
                c1.set(Calendar.YEAR, 1996)
                val dateOne: Date = c1.getTime()
                var tablica: MutableList<Int> = mutableListOf()
                for(r in 1 ..iloscRazy.toString().toInt())
                {
                    for(j in tablica.size-2 downTo 0)
                    {
                        val x = tablica[j]
                        var i = j + 1
                        while ((i<tablica.size)&&(x>tablica[i]))
                        {
                            tablica[i-1] = tablica[i]
                            i+=1
                        }
                        tablica[i-1] = x
                    }
                    tablica = liczby
                }
                val dateTwo: Date = c1.getTime()
                val wynik: Int = dateTwo.toString().toInt() - dateOne.toString().toInt()
                czasWstawianie.text = wynik.toString()
            }
        }
    }
}