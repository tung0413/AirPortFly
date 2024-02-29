package com.example.airportfly.ui.flight

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.airportfly.ViewModelFactory
import com.example.airportfly.databinding.FragmentFlightBinding
import kotlinx.coroutines.launch
import com.example.airportfly.viewmodel.FlightViewModel

class FlightFragment : Fragment() {

    private var _binding: FragmentFlightBinding? = null

    private val viewModel by viewModels<FlightViewModel> { ViewModelFactory }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val args: FlightFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFlightBinding.inflate(inflater, container, false)
        val root: View = binding.root

        Log.d("ooo", "${args.flyType}  ${args.airPortId}")
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObserver()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setObserver() {
        lifecycleScope.launch {
            viewModel.flights.collect {
                Log.d("[MY]{TEMP]", it.toString())
            }
        }
    }
}