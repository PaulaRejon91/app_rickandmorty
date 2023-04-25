package com.example.app_rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.example.app_rickandmorty.data.Model.GetCharacterByIdResponse
import com.example.app_rickandmorty.data.network.RickAndMortyService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import android.widget.ImageView
import android.view.LayoutInflater

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val inflater = LayoutInflater.from(this)
        val mainLayout = inflater.inflate(R.layout.activity_main, null)


        val textView = findViewById<TextView>(R.id.tvName)
        val headerImageView = findViewById<ImageView>(R.id.headerImageView)
        val aliveTextView = findViewById<TextView>(R.id.aliveTextView)
        val originTextView = findViewById<TextView>(R.id.originTextView)
        val speciesTextView = findViewById<TextView>(R.id.speciesTextView)
        val genderImageView = findViewById<ImageView>(R.id.genderImageView)


        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        val personajesApiClient: RickAndMortyService =retrofit.create(RickAndMortyService::class.java)

        personajesApiClient.getPersonajesById(10).enqueue(object: Callback<GetCharacterByIdResponse> {
            override  fun onResponse(call: Call<GetCharacterByIdResponse>, response: Response<GetCharacterByIdResponse>) {
                Log.i("MainActivity", response.toString())

                if(!response.isSuccessful){
                    Toast.makeText(this@MainActivity,
                        "Unsuccessful network call!",
                        Toast.LENGTH_SHORT
                    ).show()
                    return
                }
                val body = response.body()!!
                textView.text = body.name
                aliveTextView.text = body.status
                speciesTextView.text = body.species
                originTextView.text = body.origin.name

                if(body.gender.equals("male",true)){
                    genderImageView.setImageResource(R.drawable.ic_male_24)
                }else{
                    genderImageView.setImageResource(R.drawable.ic_female_24)
                }

                Picasso.get().load(body.image).into(headerImageView);

            }
            override fun onFailure(call: Call<GetCharacterByIdResponse>, t: Throwable) {
                Log.i("MainActivity",t.message?: "Null message")
            }
        })
    }
}

// retrofit + courutines =mvvm

