package com.example.cipher3

import java.math.BigInteger

class RSA {
    val chararray = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя ".toCharArray();


    fun isTheNumberSimple(n : Long): Boolean{
        if(n < 2L) return false
        if (n == 2L) return true

        for(i in 2 until n)
            if(n % i == 0L) return false

        return true
    }


    fun encode(s: String , e: Long, n: Long) : List<String>{
        var result = ArrayList<String>();

        var bi : BigInteger

        for(i in s.indices){
            val index: Int = chararray.indexOf(s[i])

            bi = BigInteger(index.toString())
            bi = bi.pow(e.toInt())

            val n_ = BigInteger(n.toString())

            bi = bi.mod(n_)

            println("add ${bi.toString()}" )
            result.add(bi.toString())
        }

        return result;
    }

    fun decode(input: List<String>, d: Long, n: Long): String{
        var result  = ""

        var bi : BigInteger

        for (item in input){
            bi = BigInteger(item)
            bi = bi.pow(d.toInt())

            val n_ = BigInteger(n.toString())
            bi = bi.mod(n_)

            var index = bi.toString().toInt()
            println("decode ${index}")
            result += chararray[index]
        }

        return result
    }

    fun calculate_e(d : Long , m: Long) : Long{
        var e = 10L

        while (true){
            if((e*d) % m ==1L)
                break
            else
                e++;
        }
        return e;
    }

    fun calculate_d(m: Long) : Long{
        var d = m-1;

        var i = 2;
        while(i <= m) {
            if ((m % i == 0L) && (d % i == 0L)) {
                d--;
                i = 1;
            }
            i++;
        }

        return d;
    }
}
