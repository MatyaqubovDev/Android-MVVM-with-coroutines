package dev.matyaqubov.android_mvvm_bez_practice.repository

import dev.matyaqubov.android_mvvm_bez_practice.data.api.ApiService

class PostRepository(private val apiService: ApiService) {
    suspend fun getPosts() = apiService.getPosts()
}