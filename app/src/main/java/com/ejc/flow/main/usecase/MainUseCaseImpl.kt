package com.ejc.flow.main.usecase

import com.ejc.flow.main.data.Movie
import com.ejc.flow.main.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MainUseCaseImpl(private val repo: MainRepository): MainUseCase {
    override fun movies(): Flow<List<Movie>> {
        return flow {
            val movies = repo.movies()
            emit(movies)
        }.flowOn(Dispatchers.IO)
    }
}