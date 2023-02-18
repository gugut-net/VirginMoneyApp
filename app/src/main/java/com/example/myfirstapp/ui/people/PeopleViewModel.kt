package com.example.myfirstapp.ui.people

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfirstapp.data.model.people.PeopleModel
import com.example.myfirstapp.data.model.people.PeopleModelItemModel
import com.example.myfirstapp.data.repository.Repository
import com.example.myfirstapp.util.ResponseType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

//    init {
//        getPeopleList()
//    }

    var peopleObject: PeopleModelItemModel? = null

    private val _result = MutableLiveData<ResponseType<PeopleModel>>()
    val result: LiveData<ResponseType<PeopleModel>> = _result

    private val _selected = MutableLiveData<PeopleModelItemModel>()
    val selected: LiveData<PeopleModelItemModel> = _selected

    fun getPeopleList() {
        viewModelScope.launch {
            _result.postValue(ResponseType.Loading())
            val response = repository.getPeopleList()
            if (response.isSuccessful) {
                _result.postValue(ResponseType.Success(response.body()!!))
            } else {
                _result.postValue(ResponseType.Error(response.message()))
            }
        }
    }

}