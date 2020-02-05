package com.rsh.samplevideofilmapp.View.Adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rsh.data.repository.DataOperationStatus
import com.rsh.domain.BASE_URL_POSTER
import com.rsh.domain.ID_FILM
import com.rsh.domain.model.Films
import com.rsh.samplevideofilmapp.R
import kotlinx.android.synthetic.main.db_network_item.view.*
import kotlinx.android.synthetic.main.db_network_item.view.progress_bar
import kotlinx.android.synthetic.main.db_network_item.view.txt_error
import kotlinx.android.synthetic.main.fragment_detail_movie.view.*
import kotlinx.android.synthetic.main.movie_item.view.*
import kotlinx.android.synthetic.main.movie_item.view.film_release_date
import kotlinx.android.synthetic.main.movie_item.view.film_title


class FilmPagedListAdapter (public val context: Context) : PagedListAdapter<Films, RecyclerView.ViewHolder>(MovieDiffCallback()){

    val FILM_VIEW_TYPE = 1
    val DB_NETWORK_TYPE = 2

    private var dataStatus : DataOperationStatus? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view : View
        if(viewType == FILM_VIEW_TYPE){
            view = layoutInflater.inflate(R.layout.movie_item, parent, false)
            return FilmItemViewHolder(view)
        }else{
            view = layoutInflater.inflate(R.layout.db_network_item, parent, false)
            return BlockItemViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if(getItemViewType(position) == FILM_VIEW_TYPE){
            (holder as FilmItemViewHolder).bind(getItem(position), context)
        }else{
            (holder as BlockItemViewHolder).bind(dataStatus)
        }
    }


    override fun getItemViewType(position: Int):Int{
        return if (hasExtraRow() && position == itemCount - 1){
            DB_NETWORK_TYPE
        }else{
            FILM_VIEW_TYPE
        }
    }

    override fun getItemCount():Int{
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    private fun hasExtraRow(): Boolean{
        return dataStatus != null && dataStatus != DataOperationStatus.END_GET_DATA_FROM_DB
    }


    class MovieDiffCallback : DiffUtil.ItemCallback<Films>(){
        override fun areItemsTheSame(oldItem: Films, newItem: Films): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Films, newItem: Films): Boolean {
            return oldItem == newItem
        }

    }

    class FilmItemViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bind(film: Films?, context: Context){
            itemView.film_title.text = film?.title
            itemView.film_release_date.text = film?.release_date

            val filmPosterUrl: String = BASE_URL_POSTER + film?.poster_path
            Glide.with(itemView.context)
                .load(filmPosterUrl)
                .into(itemView.iv_fllm_poster_item)

            itemView.setOnClickListener{
                //fragment to detail

                val bundle = Bundle()
                bundle.putInt(ID_FILM, film!!.id)
                it.findNavController().navigate(R.id.detailMovieFragment, bundle)
            }
        }
    }

    class BlockItemViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bind(dataStatus: DataOperationStatus?){
            if(dataStatus != null && dataStatus == DataOperationStatus.PROCESSING){
                itemView.progress_bar.visibility = View.VISIBLE
            }else{
                itemView.progress_bar.visibility = View.GONE
            }

            if(dataStatus != null && dataStatus == DataOperationStatus.FAILED){
                itemView.txt_error.visibility = View.VISIBLE
                itemView.txt_error.text = dataStatus.message
            }else if(dataStatus != null && dataStatus == DataOperationStatus.END_OF_LIST_FROM_DB){
                itemView.txt_error.visibility = View.VISIBLE
                itemView.txt_error.text = dataStatus.message
            }else{
                itemView.txt_error.visibility = View.GONE
            }

        }
    }


    //Подсмотрел данное решениеб но нехватили времени разобраться с библиотекой Pagination для работы с Room
    //Оставил задел на будущее, разобраться
    fun setNetworkState(newDataOperationStatus: DataOperationStatus){
        val previousState : DataOperationStatus? = this.dataStatus
        val hadExtraRow : Boolean = hasExtraRow()

        this.dataStatus = newDataOperationStatus
        val hasExtraRow: Boolean = hasExtraRow()

        if(hadExtraRow != hasExtraRow){
            if(hadExtraRow){
                notifyItemRemoved(super.getItemCount())
            }else{
                notifyItemInserted(super.getItemCount())
            }
        }else if (hasExtraRow && previousState != newDataOperationStatus){
            notifyItemChanged(itemCount - 1)
        }

    }

}