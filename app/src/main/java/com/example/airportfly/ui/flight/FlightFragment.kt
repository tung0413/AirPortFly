package com.example.airportfly.ui.flight

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.airportfly.ViewModelFactory
import com.example.airportfly.adapter.FlightAdapter
import com.example.airportfly.databinding.FragmentFlightBinding
import com.example.airportfly.viewmodel.FlightViewModel
import kotlinx.coroutines.launch

class FlightFragment : Fragment() {

    private var _binding: FragmentFlightBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val args: FlightFragmentArgs by navArgs()

    private val viewModel: FlightViewModel by activityViewModels { ViewModelFactory }

    private val adapter: FlightAdapter by lazy {
        FlightAdapter(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFlightBinding.inflate(inflater, container, false)
        val root: View = binding.root

        Log.d("[MY][TEMP]", "${args.flyType}  ${args.airPortId}")
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setView()
        setObserver()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setView() {
        binding.rvFlights.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = this@FlightFragment.adapter
        }
    }

    private fun setObserver() {
        lifecycleScope.launch {
            viewModel.flights.collect {
                adapter.submitList(it)
            }
        }
    }
}