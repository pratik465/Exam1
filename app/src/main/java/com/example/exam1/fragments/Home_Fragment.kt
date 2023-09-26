package com.example.exam1.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exam1.ProductAdapter
import com.example.exam1.ProductModel
import com.example.exam1.R
import com.example.exam1.databinding.FragmentHomeBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Home_Fragment : Fragment() {

    lateinit var reference: DatabaseReference
    lateinit var recyclerView: RecyclerView
    lateinit var itemlist: ArrayList<ProductModel>
    lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater)

        recyclerView = binding.rcvProduct
        recyclerView.layoutManager = GridLayoutManager(context,2)
        recyclerView.hasFixedSize()
        itemlist = arrayListOf<ProductModel>()

        getItemData()


        return binding.root
    }

    private fun getItemData() {


        reference = FirebaseDatabase.getInstance().getReference("items")



        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {

                    for (itemdata in snapshot.children) {
                        val item = itemdata.getValue(ProductModel::class.java)
                        itemlist.add(item!!)
                    }

                    recyclerView.adapter = ProductAdapter(itemlist)
                }

            }

            override fun onCancelled(error: DatabaseError) {


            }


        })
    }
}