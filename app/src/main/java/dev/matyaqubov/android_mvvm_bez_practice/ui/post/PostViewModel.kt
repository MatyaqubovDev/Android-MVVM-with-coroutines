package dev.matyaqubov.android_mvvm_bez_practice.ui.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.matyaqubov.android_mvvm_bez_practice.model.Post
import dev.matyaqubov.android_mvvm_bez_practice.repository.PostRepository
import dev.matyaqubov.android_mvvm_bez_practice.utils.UiStateList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class PostViewModel(private val repository: PostRepository) : ViewModel() {
    private val _postState = MutableStateFlow<UiStateList<Post>>(UiStateList.EMPTY)
    val post = _postState


    fun getPosts() = viewModelScope.launch {
        _postState.value=UiStateList.LOADING
        try {
            val posts = repository.getPosts()
            _postState.value =UiStateList.SUCCESS(posts)
//            if (posts.status){
//                _postState.value =UiStateList.SUCCESS(posts)
//            } else {
//                _postState.value=UiStateList.ERROR(posts.message,true)
//            }
        } catch (e:Exception){
            _postState.value = UiStateList.ERROR(e.localizedMessage?:"No connection")
        }
    }
}