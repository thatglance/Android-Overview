package com.epam.training.androidoverview

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val CHOOSE_IMAGE_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chooseImageButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"

            if (intent.resolveActivity(packageManager) != null) {
                startActivityForResult(intent, CHOOSE_IMAGE_REQUEST_CODE)
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CHOOSE_IMAGE_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val imageURI = data?.data
                val image = MediaStore.Images.Media.getBitmap(contentResolver, imageURI)
                imageView.setImageBitmap(image)
            } else {
                Toast.makeText(this, "Other request", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
