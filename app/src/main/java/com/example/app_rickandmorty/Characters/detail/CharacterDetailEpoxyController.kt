package com.example.app_rickandmorty.Characters.detail

import com.airbnb.epoxy.EpoxyController
import com.dmp.simplemorty.epoxy.ViewBindingKotlinModel
import com.example.app_rickandmorty.R
import com.example.app_rickandmorty.data.Model.CharacterResponse
import com.example.app_rickandmorty.databinding.ModelCharacterDetailsDataPointBinding
import com.example.app_rickandmorty.databinding.ModelCharacterDetailsHeaderBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.DefaultExecutor.key
import kotlinx.coroutines.UndispatchedMarker.key

class CharacterDetailEpoxyController : EpoxyController() {

    var isLoading: Boolean = true
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }

    var characterResponse: CharacterResponse? = null
        set(value) {
            field = value
            if (field != null) {
                isLoading = false
                requestModelBuild()
            }
        }

    override fun buildModels() {

        if (isLoading) {
            //show loading state
            return
        }
        //header model
        HeaderEpoxyModel(
            name = characterResponse!!.name,
            gender = characterResponse!!.gender,
            status = characterResponse!!.status

        ).id("header").addTo(this)

        //image model
        ImageEpoxyModel(
            imageUrl = characterResponse!!.image
        ).id("image").addTo(this)

        //data point models
        DataPointEpoxyModel(
            title = "Origin",
            description = characterResponse!!.origin.name
        ).id("data_point_1").addTo(this)

        DataPointEpoxyModel(
            title = "Species",
            description = characterResponse!!.species
        ).id("data_point_2").addTo(this)

    }

    data class HeaderEpoxyModel(
        val name: String,
        val gender: String,
        val status: String
    ) : ViewBindingKotlinModel<ModelCharacterDetailsHeaderBinding>(R.layout.model_character_details_header) {

        override fun ModelCharacterDetailsHeaderBinding.bind() {
            nameTextView.text = name
            aliveTextView.text = status

            if (gender.equals("male", true)) {
                genderImageView.setImageResource(R.drawable.ic_male_24)
            } else {
                genderImageView.setImageResource(R.drawable.ic_female_24)
            }
        }
    }

    data class ImageEpoxyModel(
        val imageUrl: String
    ) : ViewBindingKotlinModel<ModelCharacterDetailsHeaderBinding>(R.layout.model_character_details_image) {

        override fun ModelCharacterDetailsHeaderBinding.bind() {
            Picasso.get().load(imageUrl).into(headerImageView)
        }
    }

    data class DataPointEpoxyModel(
        val title: String,
        val description: String
    ) : ViewBindingKotlinModel<ModelCharacterDetailsDataPointBinding>(R.layout.model_character_details_data_point) {

        override fun ModelCharacterDetailsDataPointBinding.bind() {
            labelTextView.text = title
            textView.text = description
        }
    }
}
}