package com.example.episodatenow.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.episodatenow.databinding.FragmentTvShowEpisodeItemBinding
import com.example.episodatenow.model.Episodes

class TvShowEpisodeAdapter(
    private val context: Context,
    private val episode: List<Episodes>
) : RecyclerView.Adapter<TvShowEpisodeAdapter.ViewHolder>() {

    class ViewHolder(private val binding: FragmentTvShowEpisodeItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        val txEpisodeData = binding.txEpisodeData

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentTvShowEpisodeItemBinding.inflate(
            LayoutInflater.from(parent.context) , parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val episodeInfo = episode.get(position)
        var episodeNumber: String? = episodeInfo.episode.toString()

        if (episodeInfo.episode!! <= 9){
            episodeNumber = "0" + episodeInfo.episode.toString()
        }

        val info: String = "S" + episodeInfo.season +
                " - E" + episodeNumber +
                ": " + episodeInfo.name

        holder.txEpisodeData.text = info
    }

    override fun getItemCount(): Int {
        return episode.size
    }
}