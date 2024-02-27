package com.example.airportfly.ui.flight

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.airportfly.databinding.FragmentFlightBinding
import com.example.airportfly.viewmodel.FlightViewModel

class FlightFragment : Fragment() {

    private var _binding: FragmentFlightBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val args: FlightFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val flightViewModel =
            ViewModelProvider(this).get(FlightViewModel::class.java)

        _binding = FragmentFlightBinding.inflate(inflater, container, false)
        val root: View = binding.root

        Log.d("ooo", "${args.flyType}  ${args.airPortId}")
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}