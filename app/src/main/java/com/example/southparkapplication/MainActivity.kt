package com.example.southparkapplication

import android.os.Build
import android.os.Bundle
import android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.southparkapplication.databinding.ActivityMainBinding
import com.example.southparkapplication.retrofit.RetroInstance
import com.example.southparkapplication.dataModels.Data
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {
   // private lateinit var recyclerview: RecyclerView
    private lateinit var recyclerViewAdapter: CharacterViewAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var charactersList: List<Data>

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        charactersList = listOf()

//        recyclerview = findViewById(R.id.recyclerView)
//        recyclerview.setHasFixedSize(true)

        GlobalScope.launch(Dispatchers.IO) {
            val response = try {
            RetroInstance.api.getCharacterList()
            } catch (e: IOException) {
                Toast.makeText(applicationContext,"app error ${e.message}",Toast.LENGTH_SHORT).show()
                return@launch
            }
            catch (e: HttpException){
                Toast.makeText(applicationContext,"http error ${e.message}",Toast.LENGTH_SHORT).show()
                return@launch
            }
            if(response.isSuccessful && response.body() != null) {
                withContext(Dispatchers.Main){
                    charactersList = response.body()!!
                    binding.recyclerView.apply {
                        recyclerViewAdapter = CharacterViewAdapter(this@MainActivity,charactersList)
                        adapter = recyclerViewAdapter
                        layoutManager = LinearLayoutManager(this@MainActivity)
                    }
                }
            }
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        window.navigationBarColor = getResources().getColor(R.color.squid_ink)
        window.decorView.getWindowInsetsController()?.setSystemBarsAppearance(0, APPEARANCE_LIGHT_STATUS_BARS)

    }
}

