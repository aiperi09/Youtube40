package com.example.youtube40.ui.playlists.detail

import android.view.LayoutInflater
import android.widget.Toast
import com.example.youtube40.base.BaseActivity
import com.example.youtube40.databinding.ActivityDetailBinding

class DetailActivity : BaseActivity<DetailViewModel, ActivityDetailBinding>() {
    override val viewModel: DetailViewModel
        get() = TODO("Not yet implemented")

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(inflater)
    }

    override fun initView() {
        Toast.makeText(this, intent.getStringExtra("key"), Toast.LENGTH_SHORT).show()
    }

    override fun initListener() {

    }
}