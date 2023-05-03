package com.smy.wordguessgame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController


class HomeFragment : Fragment() {


    private lateinit var startButton: Button
    private lateinit var settingsButton: Button
    private lateinit var quitButton: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        view.apply{
            startButton = findViewById(R.id.homeStartGameButton)
            settingsButton = findViewById(R.id.homeSettingsButton)
            quitButton = findViewById(R.id.homeQuitButton)
        }

        startButton.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToGameFragment()
            findNavController().navigate(action)
        }

        settingsButton.setOnClickListener {
            val action = HomeFragmentDirections.actionGlobalSettingsFragment()
            findNavController().navigate(action)
        }
        quitButton.setOnClickListener {
            requireActivity().finish()
        }

        return view
    }


}