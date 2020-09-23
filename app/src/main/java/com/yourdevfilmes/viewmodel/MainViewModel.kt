package com.yourdevfilmes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yourdevfilmes.model.Response
import com.yourdevfilmes.service.NYTService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

class MainViewModel : ViewModel(){

    private val service = NYTService()
    private val _response = MutableLiveData<Response>()
    //private var _filter = MutableLiveData<String>()

    val response: LiveData<Response>
        get() = _response

    /*val filter: LiveData<String>
        get() = _filter*/

    fun fetchMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val res = service.getData().awaitResponse()
            val body = res.body()!!

            if (res.isSuccessful) {
                withContext(Dispatchers.Main) {
                    _response.value = res.body()
                }
            }
            if(!body.results.isEmpty()) {
                //
            }
        }
    }

    fun fetchMovieByText(filter: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val res = service.getByText(filter).awaitResponse()
            val body = res.body()!!

            if (res.isSuccessful) {
                withContext(Dispatchers.Main) {
                    _response.value = res.body()
                }
            }
            if(!body.results.isEmpty()) {
                //TODO Mostrar mensagem de não encontrado
            }
        }
    }

}