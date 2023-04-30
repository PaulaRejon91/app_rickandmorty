package com.example.app_rickandmorty.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.example.app_rickandmorty.R
import com.example.app_rickandmorty.ui.viewmodel.CharacterViewModel
import com.example.app_rickandmorty.ui.viewmodel.MainActivityViewModel
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.app_rickandmorty.data.Model.CharacterResponse



class MainActivity : AppCompatActivity() {

    val viewModel: MainActivityViewModel by lazy {
        ViewModelProvider(this).get(MainActivityViewModel::class.java)
    }

    val respuesta = CharacterResponse()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvRickAndMorty = findViewById<RecyclerView>(R.id.rvRickAndMorty)

//        rvRickAndMorty.layoutManager = LinearLayoutManager(this)
//        rvRickAndMorty.adapter = MainAdapter()

        viewModel.refreshList()
        viewModel.characterListLiveData?.observe(this) { response ->
            if (response == null) {
                Toast.makeText(
                    this@MainActivity,
                    "llamada sin éxito!",
                    Toast.LENGTH_SHORT
                ).show()
                return@observe
            }

        }
    }
}





