package com.ejc.flow.main.usecase

import com.ejc.flow.main.data.Movie
import kotlinx.coroutines.flow.Flow

interface MainUseCase {
    fun movies(): Flow<List<Movie>>
}