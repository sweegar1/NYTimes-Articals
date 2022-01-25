package com.example.articals.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.articals.R
import com.example.articals.data.model.Results
import com.example.articals.databinding.ItemViewBinding
import com.example.articals.ui.adapters.viewmodel.MainViewModel

class ArticalListRecyclerAdapter(
    val viewModel: MainViewModel,
    val arrayDetails: ArrayList<Results>,
    val context: Context
): RecyclerView.Adapter<ArticalListRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(arrayDetails[position]) {
                binding.title.text = arrayDetails[position].title
                binding.content.text = arrayDetails[position].byline

                binding.card.setOnClickListener {
                    viewModel.addArticalDetailsFragment(arrayDetails[position])
                }
            }
       }
    }

    override fun getItemCount(): Int {
        if (arrayDetails.size == 0) {
            Toast.makeText(context, context.getString(R.string.empty_list), Toast.LENGTH_LONG).show()
        } else {

        }
        return arrayDetails.size
    }

    inner class ViewHolder(val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root)

}