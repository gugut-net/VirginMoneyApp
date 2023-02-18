package com.example.myfirstapp.ui.people

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfirstapp.data.model.people.PeopleModel
import com.example.myfirstapp.util.ResponseType
import com.example.virginmoneyapp.R
import com.example.virginmoneyapp.databinding.FragmentPeopleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PeopleFragment : Fragment() {

    private var _binding: FragmentPeopleBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PeopleViewModel by activityViewModels()

    private val mAdapter by lazy {
        PeopleAdapter {

            /**
             * @Logic
             *  here you move to details
             */
            Toast.makeText(context, "${it.firstName} clicked!", Toast.LENGTH_SHORT).show()
            viewModel.peopleObject = it
            findNavController().navigate(R.id.action_navigation_home_to_navigation_details)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPeopleBinding.inflate(inflater, container, false)

        binding.rvPeople.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }

        viewModel.result.observe(viewLifecycleOwner) {
            when (it) {
                is ResponseType.Loading -> {
                    Toast.makeText(context, "Loading. . .!", Toast.LENGTH_SHORT).show()
                }
                is ResponseType.Success -> {
                    initViews(it.data)
                }
                is ResponseType.Error -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(context, "Loading. . .!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.getPeopleList()

        return binding.root
    }

    private fun initViews(data: PeopleModel?) {
        data?.let {
            mAdapter.updateAdapterPeople(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}