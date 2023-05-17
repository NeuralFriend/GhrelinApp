package com.pavelt.ghrelin.model

import android.graphics.Bitmap
import android.media.Image
import android.net.Uri

data class Food(
    val id: Int,
    val name: String,
    val category: String,
    val weight: String,
    val price: Int,
    val image: String
)