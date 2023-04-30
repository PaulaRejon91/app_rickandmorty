package com.example.app_rickandmorty.ui.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.example.app_rickandmorty.R
import com.example.app_rickandmorty.ui.viewmodel.CharacterViewModel

class CharacterActivity : AppCompatActivity() {

    val viewModel: CharacterViewModel by lazy {
        ViewModelProvider(this).get(CharacterViewModel::class.java)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//       val inflater = LayoutInflater.from(this)
//       val mainLayout = inflater.inflate(R.layout.activity_main, null)

        val textView = findViewById<TextView>(R.id.tvName)
        val headerImageView = findViewById<ImageView>(R.id.headerImageView)
        val aliveTextView = findViewById<TextView>(R.id.aliveTextView)
        val originTextView = findViewById<TextView>(R.id.originTextView)
        val speciesTextView = findViewById<TextView>(R.id.speciesTextView)
        val genderImageView = findViewById<ImageView>(R.id.genderImageView)


        viewModel.refreshCharacter(14)
        viewModel.characterByIdLiveData.observe(this) { response ->
            if (response == null) {
                Toast.makeText(
                    this@CharacterActivity,
                    "llamada sin éxito!",
                    Toast.LENGTH_SHORT
                ).show()
                return@observe
            }

            textView.text = response.name
            aliveTextView.text = response.status
            speciesTextView.text = response.species
            originTextView.text = response.origin.name

            if (response.gender.equals("male", true)) {
                genderImageView.setImageResource(R.drawable.ic_male_24)
            } else {
                genderImageView.setImageResource(R.drawable.ic_female_24)
            }

            Picasso.get().load(response.image).into(headerImageView);

        }

    }
}



