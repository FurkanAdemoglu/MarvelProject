package com.example.marvelproject.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.marvelproject.Base.BaseFragment
import com.example.marvelproject.R
import com.example.marvelproject.databinding.FragmentDetailBinding
import com.example.marvelproject.ui.list.CharacterListAdapter
import com.example.marvelproject.ui.list.CharacterListFragmentDirections
import com.example.marvelproject.utils.Resource
import com.example.marvelproject.utils.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.item_character.view.*

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val args: DetailFragmentArgs by navArgs()
    private val viewModel:DetailViewModel by viewModels()
    private val comicsListAdapter: ComicsListAdapter = ComicsListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getComics()
        initViews()

    }
    private fun getComics(characterId:Int=args.characterId,startYear:Int=2005,limit:Int=10,orderBy:String="onsaleDate"){
        viewModel.getComics(characterId,startYear,limit,orderBy).observe(viewLifecycleOwner,{response->
            when(response.status){
                Resource.Status.LOADING->{
                    showProgressBar()
                }
                Resource.Status.SUCCESS->{
                    hideProgressBar()
                    binding.comicsRecyclerView.show()
                    Log.v("ComicsData","${response.data}")
                    response.data?.let {comics->
                        comicsListAdapter.differ.submitList(comics.items)
                    }
                }
                Resource.Status.ERROR->{
                    hideProgressBar()
                    response.message?.let {message->
                        Log.v("Errormessage","$message")
                        Toast.makeText(activity, "An error occurred:$message", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
    private fun initViews(){
        binding.characterDescription.text=args.characterDescription
        binding.characterName.text=args.characterName
        Glide.with(this)
            .load(args.image)
            .into(binding.imageView)
        binding.comicsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.comicsRecyclerView.adapter = comicsListAdapter
    }
    private fun hideProgressBar(){
        binding.progressBar.visibility=View.INVISIBLE
        isLoading=false
    }
    var isLoading=false
    var isLastPage=false
    var isScrolling=false

    private fun showProgressBar(){
        binding.progressBar.visibility=View.VISIBLE
        isLoading=true
    }
}