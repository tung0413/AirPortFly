package com.example.airportfly.ui.currency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.airportfly.databinding.FragmentCurrencyBinding
import com.example.airportfly.viewmodel.CurrencyViewModel

class CurrencyFragment : Fragment() {

    private var _binding: FragmentCurrencyBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val currencyViewModel =
            ViewModelProvider(this).get(CurrencyViewModel::class.java)

        _binding = FragmentCurrencyBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textCurrency
        currencyViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}