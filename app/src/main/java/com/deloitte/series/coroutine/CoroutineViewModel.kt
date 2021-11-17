package com.deloitte.series.coroutine

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.deloitte.series.coroutine.network.model.Photo
import com.deloitte.series.coroutine.network.model.PhotoMetaData
import com.deloitte.series.coroutine.network.repository.ApiRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class CoroutineViewModel(application: Application) : AndroidViewModel(application) {

    val success = MutableLiveData<List<Photo>>()
    val error = MutableLiveData<Throwable>()

    fun fetchPhotos() {
        viewModelScope.launch {
            try {
                val result = ApiRepository().searchPhotos("Android", 0)
                success.postValue(mapList(result.data?.photos?.photo))
            } catch (ex: Exception) {
                error.postValue(ex)
            }
        }
    }

   // https://farm66.static.flickr.com/65535/51680336918_69fb00f8cb.jpg
    fun mapList(photos: List<PhotoMetaData>?) : List<Photo> = photos?.map {
        Photo(it.id, "https://farm${it.farm}.static.flickr.com/${it.server}/${it.id}_${it.secret}.jpg", it.title)
    } ?: emptyList()
}