package com.ejc.flow.main.repository

import com.ejc.data.remote.service.AppServiceGenerator
import com.ejc.data.remote.service.AppServiceSettings
import com.ejc.flow.main.data.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import retrofit2.http.GET

class MainRepositoryImpl(private val service: AppServiceGenerator): MainRepository {

    private val request by lazy {
        this.service.create(
            settings = AppServiceSettings(
                service = Endpoints::class.java
            )
        )
    }

    override suspend fun movies(): List<Movie> {
        return withContext(Dispatchers.IO) {
            try {
              request.movies()
            }catch (e: Exception) {
                throw e
            }
        }
    }

    companion object {
        private const val ENDPOINT_MOVIE = "movielist.json"

        private interface Endpoints {
            @GET(ENDPOINT_MOVIE)
            suspend fun movies(): List<Movie>
        }
    }
}