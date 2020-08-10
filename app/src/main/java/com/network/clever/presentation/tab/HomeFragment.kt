package com.network.clever.presentation.tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.meuus.base.utility.Params
import com.meuus.base.utility.Query
import com.meuus.base.view.AutoClearedValue
import com.meuus.base.view.gone
import com.meuus.base.view.show
import com.network.clever.R
import com.network.clever.data.datasource.model.item.MusicListModel
import com.network.clever.domain.usecase.item.UpdateMyPlaylistUseCase
import com.network.clever.domain.viewmodel.item.MyPlaylistViewModel
import com.network.clever.domain.viewmodel.item.UpdateMyPlaylistViewModel
import com.network.clever.presentation.BaseFragment
import com.network.clever.presentation.Caller
import com.network.clever.presentation.tab.adapter.ContentsAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : BaseFragment() {
    companion object {
        fun newInstance() = HomeFragment().apply {
            arguments = Bundle(1).apply {
                putString(FRAGMENT_TAG, HomeFragment::class.java.name)
            }
        }
    }

    @Inject
    internal lateinit var myPlaylistViewModel: MyPlaylistViewModel

    @Inject
    internal lateinit var updateMyPlaylistViewModel: UpdateMyPlaylistViewModel

    private lateinit var adapter: ContentsAdapter

    private val homeActivity: HomeActivity by lazy {
        activity as HomeActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val acvView =
            AutoClearedValue(
                this,
                inflater.inflate(R.layout.fragment_home, container, false)
            )
        return acvView.get()?.rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter =
            ContentsAdapter({ item ->
                Caller.openPlayer(homeActivity, item)
            }, { item ->
                val query = Query.query(listOf(UpdateMyPlaylistUseCase.DELETE_ITEM, item))
                update(query)
            })
        adapter.setHasStableIds(true)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(false)
        recyclerView.setItemViewCacheSize(10)
        recyclerView.itemAnimator?.changeDuration = 0
        (recyclerView.itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false
        recyclerView.isVerticalScrollBarEnabled = false
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

    override fun onResume() {
        super.onResume()

        getPlaylist()
    }

    private fun update(query: Query) {
        updateMyPlaylistViewModel.pullTrigger(Params(query))
        updateMyPlaylistViewModel.playlist.observe(viewLifecycleOwner, Observer { isSuccess ->
            if (isSuccess) {
                adapter.submitList(null)
                getPlaylist()
            } else {
            }
        })
    }

    private fun getPlaylist() {
        val query = Query.query(listOf())

        myPlaylistViewModel.pullTrigger(Params(query))
        myPlaylistViewModel.playlist.observe(viewLifecycleOwner, Observer { resource ->
            val list = resource as PagedList<MusicListModel.MusicModel>
            if (list.isEmpty()) {
                v_no_music.show()
            } else {
                v_no_music.gone()
                adapter.submitList(list)
            }
        })
    }
}