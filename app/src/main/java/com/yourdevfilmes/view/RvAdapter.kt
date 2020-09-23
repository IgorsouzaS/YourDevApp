package com.yourdevfilmes.view

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yourdevfilmes.R
import com.yourdevfilmes.model.Result
import kotlinx.android.synthetic.main.adapter_item_layout.view.*
import androidx.core.util.Pair


class RvAdapter(val resultList: ArrayList<Result>) : RecyclerView.Adapter<RvAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.adapter_item_layout, parent, false)
        return ViewHolder(v);
    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(resultList.get(position))
        //holder.name.text = resultList.get(position).display_title
        //holder.rating.text = resultList.get(position).mpaa_rating
    }

    fun update(results: List<Result>) {
        resultList.clear()
        resultList.addAll(results)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name = itemView.tvName
        private val summary = itemView.tvSummary
        private val img = itemView.img

        fun bind(result: Result) {
            name.text = result.display_title
            summary.text = result.summary_short

            Picasso.with(itemView.context)
                .load(result.multimedia?.src)
                .into(img);

            itemView.setOnClickListener({
                val detailIntent = Intent(itemView.context, DetailActivity::class.java).putExtra("summary", result.summary_short)
                val imageViewPair = Pair<View, String>(img, itemView.resources.getString(R.string.transition_imagem))
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(itemView.context as Activity, imageViewPair)

                detailIntent.putExtra("url", result.multimedia?.src)
                startActivity(itemView.context, detailIntent, options.toBundle())
            })
        }

    }

}
