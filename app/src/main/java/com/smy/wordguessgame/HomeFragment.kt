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
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        startButton = view.findViewById(R.id.homeStartGameButton)
        startButton.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToGameFragment()
            findNavController().navigate(action)
        }

        return view
    }


}