package com.example.exam1

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter( val itmlist: ArrayList<ProductModel>):RecyclerView.Adapter<ProductAdapter.ProductHolder>() {

    class ProductHolder(itemView: View) : RecyclerView.ViewHolder(itemView){


        var itemName1 : TextView = itemView.findViewById(R.id.txtproductNameSet)
        var itemPrice1 : TextView = itemView.findViewById(R.id.txtproductPriceSet)
        var itemImage1 : ImageView = itemView.findViewById(R.id.adimgImageSet)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {



        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.product_details,parent,false)
        return ProductHolder(itemView)


    }

    override fun getItemCount(): Int {
        return itmlist.size
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {


        val currentItem = itmlist[position]


        holder.itemName1.text = currentItem.itemName
        holder.itemPrice1.text = currentItem.itemRate


        val bytes = android.util.Base64.decode(currentItem.itemImage,
            android.util.Base64.DEFAULT)
        val bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.size)
        holder.itemImage1.setImageBitmap(bitmap)


    }

}