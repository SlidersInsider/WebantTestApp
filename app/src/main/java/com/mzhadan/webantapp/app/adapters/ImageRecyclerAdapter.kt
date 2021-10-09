package com.mzhadan.webantapp.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.mzhadan.webantapp.ImageListItem
import com.mzhadan.webantapp.R
import com.mzhadan.webantapp.app.fragments.ImageInfoFragment
import com.squareup.picasso.Picasso

class ImageRecyclerAdapter(containerFragmentManager: FragmentManager): RecyclerView.Adapter<ImageRecyclerAdapter.ImageViewHolder>() {

    private val imagesList: ArrayList<ImageListItem> = ArrayList()
    private var myFragmentManager: FragmentManager = containerFragmentManager

    fun setData(newImages: List<ImageListItem>){
        imagesList.clear()
        imagesList.addAll(newImages)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item, parent, false), myFragmentManager)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(model = imagesList[position])
    }

    override fun getItemCount(): Int {
        return imagesList.size
    }

    class ImageViewHolder(itemView: View, containerFragmentManager: FragmentManager): RecyclerView.ViewHolder(itemView){
        private val image: ImageView = itemView.findViewById(R.id.singleImageView)
        private val manager: FragmentManager = containerFragmentManager

        fun bind(model: ImageListItem){
            Picasso.get().load("http://gallery.dev.webant.ru/media/" + model.image.imageUrl).into(image)

            image.setOnClickListener {
                manager.beginTransaction().replace(R.id.mainFragmentContainer, ImageInfoFragment(model)).commit()
            }
        }
    }
}