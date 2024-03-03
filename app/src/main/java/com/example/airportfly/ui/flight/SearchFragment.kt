package com.example.airportfly.ui.flight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.airportfly.R
import com.example.airportfly.databinding.FragmentSearchBinding
import com.example.airportfly.util.AirPortID
import com.example.airportfly.util.FlyType
import com.example.airportfly.util.getAirPortIDFromName

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

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
        binding.edtAirport.setOnEditorActionListener { textView, i, keyEvent ->
            if(i == EditorInfo.IME_ACTION_DONE){
                searchFlight()
            }
            false
        }
    }

    private fun searchFlight() {
        val flyType =
            if (binding.groupFlightStatus.checkedButtonId == R.id.toggle_arrival) FlyType.ARRIVAL else FlyType.DEPARTURE
        val airPortName = binding.edtAirport.text.toString()

        getAirPortIDFromName(airPortName)?.let {
            clearError()
            val action =
                SearchFragmentDirections.actionSearchFragmentToFlightFragment(flyType, it)
            findNavController().navigate(action)
        } ?: run {
            if (airPortName.isEmpty()) {
                binding.layoutEdtAirport.error = getString(R.string.error_empty_airport)
            } else {
                binding.layoutEdtAirport.error = getString(R.string.error_unknown_airport)
            }
        }
    }

    private fun clearError() {
        binding.layoutEdtAirport.error = null
    }
}