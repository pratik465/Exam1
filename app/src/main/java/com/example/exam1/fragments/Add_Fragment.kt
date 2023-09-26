package com.example.exam1.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.exam1.AddActivity
import com.example.exam1.databinding.FragmentAddBinding
import com.google.firebase.database.DatabaseReference


class Add_Fragment : Fragment() {
    lateinit var binding: FragmentAddBinding
    lateinit var reference: DatabaseReference
    var sImage: String? = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddBinding.inflate(layoutInflater)

        binding.btnsendActivity.setOnClickListener {

            var intent = Intent(context,AddActivity::class.java)
            startActivity(intent)

        }

        return binding.root
    }

}