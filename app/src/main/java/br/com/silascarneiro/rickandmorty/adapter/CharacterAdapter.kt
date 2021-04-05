package br.com.silascarneiro.rickandmorty.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.silascarneiro.rickandmorty.R
import br.com.silascarneiro.rickandmorty.model.dto.CharacterDTO
import br.com.silascarneiro.rickandmorty.ui.home.callback.CharacterCallback
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class CharacterAdapter(val list: List<CharacterDTO>, val callback: CharacterCallback): RecyclerView.Adapter<CharacterAdapter.ListCharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCharacterViewHolder {
        val view = if (list.isEmpty()) {
            LayoutInflater.from(parent.context).inflate(R.layout.item_adapter_empty, parent, false)
        } else {
            LayoutInflater.from(parent.context).inflate(R.layout.item_adapter, parent, false)
        }
        return ListCharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListCharacterViewHolder, position: Int) {
        val item = list[position]
        holder.setupView(item, callback)
    }

    override fun getItemCount() = list.count()

    inner class ListCharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image = itemView.findViewById<ImageView>(R.id.img_logo)
        private val title = itemView.findViewById<TextView>(R.id.tv_title)
        private val progressBar = itemView.findViewById<ProgressBar>(R.id.loading_image)

        fun setupView(item: CharacterDTO, openDetailCharacter: CharacterCallback) {
            Glide.with(itemView.context)
                .load(item.image)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility = View.GONE
                        return false
                    }

                })
                .into(image)

            image.setOnClickListener { openDetailCharacter.selectedCharacter(item) }
            title.text = item.name
        }
    }
}