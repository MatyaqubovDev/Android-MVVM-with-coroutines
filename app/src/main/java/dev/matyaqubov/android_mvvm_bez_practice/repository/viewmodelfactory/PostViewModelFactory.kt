package dev.matyaqubov.android_mvvm_bez_practice.repository.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.matyaqubov.android_mvvm_bez_practice.repository.PostRepository
import dev.matyaqubov.android_mvvm_bez_practice.ui.post.PostViewModel

class PostViewModelFactory(private val repository: PostRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostViewModel::class.java)) {
            return PostViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}