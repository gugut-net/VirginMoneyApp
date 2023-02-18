package com.example.myfirstapp.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.myfirstapp.data.model.people.PeopleModelItemModel
import com.example.myfirstapp.ui.people.PeopleViewModel
import com.example.virginmoneyapp.R
import com.example.virginmoneyapp.databinding.ItemDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: ItemDetailsBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: PeopleViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = ItemDetailsBinding.inflate(inflater, container, false)
        initViews(sharedViewModel.peopleObject)

        return binding.root
    }

    /**
     * Glide in let scope function event
     */
    private fun initViews(data: PeopleModelItemModel?) {
        data?.let { it ->
            binding.ivUser.let {
                Glide.with(it.context)
                    .load(data.avatar)
                    .centerCrop()
                    .placeholder(R.drawable.animate_loading)
                    .error(R.drawable.animate_loading)
                    .centerCrop()
                    .override(300,330)
                    .into(it)
            }
            binding.tvName.text = it.firstName
            binding.tvJob.text = it.jobtitle
            binding.tvEmail.text = it.email
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}