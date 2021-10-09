package com.mzhadan.webantapp.app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.mzhadan.webantapp.ImageListItem
import com.mzhadan.webantapp.R
import com.squareup.picasso.Picasso

class ImageInfoFragment(item: ImageListItem) : Fragment() {

    private var imageItem: ImageListItem = item
    lateinit var imageView: ImageView
    lateinit var nameTextView: TextView
    lateinit var descriptionTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view: View = inflater.inflate(R.layout.image_info_fragment, container, false)

        imageView = view.findViewById(R.id.infoImageView)
        nameTextView = view.findViewById(R.id.nameTextView)
        descriptionTextView = view.findViewById(R.id.descriptionTextView)

        bindPageInfo(imageItem)

        return view
    }

    fun bindPageInfo(newImageItem: ImageListItem){
        Picasso.get().load("http://gallery.dev.webant.ru/media/" + newImageItem.image.imageUrl).into(imageView)
        nameTextView.setText("Name: " + newImageItem.name)
        descriptionTextView.setText("Description: " + newImageItem.description)
    }

}