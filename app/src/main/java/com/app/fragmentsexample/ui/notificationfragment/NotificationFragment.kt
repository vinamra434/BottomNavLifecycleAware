package com.app.fragmentsexample.ui.notificationfragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.app.fragmentsexample.R
import kotlinx.android.synthetic.main.fragment_container.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class NotificationFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var notificationViewModel: NotificationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        notificationViewModel = ViewModelProvider(this).get(NotificationViewModel::class.java)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
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
        const val TAG = "NotificationFragment"

        @JvmStatic
        fun newInstance(backgroundColor: String, title: String) =
            NotificationFragment().apply {
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
                notificationViewModel.onCreateChildFragment(
                    childFragmentManager,
                    title
                )
            }
        }
    }
}