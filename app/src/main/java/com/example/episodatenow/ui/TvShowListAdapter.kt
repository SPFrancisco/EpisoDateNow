package com.example.episodatenow.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.episodatenow.R
import com.example.episodatenow.databinding.FragmentTvShowListItemBinding
import com.example.episodatenow.model.TvShow
import com.squareup.picasso.Picasso

class TvShowListAdapter(
    private val context: Context,
    private val tvShows: List<TvShow>,
    private val onItemSelected: (tvShow: TvShow) -> Unit
) : RecyclerView.Adapter<TvShowListAdapter.ViewHolder>() {

    class ViewHolder(private val binding: FragmentTvShowListItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

            val cardView = binding.cardView
            val imgTvShowImage = binding.imgTvShowImage
            val tvShowTitle = binding.txTvShowTitle
            val tvShowStatus = binding.statusTvShowEdit
            val tvShowStart = binding.startTvShowEdit
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FragmentTvShowListItemBinding.inflate(
            LayoutInflater.from(parent.context) , parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvShow = tvShows.get(position)
        holder.tvShowTitle.text = tvShow.name
        holder.tvShowStatus.setText(tvShow.status)
        holder.tvShowStart.setText(tvShow.startDate)
        //val picasso =
        Picasso.get() //.with(context)
            .load(tvShow.image)
            .placeholder(R.drawable.poster_placeholder1)
            .resize(120, 160)
            .centerCrop()
            .into(holder.imgTvShowImage)

        /*holder.cardView.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {

                val activity = v!!.context as AppCompatActivity
                val tvShowDetailsFragment = TvShowDetailsFragment()
                activity.supportFragmentManager.beginTransaction().replace(R.id.fragment_container,tvShowDetailsFragment).addToBackStack(null).commit()
            }
        })*/

        holder.cardView.setOnClickListener {
            val activity = it.context as AppCompatActivity
            val tvShowDetailsFragment = TvShowDetailsFragment()
            activity.supportFragmentManager.beginTransaction().replace(R.id.fragment_container,tvShowDetailsFragment).addToBackStack(null).commit()
            onItemSelected(tvShow)
        }
    }

    override fun getItemCount(): Int {
        return tvShows.size
    }
}