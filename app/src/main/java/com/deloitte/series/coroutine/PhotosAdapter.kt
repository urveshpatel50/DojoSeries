package com.deloitte.series.coroutine

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.deloitte.series.coroutine.network.model.Photo
import com.deloitte.series.R
import com.squareup.picasso.Picasso

class PhotosAdapter(val photos: MutableList<Photo> = mutableListOf()) :
    RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {

        return PhotosViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.flickr_row,
                parent,
                false)
        )
    }

    override fun getItemCount(): Int = photos.size

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    inner class PhotosViewHolder(val row: View) :
        RecyclerView.ViewHolder(row) {

        fun bind(photo: Photo) {
            Picasso.get().load(photo.url)
                .resize(IMAGE_SIDE_PX, IMAGE_SIDE_PX)
                .centerCrop()
                .placeholder(R.drawable.place_holder)
                .into(row.findViewById(R.id.imageView) as ImageView)
        }

    }
}

const val IMAGE_SIDE_PX = 400
