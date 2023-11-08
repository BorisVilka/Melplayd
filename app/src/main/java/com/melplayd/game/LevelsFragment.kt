package com.melplayd.game

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.melplayd.game.databinding.FragmentLevelsBinding


class LevelsFragment : Fragment() {

    private lateinit var binding: FragmentLevelsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLevelsBinding.inflate(inflater,container,false)
        binding.button3.setOnClickListener {
            val navController = Navigation.findNavController(requireActivity(),R.id.fragmentContainerView)
            navController.popBackStack()
        }
        return binding.root
    }

    override fun onResume() {
        val level = requireContext().getSharedPreferences("prefs",Context.MODE_PRIVATE).getInt("level",0)
        val views = arrayOf(binding.t1,binding.t2,binding.t3,binding.t4,binding.t5,binding.t6,)
        for(i in views.indices) {
            views[i].setOnClickListener(null)
            if(i<level) {
                views[i].setTextColor(requireContext().getColor(R.color.yel))
                views[i].setBackgroundResource(R.drawable.corner1)
                views[i].setOnClickListener {
                    val navController = Navigation.findNavController(requireActivity(),R.id.fragmentContainerView)
                    navController.navigate(R.id.gameFragment, Bundle().apply { putInt("level",i) })
                }
            } else if(i==level) {
                views[i].setTextColor(requireContext().getColor(R.color.light))
                views[i].setBackgroundResource(R.drawable.corner2)
                views[i].setOnClickListener {
                    val navController = Navigation.findNavController(requireActivity(),R.id.fragmentContainerView)
                    navController.navigate(R.id.gameFragment, Bundle().apply { putInt("level",i) })
                }
            } else {
                views[i].setTextColor(requireContext().getColor(R.color.white))
                views[i].setBackgroundResource(R.drawable.corner)
            }
        }
        super.onResume()
    }


}