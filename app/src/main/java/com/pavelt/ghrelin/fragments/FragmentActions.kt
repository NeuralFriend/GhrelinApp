package com.pavelt.ghrelin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.pavelt.ghrelin.R

class FragmentActions : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_actions, container, false)

        val btnLogIn = view.findViewById<ImageButton>(R.id.btnLogIn)
        val btnMenu = view.findViewById<ImageButton>(R.id.btnMenu)

        val imgUrlMenu ="https://static.tildacdn.com/tild3766-3138-4761-a434-623234653162/228-2286261_canteen-.png"
        val imgUrlLogin = "https://img2.freepng.ru/20180714/wyo/kisspng-teplogidrostroy-password-manager-admin-icon-5b4a38cb08a5b6.1280845915315908590354.jpg"

        Glide.with(this)
            .load(imgUrlMenu)
            .apply(RequestOptions().centerCrop())
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(btnMenu)

        Glide.with(this)
            .load(imgUrlLogin)
            .apply(RequestOptions().centerCrop())
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(btnLogIn)

        btnMenu.setOnClickListener {
            findNavController().navigate(R.id.fragmentNonauthorizedMenu)
        }

        btnLogIn.setOnClickListener {
            findNavController().navigate(R.id.fragmentAuthorization)
        }

        return view
    }
}