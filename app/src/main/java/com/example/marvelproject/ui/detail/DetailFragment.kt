package com.example.marvelproject.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.marvelproject.Base.BaseFragment
import com.example.marvelproject.R
import com.example.marvelproject.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint


class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val args: DetailFragmentArgs by navArgs()

}