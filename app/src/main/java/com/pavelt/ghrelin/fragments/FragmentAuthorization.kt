package com.pavelt.ghrelin.fragments

import android.os.Build.VERSION_CODES.P
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.pavelt.ghrelin.R
import com.pavelt.ghrelin.userRepository.UserRepository
import com.pavelt.ghrelin.userRepository.UserRepositoryImpl

class FragmentAuthorization : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_authorization, container, false)
        val btnJoin = view.findViewById<Button>(R.id.btnEnter)
        val repository = UserRepositoryImpl()

        btnJoin.setOnClickListener {
            val login = view.findViewById<EditText>(R.id.userLoginTV).text.toString()
            val password = view.findViewById<EditText>(R.id.userPasswordTV).text.toString()

            val userId = repository.findUserBy(login, password)

            if (userId != null) {
                val bundle = bundleOf("id" to userId)
                findNavController().navigate(R.id.fragmentPersonalArea, bundle)
            } else {
                Toast.makeText(requireContext(), "Неверный логин или пароль", Toast.LENGTH_LONG)
                    .show()
            }
        }
        return view
    }
}
//        if (etLogin == "login" && etPassword == "password") {
//            findNavController().navigate(R.id.fragmentCatalog, null)
//        } else {
//            Toast.makeText(requireContext(), "Неверный логин или пароль", Toast.LENGTH_LONG)
//                .show()
//        }