package com.example.exam1

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.exam1.databinding.AddActivityBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.io.ByteArrayOutputStream

class AddActivity : AppCompatActivity() {

    lateinit var binding: AddActivityBinding
    lateinit var reference: DatabaseReference
    var sImage: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    fun insert_data(view: View) {

        var itemName = binding.productName.text.toString()
        var itemRate = binding.productPrice.text.toString()

        reference = FirebaseDatabase.getInstance().getReference("items")

        if (itemName.isEmpty()) {
            Toast.makeText(this, "please enter product name", Toast.LENGTH_SHORT).show()
        } else if (itemRate.isEmpty()) {
            Toast.makeText(this, "please enter product price", Toast.LENGTH_SHORT).show()
        } else {

            val item = ProductModel(itemName, itemRate, sImage)
            val databaseReference = FirebaseDatabase.getInstance().reference
            val id = databaseReference.push().key
            reference.child(id.toString()).setValue(item).addOnSuccessListener {

                binding.productName.text.clear()
                binding.productPrice.text.clear()
                Toast.makeText(this, "data inserted", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener {

                Toast.makeText(this, "data not inserted", Toast.LENGTH_SHORT).show()

            }

        }


    }

    fun insert_image(view: View) {


        var myfileintent = Intent(Intent.ACTION_GET_CONTENT)
        myfileintent.setType("image/*")
        ActivityResultLauncher.launch(myfileintent)

    }

    private val ActivityResultLauncher = registerForActivityResult<Intent, ActivityResult>(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->

        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            val uri = result.data!!.data
            try {

                val inputStreem =contentResolver.openInputStream(uri!!)
                val myBitmap = BitmapFactory.decodeStream(inputStreem)
                val stream = ByteArrayOutputStream()
                myBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
                val bytes = stream.toByteArray()
                sImage = android.util.Base64.encodeToString(bytes, android.util.Base64.DEFAULT)
                binding.imgSelect.setImageBitmap(myBitmap)
                inputStreem!!.close()
                Toast.makeText(this, "Image Selected", Toast.LENGTH_SHORT).show()

            } catch (ex: Exception) {
                Toast.makeText(this, ex.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }

    }

}