package com.example.airportfly.ui.flight

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.airportfly.R
import com.example.airportfly.ViewModelFactory
import com.example.airportfly.adapter.FlightAdapter
import com.example.airportfly.databinding.FragmentFlightBinding
import com.example.airportfly.network.response.ApiResponse
import com.example.airportfly.ui.flight.FlightFragment.UiState.ERROR
import com.example.airportfly.ui.flight.FlightFragment.UiState.LIST_IS_EMPTY
import com.example.airportfly.ui.flight.FlightFragment.UiState.NO_NETWORK
import com.example.airportfly.ui.flight.FlightFragment.UiState.SUCCESS
import com.example.airportfly.util.FlyType.ARRIVAL
import com.example.airportfly.util.FlyType.DEPARTURE
import com.example.airportfly.util.NetworkUtil
import com.example.airportfly.util.getNowTimeString
import com.example.airportfly.viewmodel.FlightViewModel


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
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFlightBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("[MY][TEMP]", "${args.flyType}  ${args.airPortId}")
        viewModel.startGetFlightsJob(args.flyType.str, args.airPortId.name)

        setView()
        setListener()
        setObserver()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        viewModel.cancelGetFlightsJob()
    }

    private fun setView() {
        binding.tvFlyType.text = getString(
            R.string.flight_fly_type, when (args.flyType) {
                ARRIVAL -> getString(R.string.flight_arrival)
                DEPARTURE -> getString(R.string.flight_departure)
            }
        )

        binding.tvAirPort.text = getString(R.string.flight_air_port, args.airPortId.str)

        binding.rvFlights.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = this@FlightFragment.adapter
        }
    }

    private fun setListener() {
        binding.btnRefresh.setOnClickListener {
            viewModel.startGetFlightsJob(args.flyType.str, args.airPortId.name)
        }
    }

    private fun setObserver() {
        viewModel.flightsLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResponse.Error -> {
                    if (!NetworkUtil(requireContext()).isNetworkAvailable()) {
                        Log.i("[MY][NET]", "未連接網路")
                        setUi(NO_NETWORK)
                    } else {
                        setUi(ERROR)
                    }
                }

                ApiResponse.Loading -> {
                    binding.progressHorizontal.visibility = View.VISIBLE
                }

                is ApiResponse.Success -> {

                    if (it.data.isEmpty()) {
                        setUi(LIST_IS_EMPTY)
                    } else {
                        setUi(SUCCESS)
                        adapter.submitList(it.data)
                    }
                }
            }
        }
    }

    private fun setUi(state: UiState) {
        binding.layoutError.visibility = if (state == SUCCESS) View.GONE else View.VISIBLE
        binding.rvFlights.visibility = if (state == SUCCESS) View.VISIBLE else View.GONE

        binding.imgError.visibility = if (state == LIST_IS_EMPTY) View.GONE else View.VISIBLE
        binding.imgError.setImageResource(
            when (state) {
                NO_NETWORK -> R.drawable.ic_no_wifi
                ERROR -> R.drawable.ic_error
                else -> 0
            }
        )

        binding.tvError.text = when (state) {
            NO_NETWORK -> getString(R.string.error_no_wifi)
            ERROR -> getString(R.string.error_init)
            LIST_IS_EMPTY -> getString(R.string.list_is_empty)
            else -> ""
        }

        binding.tvLastUpdate.text = if (state == SUCCESS || state == LIST_IS_EMPTY) getString(
            R.string.flight_last_update, getNowTimeString("HH:mm:ss")
        ) else getString(R.string.flight_last_update_init)

        binding.btnRefresh.visibility = if (state == LIST_IS_EMPTY) View.GONE else View.VISIBLE
        binding.progressHorizontal.visibility = View.INVISIBLE
    }

    private enum class UiState {
        NO_NETWORK, // 沒有網路
        LIST_IS_EMPTY,  // 資料為空
        SUCCESS,    // 正常
        ERROR   // 其他錯誤
    }
}
