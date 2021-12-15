package com.example.episodatenow.ui

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.episodatenow.databinding.FragmentTvShowListBinding
import com.example.episodatenow.model.TvShow
import com.example.episodatenow.viewmodel.EpisoDateViewModel


class TvShowListFragment : Fragment() {

    private lateinit var  binding: FragmentTvShowListBinding
    private lateinit var viewModel: EpisoDateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_tv_show_list, container, false)
        setHasOptionsMenu(true)

        binding = FragmentTvShowListBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(EpisoDateViewModel::class.java)
        viewModel.tvShowList.observe(viewLifecycleOwner, {
            setAdapter(it)
        })

        viewModel.pagination.observe(viewLifecycleOwner, {
            binding.currentPage.text = it.page.toString() + " of " + it.totalPages.toString()
        })

        viewModel.exception.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
        })

        viewModel.loading.observe(viewLifecycleOwner, {
            if (it == true) {
                binding.currentPage.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
            }else {
                binding.progressBar.visibility = View.GONE
                binding.currentPage.visibility = View.VISIBLE
            }
        })

        viewModel.loadTvShows()

        binding.btnNext.setOnClickListener {
            val nextPage = viewModel.pagination.value!!.page + 1
            if (nextPage <= viewModel.pagination.value!!.totalPages) {
                viewModel.loadTvShows(nextPage)
            }else {
                Toast.makeText(requireContext(), "No more pages", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnPrevious.setOnClickListener {
            val previousPage = viewModel.pagination.value!!.page - 1
            if (previousPage >= 1) {
                viewModel.loadTvShows(previousPage)
            }else {
                Toast.makeText(requireContext(), "No more pages", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnSearch.setOnClickListener {
            if (binding.txtSearch.text.isEmpty()){
                viewModel.loadTvShows()
            }else {
                viewModel.search(binding.txtSearch.text.toString())
            }
//            hideKeyboard()
        }

        return binding.root
    }

    /*private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }*/

    private fun setAdapter(item: List<TvShow>) {
        binding.tvShowListRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.tvShowListRecyclerView.adapter = TvShowListAdapter(requireContext(), item) {
            viewModel.select(it)
            //findNavController().navigate(R.id.TvShowFromFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        //inflater.inflate(R.menu.fragment_tv_show_menu, menu)
        //super.onCreateOptionsMenu(menu, inflater)
    }

}