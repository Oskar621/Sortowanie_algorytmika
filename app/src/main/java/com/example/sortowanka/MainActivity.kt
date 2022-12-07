package com.example.sortowanka

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Stopwatch
import java.util.concurrent.TimeUnit
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
            if (iloscElementow.isNotEmpty() && iloscRazy.isNotEmpty()) {
                if (iloscElementow.toString().toInt() >= 1)
                    for (i in 1..iloscElementow.toString().toInt())
                        liczby.add(Random.nextInt(1, 50))

                //sortowanie przez wstawianie
                var sw : Stopwatch = Stopwatch.createStarted()
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
                var elapsedMillis: Long = sw.elapsed(TimeUnit.NANOSECONDS)
                czasWstawianie.text = "$elapsedMillis ns"


                //sortowanie babelkowe

                val sw2: Stopwatch = Stopwatch.createStarted()
                for (r in 1 .. iloscRazy.toString().toInt())
                {
                    for(currentPosition in 0 until (tablica.size -1)) {
                        if( tablica[currentPosition] > tablica[currentPosition+1]) {
                            val tmp = tablica[currentPosition]
                            tablica[currentPosition] = tablica[currentPosition+1]
                            tablica[currentPosition+1] = tmp
                        }
                    }
                    Toast.makeText(applicationContext, tablica.toString(), Toast.LENGTH_SHORT)
                    tablica = liczby
                }
                elapsedMillis = sw2.elapsed(TimeUnit.NANOSECONDS)
                czasBubble.text = "$elapsedMillis ns"


            }


        }
    }
}