package com.example.articals.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.articals.R
import com.example.articals.data.api.ApiHelper
import com.example.articals.data.api.RetrofitBuilder
import com.example.articals.data.model.ResponseArticalList
import com.example.articals.data.model.Results
import com.example.articals.ui.adapters.ArticalListRecyclerAdapter
import com.example.articals.ui.adapters.viewmodel.MainViewModel
import com.example.articals.ui.adapters.viewmodel.MainViewModelFactory
import com.example.articals.utils.GenericUtil
import com.example.articals.utils.Resource
import com.example.articals.utils.Status
import kotlinx.android.synthetic.main.fragment_artical_list.*

class ArticalListFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_artical_list, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialiseAdapter()
        observeData()

    }

    private fun initialiseAdapter() {

        recycler.layoutManager = LinearLayoutManager(activity)
    }

    fun observeData() {
        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(
                ApiHelper(RetrofitBuilder.apiService),
                requireActivity().applicationContext
            )
        ).get(MainViewModel::class.java)

        if (GenericUtil.isNetworkAvailable(requireActivity())) {
                fetchArticalList()

        } else {
            progress_circular.visibility = View.GONE
            message.text = "Please Check Your Intenet Connection!!"
            message.visibility = View.VISIBLE
        }
        viewModel.data.observe(getViewLifecycleOwner(), Observer {
            it?.let {
                addArticalDetailsFragment(it)
            }
        })

    }

    private fun fetchArticalList() {
        viewModel.getArticalList().observe(getViewLifecycleOwner(),
            Observer<Resource<ResponseArticalList>> {
                when (it.status) {
                    Status.SUCCESS -> {
                        progress_circular.visibility = View.GONE
                        recycler.visibility = View.VISIBLE
                        it.data?.let {
                            recycler.adapter = activity?.let { it1 ->
                                ArticalListRecyclerAdapter(
                                    viewModel,
                                    it.results, it1.applicationContext
                                )
                            }
                            recycler
                                .adapter?.notifyDataSetChanged()
                        }
                    }

                    Status.LOADING -> {
                        progress_circular.visibility = View.VISIBLE
                    }

                    Status.ERROR -> {
                        progress_circular.visibility = View.GONE
                        message.text = it.message
                        message.visibility = View.VISIBLE
                    }

                }
            })


    }


    private fun addArticalDetailsFragment(result: Results) {
        requireActivity().supportFragmentManager.beginTransaction()
            .add(R.id.container, ArticalDetailsFragment.newInstance(result))
            .addToBackStack(null)
            .commit()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ArticalListFragment().apply {

            }
    }
}