package com.example.airportfly.ui.currency

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.airportfly.R
import com.example.airportfly.ViewModelFactory
import com.example.airportfly.adapter.CurrencyAdapter
import com.example.airportfly.databinding.FragmentCurrencyBinding
import com.example.airportfly.network.response.ApiResponse
import com.example.airportfly.util.NetworkUtil
import com.example.airportfly.util.getNowTimeString
import com.example.airportfly.viewmodel.CurrencyViewModel

class CurrencyFragment : Fragment() {

    private var _binding: FragmentCurrencyBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: CurrencyViewModel by activityViewModels { ViewModelFactory }

    private val adapter: CurrencyAdapter by lazy {
        CurrencyAdapter()
    }

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

        setView()
        setListener()
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

    private fun setView() {
        binding.rvCurrencies.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = this@CurrencyFragment.adapter
        }
    }

    private fun setListener() {
        binding.btnRefresh.setOnClickListener {
            viewModel.startGetRatesJob()
        }
    }

    private fun setObserver() {
        viewModel.ratesLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResponse.Error -> {
                    if (!NetworkUtil(requireContext()).isNetworkAvailable()) {
                        Log.i("[MY][NET]", "未連接網路")
                        setUi(UiState.NO_NETWORK)
                    } else {
                        setUi(UiState.ERROR)
                    }
                }

                ApiResponse.Loading -> {
                    binding.progressHorizontal.visibility = View.VISIBLE
                }

                is ApiResponse.Success -> {

                    if (it.data.isEmpty()) {
                        setUi(UiState.LIST_IS_EMPTY)
                    } else {
                        setUi(UiState.SUCCESS)
                        adapter.submitList(it.data)
                    }
                }
            }
        }
    }

    private fun setUi(state: UiState) {
        binding.layoutError.visibility = if (state == UiState.SUCCESS) View.GONE else View.VISIBLE
        binding.rvCurrencies.visibility = if (state == UiState.SUCCESS) View.VISIBLE else View.GONE

        binding.imgError.visibility =
            if (state == UiState.LIST_IS_EMPTY) View.GONE else View.VISIBLE
        binding.imgError.setImageResource(
            when (state) {
                UiState.NO_NETWORK -> R.drawable.ic_no_wifi
                UiState.ERROR -> R.drawable.ic_error
                else -> 0
            }
        )

        binding.tvError.text = when (state) {
            UiState.NO_NETWORK -> getString(R.string.error_no_wifi)
            UiState.ERROR -> getString(R.string.error_init)
            UiState.LIST_IS_EMPTY -> getString(R.string.list_is_empty)
            else -> ""
        }

        binding.tvLastUpdate.text =
            if (state == UiState.SUCCESS || state == UiState.LIST_IS_EMPTY) getString(
                R.string.flight_last_update, getNowTimeString("HH:mm:ss")
            ) else getString(R.string.flight_last_update_init)

        binding.btnRefresh.visibility =
            if (state == UiState.LIST_IS_EMPTY) View.GONE else View.VISIBLE
        binding.progressHorizontal.visibility = View.INVISIBLE
    }

    private enum class UiState {
        NO_NETWORK, // 沒有網路
        LIST_IS_EMPTY,  // 資料為空
        SUCCESS,    // 正常
        ERROR   // 其他錯誤
    }
}