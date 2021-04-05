package br.com.silascarneiro.rickandmorty.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.silascarneiro.rickandmorty.BaseApplication
import br.com.silascarneiro.rickandmorty.R
import br.com.silascarneiro.rickandmorty.adapter.CharacterAdapter
import br.com.silascarneiro.rickandmorty.model.dto.CharacterDTO
import br.com.silascarneiro.rickandmorty.ui.detailCharacter.DetailCharacterActivity
import br.com.silascarneiro.rickandmorty.ui.home.callback.CharacterCallback
import br.com.silascarneiro.rickandmorty.viewmodel.character.CharacterViewModel
import br.com.silascarneiro.rickandmorty.viewmodel.character.events.CharacterEvent
import br.com.silascarneiro.rickandmorty.viewmodel.character.events.CharacterState
import com.google.android.material.snackbar.Snackbar
import com.yarolegovich.discretescrollview.DiscreteScrollView
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter
import com.yarolegovich.discretescrollview.transform.Pivot
import com.yarolegovich.discretescrollview.transform.ScaleTransformer
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), CharacterCallback {

    @Inject
    lateinit var viewmodelFactory: ViewModelProvider.Factory

    lateinit var viewModel: CharacterViewModel

    private val clContainer by lazy { findViewById<ConstraintLayout>(R.id.cl_container) }
    private val caroussel by lazy { findViewById<DiscreteScrollView>(R.id.rc_character) }
    private val progress by lazy { findViewById<ProgressBar>(R.id.loading_images) }

    companion object {
        fun newInstance(context: Context) = Intent(context, HomeActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        (application as BaseApplication).getAppComponent()?.inject(this)

        setupViewModel()
        getListCharacter()
        setupObservableEvent()
        setupObservableState()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewmodelFactory).get(CharacterViewModel::class.java)
    }

    private fun getListCharacter() {
        viewModel.getListCharacter()
    }

    private fun setupObservableEvent() {
        viewModel.viewEvent.observe(this, Observer {
            if (it is CharacterEvent.ShowOrHideLoading) {
                showOrHideProgress(it.visible)
            }
        })
    }

    private fun setupObservableState() {
        viewModel.viewState.observe(this, Observer {
            when (it) {
                is CharacterState.SucessCall -> sucessCall(it.list)
                is CharacterState.ErrorCall -> errorCall()
            }
        })
    }

    private fun showOrHideProgress(visible: Int) {
        progress.visibility = visible
    }

    private fun errorCall() {
        Snackbar.make(clContainer, getString(R.string.menssage_error), Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.tentar_novamente)) {
                    getListCharacter()
                }.show()
    }

    private fun sucessCall(list: List<CharacterDTO>) {
        caroussel.adapter = InfiniteScrollAdapter.wrap(CharacterAdapter(list, this))
        caroussel.setItemTransformer(
                ScaleTransformer.Builder()
                        .setMaxScale(1.05f)
                        .setMinScale(0.8f)
                        .setPivotX(Pivot.X.CENTER)
                        .setPivotY(Pivot.Y.BOTTOM)
                        .build()
        )
    }

    override fun selectedCharacter(dto: CharacterDTO) {
        startActivity(DetailCharacterActivity.newInstance(this, dto))
    }
}