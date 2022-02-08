package com.ideal.mobileexcercise.ui.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ideal.mobileexcercise.data.Photo
import com.ideal.mobileexcercise.services.Repository
import com.ideal.mobileexcercise.ui.adapters.PhotosAdapter
import javax.inject.Inject


class PhotosViewModel @Inject constructor(private val repository: Repository): ViewModel(){

    private val mutableListPhoto = MutableLiveData<List<Photo>>()
    private val photosListLiveData: LiveData<List<Photo>> = mutableListPhoto

    var photosAdapter = PhotosAdapter()

    fun loadPhotos2(search: String): LiveData<List<Photo>> {
        repository.getFlickerAPI(search) { code, flickrResponse, err ->
            if (code == 200) {
                if (flickrResponse != null) {
                    val photosList = flickrResponse.photos.photo.map { photo ->
                        Photo(
                            id = photo.id,
                            url = "https://farm${photo.farm}.static.flickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg",
                            title = photo.title
                        )
                    }
                    mutableListPhoto.postValue(photosList)
                }
            }
        }
        return photosListLiveData
    }


}