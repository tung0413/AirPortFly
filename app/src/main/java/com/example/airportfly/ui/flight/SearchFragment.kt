package com.example.airportfly.ui.flight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.airportfly.R
import com.example.airportfly.ViewModelFactory
import com.example.airportfly.databinding.FragmentSearchBinding
import com.example.airportfly.util.AirPortID
import com.example.airportfly.util.FlyType
import com.example.airportfly.util.getAirPortIDFromName
import com.example.airportfly.viewmodel.FlightViewModel

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: FlightViewModel by activityViewModels { ViewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setView()
        setListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setView() {
        binding.edtAirport.setSimpleItems(AirPortID.entries.map { it.str }.toTypedArray())
    }

    private fun setListener() {
        binding.btnSearch.setOnClickListener {
            searchFlight()
        }
    }

    private fun searchFlight() {
        val flyType =
            if (binding.groupFlightStatus.checkedButtonId == R.id.toggle_arrival) FlyType.ARRIVAL.str else FlyType.DEPARTURE.str
        val airPortName = binding.edtAirport.text.toString()

        getAirPortIDFromName(airPortName)?.let {
            val action =
                SearchFragmentDirections.actionSearchFragmentToFlightFragment(flyType, it.name)
            findNavController().navigate(action)
        } ?: run {
            // todo 查無機場代碼提示
        }
    }
}