package com.ideal.mobileexcercise.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ideal.mobileexcercise.R
import com.ideal.mobileexcercise.data.Photo
import com.ideal.mobileexcercise.databinding.CardviewPhotoBinding
import com.ideal.mobileexcercise.utils.IMAGE_SIDE_PX
import com.squareup.picasso.Picasso

class PhotosAdapter(val photos: MutableList<Photo> = mutableListOf()) : RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_photo, parent, false)
        return PhotosViewHolder(view)
    }

    override fun getItemCount(): Int {
      return  photos.size
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    inner class PhotosViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = CardviewPhotoBinding.bind(view)

        //this function will load the images into the cardview holder for photo
        fun bind(photo: Photo) {
            with(binding) {
                Picasso.get().
                load(photo.url)
                    .resize(IMAGE_SIDE_PX, IMAGE_SIDE_PX)
                    .centerCrop()
                    .into(image)
            }
        }
    }
}

