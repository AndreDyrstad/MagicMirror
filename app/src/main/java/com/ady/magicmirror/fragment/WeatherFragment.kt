package com.ady.magicmirror.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.ady.magicmirror.R
import com.ady.magicmirror.databinding.WeatherFragmentBinding
import com.ady.magicmirror.viewmodels.SoMeViewModel


class WeatherFragment : Fragment() {

    private val soMeViewModel: SoMeViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return DataBindingUtil.inflate<WeatherFragmentBinding>(inflater, R.layout.weather_fragment, container, false)
            .apply {
                vm = soMeViewModel
            }
            .root
    }
}


