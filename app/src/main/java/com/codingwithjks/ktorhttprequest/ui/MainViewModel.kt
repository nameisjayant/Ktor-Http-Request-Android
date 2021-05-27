package com.codingwithjks.ktorhttprequest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingwithjks.ktorhttprequest.data.Repository.MainRepository
import com.codingwithjks.ktorhttprequest.data.util.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(private val mainRepository: MainRepository): ViewModel() {

    private val _apiStateFlow:MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Empty)
    val apiStateFlow : StateFlow<ApiState> = _apiStateFlow

    fun getPost() = viewModelScope.launch {
        mainRepository.getPost()
            .onStart {
                _apiStateFlow.value = ApiState.Loading
            }
            .catch { e->
               _apiStateFlow.value = ApiState.Failure(e)
            }.collect { response->
                _apiStateFlow.value = ApiState.Success(response)
            }
    }
}