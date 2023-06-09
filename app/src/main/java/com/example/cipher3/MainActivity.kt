package com.example.cipher3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etP = findViewById<EditText>(R.id.etP)
        val etQ = findViewById<EditText>(R.id.etQ)
        val etD = findViewById<EditText>(R.id.etD)
        val etN = findViewById<EditText>(R.id.etN)
        val etE = findViewById<EditText>(R.id.etE)
        val etCode = findViewById<EditText>(R.id.etCode)
        val etDecode = findViewById<EditText>(R.id.etDecode)


        val btnCode = findViewById<Button>(R.id.code)
        val btnDecode = findViewById<Button>(R.id.decode)

        val rsa = RSA()

        btnCode.setOnClickListener {
            try {
                val p = etP.text.toString().toInt() * 1L
                val q = etQ.text.toString().toInt() * 1L
                val s = etCode.text.toString()

                val n = p * q
                val m = (p - 1) * (q - 1)
                val d = rsa.calculate_d(m)
                var e =3L
                while(rsa.gcd(e , m) != 1L){
                    e+=2
                }

                etDecode.setText(rsa.encode(s, e, n).joinToString(";"))
                etD.setText(d.toString())
                etN.setText(n.toString())
                etE.setText(e.toString())
            } catch (e: java.lang.Exception) {
                println(e.toString())
            }

        }

        btnDecode.setOnClickListener {
            try {
                val n = etN.text.toString().toInt() * 1L
                val d = etD.text.toString().toInt() * 1L
                val arrS = etDecode.text.toString().split(";")


                val answer = rsa.decode(arrS, d, n)
                etCode.setText(answer)

            } catch (e: java.lang.Exception) {
                e.stackTraceToString()
            }
        }
    }
}