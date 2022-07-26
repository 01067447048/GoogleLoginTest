package com.jaehyeon.compose.googlelogintest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.jaehyeon.compose.googlelogintest.databinding.FragmentSubBinding

/**
 * Created by Jaehyeon on 2022/07/26.
 */
class SubFragment: Fragment() {

    private lateinit var binding: FragmentSubBinding
    private val model: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sub, container, false)
        binding.vm = model
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = SubFragment()
    }
}