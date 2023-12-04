package com.valencio.pawsomepets.models

import java.io.Serializable

/** First API Call Model Class : Getting the Dog List */
data class PetsDataClass(
    val weight: HeightWeight?,
    val height: HeightWeight?,
    val id: Int?,
    val name: String?,
    val bred_for: String?,
    val breed_group: String?,
    val life_span: String?,
    val temperament: String?,
    val origin: String?,
    val reference_image_id: String?
) : Serializable

data class HeightWeight(
    val imperial: String?,
    val metric: String?
) : Serializable

/** Second API Call Model Class : Getting the Dog Details */
data class PetSelected(
    val id: String?,
    val url: String?,
    val breeds: List<PetsDataClass>,
    val width: Int?,
    val height: Int?
) : Serializable