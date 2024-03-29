package com.example.marvelproject.ui.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelproject.Base.BaseFragment
import com.example.marvelproject.R
import com.example.marvelproject.databinding.FragmentCharacterListBinding
import com.example.marvelproject.utils.Constants.QUERY_PAGE_SIZE
import com.example.marvelproject.utils.Resource
import com.example.marvelproject.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterListFragment :
    BaseFragment<FragmentCharacterListBinding>(FragmentCharacterListBinding::inflate) {

    private val viewModel: CharacterListViewModel by viewModels()
    private val characterListAdapter: CharacterListAdapter = CharacterListAdapter()
    var page = 30
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCharacterApi(0, page)
        initViews()
    }

    private fun getCharacterApi(offset: Int, page: Int) {
        viewModel.getCharacters(offset, page).observe(viewLifecycleOwner, { response ->
            when (response.status) {
                Resource.Status.LOADING -> {
                    showProgressBar()
                }
                Resource.Status.SUCCESS -> {
                    hideProgressBar()
                    binding.movieRecyclerView.show()
                    characterListAdapter.setOnItemClickListener {
                        val action =
                            CharacterListFragmentDirections.actionCharacterListFragmentToDetailFragment(
                                it.thumbnail.path + "." + it.thumbnail.extension,
                                it.name,
                                it.description,
                                it.characterId
                            )
                        findNavController().navigate(action)
                    }
                    response.data?.let { characterResponse ->
                        characterListAdapter.differ.submitList(characterResponse.data.results)
                    }
                }
                Resource.Status.ERROR -> {
                    hideProgressBar()
                }
            }
        })
    }


    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
        isLoading = false
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
        isLoading = true
    }

    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= QUERY_PAGE_SIZE
            val shouldPaginate = isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning
                    && isTotalMoreThanVisible && isScrolling
            if (shouldPaginate) {
                page += 30
                getCharacterApi(0, page)
                isScrolling = false
            }
        }
    }

    private fun initViews() {
        binding.movieRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.movieRecyclerView.adapter = characterListAdapter
        binding.movieRecyclerView.addOnScrollListener(this@CharacterListFragment.scrollListener)
    }

}