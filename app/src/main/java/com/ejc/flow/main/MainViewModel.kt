package com.ejc.flow.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ejc.flow.main.data.Movie
import com.ejc.flow.main.usecase.MainUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MainViewModel(private val useCase: MainUseCase): ViewModel() {
    private val _uiState = MutableStateFlow<MainUiState>(MainUiState.Loading)
    val uiState: StateFlow<MainUiState> = _uiState

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            useCase.movies()
                .catch { error -> _uiState.value = MainUiState.Error(error) }
                .collect { movies ->
                    _uiState.value = MainUiState.Success(movies)
                }
        }
    }
}

sealed class MainUiState {
    object Loading : MainUiState()
    data class Success(val movies: List<Movie>): MainUiState()
    data class Error(val exception: Throwable): MainUiState()
}