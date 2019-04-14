package com.epam.training.androidoverview

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

/**
 * This class loads picture from gallery into [imageView] at a press of a [chooseImageButton].
 *
 * @author Alexandra Makovskaya
 */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chooseImageButton.setOnClickListener {
            pickImageFromGallery()
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK).apply {
            type = INTENT_TYPE_IMAGE
        }

        intent.resolveActivity(packageManager)?.let {
            startActivityForResult(intent, CHOOSE_IMAGE_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CHOOSE_IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val imageURI = data?.data
            val image = MediaStore.Images.Media.getBitmap(contentResolver, imageURI)
            imageView.setImageBitmap(image)
        } else {
            Toast.makeText(this, R.string.other_request_message, Toast.LENGTH_SHORT).show()
        }

    }

    private companion object {
        private const val CHOOSE_IMAGE_REQUEST_CODE = 1
        private const val INTENT_TYPE_IMAGE = "image/*"
    }

}
