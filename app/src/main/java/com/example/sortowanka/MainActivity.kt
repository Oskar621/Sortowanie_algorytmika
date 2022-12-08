package com.example.sortowanka

import android.annotation.SuppressLint
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

    fun quickSort(array: IntArray, left: Int, right: Int) {
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

    fun maxheapify(array: MutableList<Int>, heapSize: Int, index: Int) {
        val left = 2 * index + 1
        val right = 2 * index + 2
        var largest = index

        if(left < heapSize && array[left] > array[largest])
            largest = left
        if(right < heapSize && array[right] > array[largest])
            largest = right
        if(largest != index) {
            swapArray(array.toIntArray(), index, largest)
            maxheapify(array, heapSize, largest)
        }
    }
    fun buildMaxHeap(array: MutableList<Int>) {
        val n = array.size
        for(i in (n / 2 - 1) downTo 0)
            maxheapify(array, n, i)
    }
    fun transformMaxHeapToSortedArray(array: MutableList<Int>) {
        for(i in (array.size - 1) downTo 0) {
            swapArray(array.toIntArray(), i, 0)
            maxheapify(array, i, 0)
        }
    }

    fun merge(array: MutableList<Int>, left: Int, right: Int, division: Int) {
        val mergedLength = right - left
        val merged = Array(mergedLength){0}
        var index1 = left
        var index2 = division

        for(i in 0 until mergedLength) {
            if(index1 >= division) {
                merged[i] = array[index2]
                index2++
            } else if(index2 >= right) {
                merged[i] = array[index1]
                index1++
            } else if(array[index1] <= array[index2]) {
                merged[i] = array[index1]
                index1++
            } else {
                merged[i] = array[index2]
                index2++
            }
        }

        for(i in left until right) {
            array[i] = merged[i - left]
        }
    }
    fun mergeSort(array: MutableList<Int>, left: Int, right: Int) {
        if(right - left <= 1) {
            return
        }

        val division = (left + right) / 2
        mergeSort(array, left, division)
        mergeSort(array, division, right)

        merge(array, left, right, division)
    }



    @SuppressLint("SetTextI18n")
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
                val sw : Stopwatch = Stopwatch.createStarted()
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
                sw.stop()
                val elapsedwstawianie: Long = sw.elapsed(TimeUnit.NANOSECONDS)
                czasWstawianie.text = "$elapsedwstawianie ms"


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
                sw2.stop()
                val elapsedbubble = sw2.elapsed(TimeUnit.NANOSECONDS)
                czasBubble.text = "$elapsedbubble ms"

                //sortowanie szybkie

                val sw3: Stopwatch = Stopwatch.createStarted()

                for (r in 1 until iloscRazy.toString().toInt())
                {
                    quickSort(tablica.toIntArray(),0,tablica.size-1)
                    tablica = liczby
                }
                sw3.stop()
                val elapsedquick = sw3.elapsed(TimeUnit.NANOSECONDS)
                czasFast.text = "$elapsedquick ms"

                // heapsort

                val sw4: Stopwatch = Stopwatch.createStarted()
                for(r in 1 until iloscRazy.toString().toInt())
                {
                    buildMaxHeap(tablica)
                    transformMaxHeapToSortedArray(tablica)
                    tablica = liczby
                }
                sw4.stop()
                val elapsedheap = sw4.elapsed(TimeUnit.NANOSECONDS)
                czasHeapsort.text = "$elapsedheap ms"


                // sortowanie przez scalanie

                val sw5: Stopwatch = Stopwatch.createStarted()
                for (r in 1 until iloscRazy.toString().toInt())
                {
                    mergeSort(tablica, 0, tablica.size)
                    tablica = liczby
                }
                sw5.stop()
                val elapsedscal = sw5.elapsed(TimeUnit.NANOSECONDS)
                czasScal.text = "$elapsedscal ms"
            }
        }
    }
}