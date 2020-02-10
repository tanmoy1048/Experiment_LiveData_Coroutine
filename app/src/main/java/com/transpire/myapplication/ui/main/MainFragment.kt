package com.transpire.myapplication.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.transpire.myapplication.R
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    var x = 1
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.viewModelValue.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, "$it", Toast.LENGTH_SHORT).show()
        })
        viewModel.viewModelSquareValue.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, "$it", Toast.LENGTH_SHORT).show()
            viewModel.viewModelSquareValue.removeObservers(viewLifecycleOwner)
        })
        viewModel.viewModelEmitValue.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })

        button1.setOnClickListener { viewModel.viewModelGetNextValue() }
        button2.setOnClickListener { viewModel.viewModelGetSquareValue(++x) }
        button3.setOnClickListener {
            viewModel.viewModelEmitLiveFunction(++x)
            viewModel.viewModelEmitFunctionValue.observe(viewLifecycleOwner, Observer {
                Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
            })
        }
    }

}
