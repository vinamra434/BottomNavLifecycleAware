package com.app.fragmentsexample.ui.homefragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.app.fragmentsexample.R
import kotlinx.android.synthetic.main.fragment_container.*
import java.lang.StringBuilder

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        childFragmentManager.addOnBackStackChangedListener {
            val message = StringBuilder("BackStackCount is = ${childFragmentManager.backStackEntryCount}")

            for (index in childFragmentManager.backStackEntryCount - 1 downTo 0) {
                val backStackEntryAt = childFragmentManager.getBackStackEntryAt(index)
                message.append("${backStackEntryAt.name}\n")
            }

            Log.d("COMMON", message.toString())

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_container, container, false)
    }

    companion object {

        const val TAG = "HomeFragment"
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(backgroundColor: String, title: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, backgroundColor)
                    putString(ARG_PARAM2, title)

                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.run {
            val color = getString(ARG_PARAM1)
            val title = getString(ARG_PARAM2)

            text_title.text = title
            container.setBackgroundColor(Color.parseColor(color))

            button_open_child_fragment.setOnClickListener {
                homeViewModel.onCreateChildFragment(
                    childFragmentManager,
                    title
                )
            }
        }

        //Log.d("COMMON", "child fragments number = ${childFragmentManager.backStackEntryCount}")
    }
}