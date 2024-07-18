package com.example.southparkapplication

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.southparkapplication.databinding.ActivityMainBinding
import com.example.southparkapplication.viewModel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerview: RecyclerView
    private lateinit var recyclerViewAdapter: CharacterViewAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

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

//        characterList = listOf(CharactersData(
//            "https://static.wikia.nocookie.net/spsot/images/e/e2/Gerald_Broflovski_facebook_profile.png/",
//            "Broflovsky", "Gerald", "Male"),
//            CharactersData(
//                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRVRp7h-9P1VwPYk8gqpfK3AR8CAHVDqCqbRw&s",
//            "Broflovsky", "Sheila", "Female"),
//            CharactersData(
//                "https://static.displate.com/270x380/displate/2023-12-07/57b46d7dfe39f822d4767852af1de490_eab249c39a235766de1a637fe9863e21.jpg",
//                "Broflovsky", "Kyle", "Male"),
//            CharactersData(
//                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRxVup2yXKwQvjHxItWohdUPN7bSbIMnarIEA&s",
//                "Broflovsky", "Ike", "Male")
//            )
            recyclerViewAdapter = CharacterViewAdapter(this)
            binding.recyclerView.adapter = recyclerViewAdapter

        viewModel = ViewModelProvider(this)[(MainActivityViewModel::class.java)]
        viewModel.getLiveDataObserver().observe(this) {
            recyclerViewAdapter.setCharacterList(it)
            recyclerViewAdapter.notifyDataSetChanged()
        }
        viewModel.makeAPICall()


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        window.navigationBarColor = getResources().getColor(R.color.squid_ink)
        window.decorView.getWindowInsetsController()?.setSystemBarsAppearance(0, APPEARANCE_LIGHT_STATUS_BARS)

    }
}

