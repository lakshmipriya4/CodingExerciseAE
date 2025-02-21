package com.example.codingexerciseae.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codingexerciseae.model.Hiring
import com.example.codingexerciseae.repository.HiringRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.android.scopes.ViewScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HiringViewModel @Inject constructor(private val repository: HiringRepository): ViewModel() {
    var hiringList = MutableLiveData<List<Hiring>>()
    var errorMessage = MutableLiveData<String>()

    init {
        fetchHiringList()
    }

    fun fetchHiringList() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getHiringList()
            if (response.isSuccessful) {
                hiringList.postValue(response.body()?.filter { !it.name.isNullOrBlank() })
            } else {
                errorMessage.postValue("Failed to get response")
            }
        }
    }
}