package com.smy.wordguessgame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController

class SettingsFragment : Fragment() {

    private lateinit var returnButton: ImageView

    private lateinit var viewModel : GameViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_settings, container,false)

        returnButton = view.findViewById(R.id.settingsBackButton)
        returnButton.setOnClickListener {
            findNavController().popBackStack()
        }

        return view
    }




}