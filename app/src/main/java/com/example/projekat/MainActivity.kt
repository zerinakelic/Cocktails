package com.example.projekat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
//todo: ERROR: baza se ne refreshuje kad se proslijedi neki drugi GET za BASE_URL, inače ok, ovo ne moramo hahaha

//todo: Dodati jos jedan fragment: SecondFragment i u njega prebaciti podatke - sliku i Instructions :
//    val strInstructions: String,  - instructions
//    val strDrinkThumb: String     - slika
//      iz Model-a

//todo: dizajn (malo uljepsati app)

//todo: filtriranje podataka

//todo: prosljeđivanje podataka putem textualne poruke - share button ??

//todo: App bar sa bar jednim item-om i up button-om : app bar za filtriranje (tri tackice), back button