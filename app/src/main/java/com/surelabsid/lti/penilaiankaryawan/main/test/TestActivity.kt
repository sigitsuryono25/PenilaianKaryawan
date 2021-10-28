package com.surelabsid.lti.penilaiankaryawan.main.test

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.surelabsid.lti.penilaiankaryawan.R

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val checkELement = findViewById<Button>(R.id.checkELement)
        val hasNext = findViewById<TextView>(R.id.hasNext)

        val d = mutableListOf<String>()
        d.add("anyong")
        d.add("haseyp")
        d.add("anyong")
        d.add("haseyo")

        val iterator = d.listIterator()
        checkELement.setOnClickListener {
            if (iterator.hasNext())
                hasNext.text = hasNext.text.toString() + "\n" + iterator.next()
        }
    }
}