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

            fun quickSort(array: MutableList<Int>, left: Int, right: Int) {
                val index = partition (array, left, right)
                if(left < index-1) { // 2) Sorting left half
                    quickSort(array, left, index-1)
                }
                if(index < right) { // 3) Sorting right half
                    quickSort(array,index, right)
                }
            }

            fun swapArray(a: IntArray, b: Int, c: Int) {
                val temp = a[b]
                a[b] = a[c]
                a[c] = temp
            }
            fun partition(array: IntArray, l: Int, r: Int): Int {
                var left = l
                var right = r
                val pivot = array[(left + right)/2] // 4) Pivot Point
                while (left <= right) {
                    while (array[left] < pivot) left++ // 5) Find the elements on left that should be on right

                    while (array[right] > pivot) right-- // 6) Find the elements on right that should be on left

                    // 7) Swap elements, and move left and right indices
                    if (left <= right) {
                        swapArray(array, left,right)
                        left++
                        right--
                    }
                }
                return left
            }


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
                    tablica = liczby
                }
                elapsedMillis = sw2.elapsed(TimeUnit.NANOSECONDS)
                czasBubble.text = "$elapsedMillis ns"

                //sortowanie szybkie

                val sw3: Stopwatch = Stopwatch.createStarted()

                for (r in 1 until iloscRazy.toString().toInt())
                {
                    quickSort(tablica,0,tablica.size-1)
                    tablica = liczby
                }
                val elaspedMillis = sw3.elapsed(TimeUnit.NANOSECONDS)
                czasFast.text = "$elapsedMillis ns"
                Toast.makeText(applicationContext, tablica.toString(), Toast.LENGTH_SHORT)
            }

        }
    }
}