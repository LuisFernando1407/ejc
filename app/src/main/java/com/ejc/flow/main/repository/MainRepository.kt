package com.ejc.flow.main.repository

import com.ejc.flow.main.data.Movie

interface MainRepository {
    suspend fun movies(): List<Movie>
}