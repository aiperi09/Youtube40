package com.example.youtube40.ui.playlists

import android.content.Intent
import android.view.LayoutInflater
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtube40.base.BaseActivity
import com.example.youtube40.checkNet.ConnectivityStatus
import com.example.youtube40.databinding.ActivityPlaylistsBinding
import com.example.youtube40.model.Item
import com.example.youtube40.ui.playlists.detail.DetailActivity

class PlaylistsActivity : BaseActivity<PlaylistViewModel, ActivityPlaylistsBinding>() {

    override val viewModel: PlaylistViewModel by lazy {
        ViewModelProvider(this)[PlaylistViewModel::class.java]
    }
    private val adapter = PlaylistAdapter(this::onItemClick)

    private fun onItemClick(id: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("key", id)
        startActivity(intent)
    }


    override fun initViewModel() {
        viewModel.getPlaylists().observe(this) {
            adapter.setList(it.items as ArrayList<Item>)
        }
    }

    override fun initView() {
        initAdapter()

    }

    private fun initAdapter() {
        binding.recyclerViewPlaylist.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewPlaylist.adapter = adapter
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityPlaylistsBinding {
        return ActivityPlaylistsBinding.inflate(inflater)
    }

    override fun checkInternet() {
        val checkNet = ConnectivityStatus(this)
        checkNet.observe(this) {
            if (it == true) {
                binding.layoutNoInternet.root.isInvisible = true
                binding.recyclerViewPlaylist.isVisible = true
                initViewModel()
            } else {
                binding.layoutNoInternet.root.isInvisible = false
                binding.recyclerViewPlaylist.isVisible = false
            }
        }
    }
}