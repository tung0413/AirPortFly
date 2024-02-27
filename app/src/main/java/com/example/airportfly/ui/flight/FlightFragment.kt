package com.example.airportfly.ui.flight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.airportfly.databinding.FragmentFlightBinding

class FlightFragment : Fragment() {

    private var _binding: FragmentFlightBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val flightViewModel =
            ViewModelProvider(this).get(FlightViewModel::class.java)

        _binding = FragmentFlightBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textFlight
        flightViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}