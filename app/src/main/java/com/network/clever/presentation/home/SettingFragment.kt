package com.network.clever.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.meuus.base.view.AutoClearedValue
import com.network.clever.R
import com.network.clever.presentation.BaseFragment
import com.network.clever.presentation.MainActivity
import kotlinx.android.synthetic.main.fragment_tab.*

class SettingFragment : BaseFragment() {
    companion object {
        fun newInstance() = SettingFragment().apply {
            arguments = Bundle(1).apply {
                putString(FRAGMENT_TAG, SettingFragment::class.java.name)
            }
        }
    }

    private val mainActivity: MainActivity by lazy {
        activity as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val acvView =
            AutoClearedValue(
                this,
                inflater.inflate(R.layout.fragment_setting, container, false)
            )
        return acvView.get()?.rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
}