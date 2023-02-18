package com.example.myfirstapp.ui.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfirstapp.data.repository.Repository
import com.example.myfirstapp.util.ResponseType
import com.example.myfirstapp.data.model.room.RoomModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private val _result = MutableLiveData<ResponseType<RoomModel>>()
    val result: LiveData<ResponseType<RoomModel>> = _result

    fun getRoomList() {
        viewModelScope.launch {
            _result.postValue(ResponseType.Loading())
            val response = repository.getRoomList()
            if (response.isSuccessful) {
                _result.postValue(ResponseType.Success(response.body()!!))
            } else {
                _result.postValue(ResponseType.Error(response.message()))
            }
        }
    }
}