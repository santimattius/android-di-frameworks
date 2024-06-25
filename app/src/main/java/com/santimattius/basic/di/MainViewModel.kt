package com.santimattius.basic.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santimattius.basic.di.data.repositories.MovieRepository
import com.santimattius.basic.di.domain.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

data class MainUiState(
    val isLoading: Boolean = false,
    val data: List<Movie> = emptyList(),
)

@KoinViewModel
class MainViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    private val _state = MutableStateFlow(MainUiState())
    val state = repository.all.onEach {
        if (it.isEmpty()) {
            repository.refresh()
        }
    }.map {
        MainUiState(
            isLoading = false,
            data = it
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = MainUiState(isLoading = true)
    )

    fun refresh() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            repository.refresh().onFailure {
                _state.update { it.copy(isLoading = false) }
            }
        }
    }
}