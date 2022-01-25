package com.example.articals.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.articals.R
import com.example.articals.data.api.ApiHelper
import com.example.articals.data.api.RetrofitBuilder
import com.example.articals.data.model.Results
import com.example.articals.ui.viewmodel.ArticalDetailsViewModel
import com.example.articals.ui.viewmodel.ArticalDetailsViewModelFactory
import kotlinx.android.synthetic.main.fragment_artical_details.*
import android.graphics.BitmapFactory

import android.graphics.Bitmap
import android.util.Log
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


private const val ARG_PARAM1 = "data"
private lateinit var viewModel: ArticalDetailsViewModel


class ArticalDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var data: Results? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            data = it.getSerializable(ARG_PARAM1) as Results?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_artical_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
    }


    fun observeData() {
        viewModel = ViewModelProvider(
            this,
            ArticalDetailsViewModelFactory(
                ApiHelper(RetrofitBuilder.apiService),
                requireActivity().applicationContext
            )
        ).get(ArticalDetailsViewModel::class.java)


        setValues(data)

    }

    private fun setValues(mResponseArticalDetails: Results?) {

        mResponseArticalDetails.let {
            title.text = mResponseArticalDetails!!.title
            content.text = mResponseArticalDetails!!.abstract
            description.text = mResponseArticalDetails!!.byline
            author.text = mResponseArticalDetails!!.publishedDate

        }

    }


    companion object {
        @JvmStatic
        fun newInstance(id: Results) =
            ArticalDetailsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, id)

                }
            }
    }
}