package dev.matyaqubov.android_mvvm_bez_practice.data.api

import dev.matyaqubov.android_mvvm_bez_practice.model.Post
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getPosts(): List<Post>
}