package com.example.episodatenow.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.episodatenow.R
import com.example.episodatenow.databinding.FragmentTvShowDetailsBinding
import com.example.episodatenow.model.Episodes
import com.example.episodatenow.model.TvShow
import com.example.episodatenow.viewmodel.EpisoDateViewModel
import com.squareup.picasso.Picasso


class TvShowDetailsFragment : Fragment() {

    private lateinit var binding: FragmentTvShowDetailsBinding
    private lateinit var viewModel: EpisoDateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentTvShowDetailsBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(requireActivity()).get(EpisoDateViewModel::class.java)
        viewModel.loadTvShowDetails(viewModel.selected.value!!.id!!.toString())

        viewModel.tvShow.observe(viewLifecycleOwner, {
            lateinit var genres: String
            binding.tvShowTitle.text = viewModel.tvShow.value!!.name
            binding.tvShowDescriptionEdit.setText(viewModel.tvShow.value!!.description)
            binding.tvShowStatusEdit.setText(viewModel.tvShow.value!!.status)
            binding.tvShowStartEdit.setText(viewModel.tvShow.value!!.startDate)
            binding.tvShowRatingEdit.setText(viewModel.tvShow.value!!.rating)
            binding.tvShowStationEdit.setText(viewModel.tvShow.value!!.network)
            binding.tvShowGenresEdit.setText(viewModel.tvShow.value!!.genres.joinToString())
            Picasso.get()
                .load(viewModel.tvShow.value!!.image)
                .placeholder(R.drawable.poster_placeholder1)
                .resize(150, 200)
                .centerCrop()
                .into(binding.tvShowImgs)
        })

        viewModel.tvShowEpisodesList.observe(viewLifecycleOwner, {
            setAdapter(it)
        })


        return binding.root
    }

    private fun setAdapter(item: List<Episodes>) {
        binding.episodesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.episodesRecyclerView.adapter = TvShowEpisodeAdapter(requireContext(), item) /*{
            viewModel.select(it)
        }*/
    }

}