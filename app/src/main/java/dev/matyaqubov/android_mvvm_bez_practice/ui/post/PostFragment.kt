package dev.matyaqubov.android_mvvm_bez_practice.ui.post

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import dev.matyaqubov.android_mvvm_bez_practice.R
import dev.matyaqubov.android_mvvm_bez_practice.data.api.ApiClient
import dev.matyaqubov.android_mvvm_bez_practice.data.api.ApiService
import dev.matyaqubov.android_mvvm_bez_practice.repository.PostRepository
import dev.matyaqubov.android_mvvm_bez_practice.repository.viewmodelfactory.PostViewModelFactory
import dev.matyaqubov.android_mvvm_bez_practice.utils.UiStateList


class PostFragment : Fragment(R.layout.fragment_post) {

    private lateinit var viewModel: PostViewModel

    //    private lateinit var viewModel by viewModels<PostViewModel>() for Dagger Hit

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // viewModel = ViewModelProvider(this).get(PostViewModel::class.java)  for  ViewModelda constructor bo'lmasa  construktor bo'lsa setupViewModel
        setupViewModel()
        viewModel.getPosts()
        setupUi()
    }

    private fun setupUi() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.post.collect {
                when (it) {
                    is UiStateList.LOADING -> {
                        //Show Loading
                    }
                    is UiStateList.SUCCESS -> {
                        Log.d("SUCCESS", "setupUi: ${it.data}")
                    }
                    is UiStateList.ERROR -> {
                        Log.d("ERROR", "setupUi: ${it.message}")
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun setupViewModel() {

        viewModel = ViewModelProvider(
            this,
            PostViewModelFactory(PostRepository(ApiClient.createService(ApiService::class.java)))
        ).get(PostViewModel::class.java)
    }

}
   