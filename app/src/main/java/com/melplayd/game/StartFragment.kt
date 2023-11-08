package com.melplayd.game

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.melplayd.game.databinding.FragmentStartBinding


class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStartBinding.inflate(inflater,container,false)
        binding.button2.setOnClickListener {
            val navController = Navigation.findNavController(requireActivity(),R.id.fragmentContainerView)
            navController.navigate(R.id.rulesFragment)
        }
        binding.button.setOnClickListener {
            val navController = Navigation.findNavController(requireActivity(),R.id.fragmentContainerView)
            navController.navigate(R.id.levelsFragment)
        }
        var music = requireActivity().getSharedPreferences("prefs",Context.MODE_PRIVATE).getBoolean("music",true)
        binding.imageView5.setImageResource(if(music) R.drawable.baseline_volume_up_24 else R.drawable.baseline_volume_off_24)
        binding.imageView5.setOnClickListener {
            music = ! music
            requireActivity().getSharedPreferences("prefs",Context.MODE_PRIVATE).edit().putBoolean("music",music).apply()
            binding.imageView5.setImageResource(if(music) R.drawable.baseline_volume_up_24 else R.drawable.baseline_volume_off_24)
        }
        return binding.root
    }


}