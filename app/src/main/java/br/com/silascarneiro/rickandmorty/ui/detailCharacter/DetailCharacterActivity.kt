package br.com.silascarneiro.rickandmorty.ui.detailCharacter

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import br.com.silascarneiro.rickandmorty.R
import br.com.silascarneiro.rickandmorty.model.dto.CharacterDTO
import br.com.silascarneiro.rickandmorty.utils.Constants.ConstantIntent.CHARACTER_DTO
import br.com.silascarneiro.rickandmorty.utils.formatDatePadraoBrasileiro
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import de.hdodenhof.circleimageview.CircleImageView

class DetailCharacterActivity : AppCompatActivity() {

    companion object {
        fun newInstance(context: Context, dto: CharacterDTO) =
                Intent(context, DetailCharacterActivity::class.java).apply {
                    this.putExtra(CHARACTER_DTO, dto)
                }
    }

    private val dto by lazy { intent.getParcelableExtra<CharacterDTO>(CHARACTER_DTO) }
    private val backButton by lazy { findViewById<Button>(R.id.back_button) }
    private val image by lazy { findViewById<CircleImageView>(R.id.civ_image) }
    private val progress by lazy { findViewById<ProgressBar>(R.id.progress_image_detail) }
    private val tvName by lazy { findViewById<TextView>(R.id.tv_name) }
    private val tvDateCreate by lazy { findViewById<TextView>(R.id.tv_field_date_create) }
    private val tvQtEpisodeos by lazy { findViewById<TextView>(R.id.tv_field_qt_episodios) }
    private val tvLocation by lazy { findViewById<TextView>(R.id.tv_field_qt_location) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_character)

        setupEventBackButton()
        setupImagePerson()
        setupProperties()
    }

    private fun setupProperties() {
        dto?.let {
            tvName.text = getString(R.string.name_species_gender, it.name, it.species, it.gender)
            tvDateCreate.text = it.dateCreate?.formatDatePadraoBrasileiro() ?: getString(R.string.dash)
            tvLocation.text = it.location ?: getString(R.string.dash)
            tvQtEpisodeos.text = it.qtEpisodes.toString()
        }
    }

    private fun setupImagePerson() {
        Glide.with(this)
                .load(dto?.image)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                    ): Boolean {
                        progress.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                    ): Boolean {
                        progress.visibility = View.GONE
                        return false
                    }

                })
                .into(image)
    }

    private fun setupEventBackButton() {
        backButton.setOnClickListener { onBackPressed() }
    }

}