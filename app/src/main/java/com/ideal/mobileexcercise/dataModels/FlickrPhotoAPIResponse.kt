package com.ideal.mobileexcercise.data


data class FlickrPhotoAPIResponse(
    val photos: PhotosSearchResponse
)

data class PhotosSearchResponse(
    val page: Int,
    val photo: ArrayList<PhotoRes>
)

data class PhotoRes(
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: Int,
    val title: String,
    val ispublic: Int,
    val isfriend: Int,
    val isfamily: Int
    )