package com.example.airportfly.ui.currency

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.airportfly.ViewModelFactory
import com.example.airportfly.databinding.FragmentCurrencyBinding
import com.example.airportfly.viewmodel.CurrencyViewModel

class CurrencyFragment : Fragment() {

    private var _binding: FragmentCurrencyBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: CurrencyViewModel by activityViewModels { ViewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrencyBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObserver()
    }

    override fun onResume() {
        super.onResume()

        viewModel.startGetRatesJob()
    }

    override fun onPause() {
        super.onPause()

        viewModel.cancelGetFlightsJob()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setObserver() {
        viewModel.ratesLiveData.observe(viewLifecycleOwner) {
            Log.d("[MY][TEMP]", it.toString())
        }
    }
}