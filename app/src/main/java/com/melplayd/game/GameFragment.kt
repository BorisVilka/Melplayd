package com.melplayd.game

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.Navigation
import com.melplayd.game.databinding.FragmentGameBinding
import java.util.LinkedList
import java.util.Random


class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding
    lateinit var tmp: List<Domino>
    var win = false
    lateinit var bg: MediaPlayer

    override fun onDestroy() {
        bg.stop()
        bg.release()
        super.onDestroy()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGameBinding.inflate(inflater,container,false)
        binding.imageView6.setOnClickListener {
            binding.exit.visibility = View.VISIBLE
        }
        bg = MediaPlayer.create(requireContext(),R.raw.bg)
        bg.setOnCompletionListener { it.start() }
        if(requireContext().getSharedPreferences("prefs",Context.MODE_PRIVATE).getBoolean("music",true)) bg.start()
        binding.textView5.text = "Level ${requireArguments().getInt("level")+1}"
        binding.button7.text = "Level ${requireArguments().getInt("level")+2}"
        if(requireArguments().getInt("level")+2>6) binding.button7.visibility = View.INVISIBLE
        binding.textView2.text = "${requireArguments().getInt("level")+1} level"
        binding.imageView8.setOnClickListener {
            if(!win) {
                val navController = Navigation.findNavController(requireActivity(),R.id.fragmentContainerView)
                navController.popBackStack()
                navController.navigate(R.id.gameFragment,requireArguments())
            }
        }
        binding.button4.setOnClickListener {
           if(!win) {
               val navController = Navigation.findNavController(requireActivity(),R.id.fragmentContainerView)
               navController.popBackStack()
           }
        }
        binding.button6.setOnClickListener {
            val navController = Navigation.findNavController(requireActivity(),R.id.fragmentContainerView)
            navController.popBackStack()
        }
        binding.button7.setOnClickListener {
            val navController = Navigation.findNavController(requireActivity(),R.id.fragmentContainerView)
            navController.popBackStack()
            navController.navigate(R.id.gameFragment,Bundle().apply {
                putInt("level",requireArguments().getInt("level")+1)
            })
        }
        binding.button5.setOnClickListener {
            if(!win) binding.exit.visibility = View.GONE
        }
        var views = arrayOf(binding.a1,binding.a2,binding.a3,binding.a4,binding.a5,binding.a6,binding.a7,binding.a8,binding.a9,binding.a10,binding.a11,binding.a12,binding.a13,binding.a14,binding.a15,binding.a16,binding.a17,binding.a18,binding.a19,binding.a20, )
        tmp = listOf()
        var last: Domino? = null
        var check: Domino? = null
        var indP = -1
        for(i in views) i.visibility = View.INVISIBLE
        when(requireArguments().getInt("level")) {
            0 -> {
                tmp = listOf(Domino(0,0,binding.a1,true,false),Domino(0,0,binding.a10,false,false),Domino(0,0,binding.a11,false,false),
                    Domino(0,0,binding.a6,true,true),Domino(0,0,binding.a14,false,true),)
            }
            1 -> {
                tmp = listOf(Domino(0,0,binding.a1,true,false),Domino(0,0,binding.a2,true,false),Domino(0,0,binding.a9,false,false),
                    Domino(0,0,binding.a5,true,true), Domino(0,0,binding.a6,true,true),Domino(0,0,binding.a14,false,true),)
            }
            2 -> {
                tmp = listOf(Domino(0,0,binding.a1,true,false),Domino(0,0,binding.a2,true,false),Domino(0,0,binding.a3,true,false),Domino(0,0,binding.a8,false,false),
                    Domino(0,0,binding.a4,true,true), Domino(0,0,binding.a5,true,true), Domino(0,0,binding.a6,true,true),Domino(0,0,binding.a14,false,true),)
            }
            3 -> {
                tmp = listOf(Domino(0,0,binding.a1,true,false),Domino(0,0,binding.a2,true,false),Domino(0,0,binding.a3,true,false),Domino(0,0,binding.a8,false,false),
                    Domino(0,0,binding.a12,false,false), Domino(0,0,binding.a7,true,true),Domino(0,0,binding.a13,false,false), Domino(0,0,binding.a6,true,true),Domino(0,0,binding.a14,false,true),)
            }
            4 -> {
                tmp = listOf(Domino(0,0,binding.a1,true,false),Domino(0,0,binding.a2,true,false),Domino(0,0,binding.a15,false,true),Domino(0,0,binding.a16,true,false),
                    Domino(0,0,binding.a17,false,false), Domino(0,0,binding.a18,false,false),Domino(0,0,binding.a19,true,true), Domino(0,0,binding.a12,false,true),
                    Domino(0,0,binding.a7,true,true),Domino(0,0,binding.a13,false,true),Domino(0,0,binding.a6,true,true),Domino(0,0,binding.a14,false,true),
                    )
            }
            5 -> {
                tmp = listOf(Domino(0,0,binding.a2,true,false),Domino(0,0,binding.a15,false,true),Domino(0,0,binding.a16,true,false),
                    Domino(0,0,binding.a17,false,false), Domino(0,0,binding.a18,false,false),Domino(0,0,binding.a19,true,true), Domino(0,0,binding.a12,false,true),
                    Domino(0,0,binding.a7,true,true),Domino(0,0,binding.a13,false,true),Domino(0,0,binding.a20,false,true)
                )
            }
        }
        for(i in tmp.iterator()) {
            i.im.visibility = View.VISIBLE
            i.im.setOnClickListener {
                if(check==null || check!=i) {
                    if(check!=null) check!!.im.setImageResource(if(check!!.hor) R.drawable.emh else R.drawable.emv)
                    check = i
                    i.im.setImageResource(if(i.hor) R.drawable.selh else R.drawable.selv)
                }
            }
        }
        var b = arrayOf(Pair(0,0),Pair(0,0),Pair(0,0),Pair(0,0),Pair(0,0),Pair(0,0),Pair(0,0),Pair(0,0),Pair(0,0),Pair(0,0),Pair(0,0),Pair(0,0),Pair(0,0),Pair(0,0),Pair(0,0))
        var cards = arrayOf(binding.b1,binding.b,binding.b2,binding.b3,binding.b4,binding.b5,binding.b6,binding.b7,binding.b8,binding.b9,binding.b10,binding.b11,binding.b12,binding.b13,binding.b14)
        val random = Random()
        for(i in cards) i.visibility = View.INVISIBLE
        for(i in tmp.indices) {
            cards[i].visibility = View.VISIBLE
            val st = if(i==0) random.nextInt(5)+1 else b[i-1].end
            val end = if(i==tmp.size-1) b[0].st else random.nextInt(5)+1
            b[i] = Pair(st,end)
            cards[i].setImageResource(arr[st][end][1])
            cards[i].setOnClickListener {
                if(check!=null) {
                    indP = i
                    last = check
                    check = null
                    last!!.fir = b[i].st
                    last!!.sec = b[i].end
                    last!!.im.setImageResource(arr[b[i].st][b[i].end][if(last!!.hor) 0 else 1])
                    cards[i].visibility = View.INVISIBLE
                    if(check()) {
                        requireActivity().getSharedPreferences("prefs",Context.MODE_PRIVATE).edit().putInt("level",requireArguments().getInt("level")+1).apply()
                        win = true
                        binding.win.visibility = View.VISIBLE
                    }
                }
            }
        }
        binding.imageView7.setOnClickListener {
            if(last!=null) last!!.im.setImageResource(if(last!!.hor) R.drawable.emh else R.drawable.emv)
            last = null
            if(indP!=-1) cards[indP].visibility = View.VISIBLE
            if(check!=null) check!!.im.setImageResource(if(check!!.hor) R.drawable.emh else R.drawable.emv)
            check = null
        }
        return binding.root
    }

    val arr = arrayOf(
        arrayOf(arrayOf(-1,-1),arrayOf(-1,-1),arrayOf(-1,-1),arrayOf(-1,-1),arrayOf(-1,-1),arrayOf(-1,-1), arrayOf(-1,-1)),
        arrayOf(arrayOf(-1,-1), arrayOf(R.drawable.t11h,R.drawable.t11v),   arrayOf(R.drawable.t21h,R.drawable.t21v),  arrayOf(R.drawable.t31h,R.drawable.t31v),   arrayOf(R.drawable.t41h,R.drawable.t41v),    arrayOf(R.drawable.t51h,R.drawable.t51v),   arrayOf(R.drawable.t61h,R.drawable.t61v)),
        arrayOf(arrayOf(-1,-1), arrayOf(R.drawable.t12h,R.drawable.t12v),   arrayOf(R.drawable.t22h,R.drawable.t22v),  arrayOf(R.drawable.t32h,R.drawable.t32v),   arrayOf(R.drawable.t42h,R.drawable.t42v),    arrayOf(R.drawable.t52h,R.drawable.t52v),   arrayOf(R.drawable.t62h,R.drawable.t62v)),
        arrayOf(arrayOf(-1,-1), arrayOf(R.drawable.t13h,R.drawable.t13v),   arrayOf(R.drawable.t23h,R.drawable.t23v),  arrayOf(R.drawable.t33h,R.drawable.t33v),   arrayOf(R.drawable.t43h,R.drawable.t43v),    arrayOf(R.drawable.t53h,R.drawable.t53v),   arrayOf(R.drawable.t63h,R.drawable.t63v)),
        arrayOf(arrayOf(-1,-1), arrayOf(R.drawable.t14h,R.drawable.t14v),   arrayOf(R.drawable.t24h,R.drawable.t24v),  arrayOf(R.drawable.t34v,R.drawable.t34h),   arrayOf(R.drawable.t44h,R.drawable.t44v),    arrayOf(R.drawable.t54h,R.drawable.t54v),   arrayOf(R.drawable.t64h,R.drawable.t64v)),
        arrayOf(arrayOf(-1,-1), arrayOf(R.drawable.t15h,R.drawable.t15v),   arrayOf(R.drawable.t25h,R.drawable.t25v),  arrayOf(R.drawable.t35h,R.drawable.t35v),   arrayOf(R.drawable.t45h,R.drawable.t45v),    arrayOf(R.drawable.t55h,R.drawable.t55v),   arrayOf(R.drawable.t65h,R.drawable.t65v)),
        arrayOf(arrayOf(-1,-1), arrayOf(R.drawable.t16h,R.drawable.t16v),   arrayOf(R.drawable.t26h,R.drawable.t26v),  arrayOf(R.drawable.t36h,R.drawable.t36v),   arrayOf(R.drawable.t46h,R.drawable.t46v),    arrayOf(R.drawable.t56h,R.drawable.t56v),   arrayOf(R.drawable.t66h,R.drawable.t66v)),
    )

    fun check(): Boolean {
        var i = 0
        var ans = true
        while(i<tmp.size-1) {
            Log.d("TAG","$i ${tmp[i].fir} ${tmp[i].sec} ${tmp[i+1].fir} ${tmp[i+1].sec} ${tmp[i].inv} ${tmp[i+1].inv} $ans")
            if(tmp[i].sec==0 || tmp[i+1].fir==0) {
                i++
                ans = false
                continue
            }
            ans = ans && (
                    tmp[i].fir==tmp[i+1].sec || ((tmp[i].fir==tmp[i+1].sec || tmp[i+1].sec==tmp[i].sec || tmp[i].fir==tmp[i+1].fir) && (tmp[i].inv || tmp[i+1].inv))
                    )
            i++
        }
        ans = ans && (
                tmp[i].fir==tmp[0].sec || ((tmp[i].fir==tmp[0].sec || tmp[0].sec==tmp[i].sec || tmp[i].fir==tmp[0].fir) && (tmp[i].inv || tmp[0].inv))
                )
        Log.d("TAG","$i ${tmp[i].fir} ${tmp[i].sec} ${tmp[0].fir} ${tmp[0].sec} ${tmp[i].inv} ${tmp[0].inv} $ans")
        return ans
    }

    companion object {
       data class Domino(var fir: Int, var sec: Int, var im: ImageView, var hor: Boolean, var inv: Boolean)
        data class Pair(val st: Int, val end: Int)
    }
}