package com.example.southparkapplication

import android.os.Build
import android.os.Bundle
import android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.southparkapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerview: RecyclerView
    private lateinit var characterList: ArrayList<CharactersData>
    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
        }


        recyclerview = findViewById(R.id.recyclerView)
        recyclerview.setHasFixedSize(true)

        characterList = arrayListOf()

        characterList.add(CharactersData("https://random.responsiveimages.io/",
            "Broflovsky", "Gerald", "Male"))
        characterList.add(CharactersData("https://random.responsiveimages.io/",
            "Broflovsky", "Sheila", "Female"))
        characterList.add(CharactersData("https://random.responsiveimages.io/",
            "Broflovsky", "Kyle", "Male"))
        characterList.add(CharactersData("https://random.responsiveimages.io/",
            "Broflovsky", "Iyk", "Male"))

        binding.recyclerView.adapter = CharacterViewAdapter(characterList,this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        window.navigationBarColor = getResources().getColor(R.color.squid_ink)
        window.decorView.getWindowInsetsController()?.setSystemBarsAppearance(0, APPEARANCE_LIGHT_STATUS_BARS)

    }

}