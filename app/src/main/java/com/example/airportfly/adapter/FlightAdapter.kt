package com.example.airportfly.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.airportfly.R
import com.example.airportfly.data.Flight
import com.example.airportfly.databinding.FlightItemBinding
import com.example.airportfly.extension.addCharAtIndex
import com.example.airportfly.extension.isAlphabet
import com.example.airportfly.util.FlyType

class FlightAdapter(private val context: Context) :
    RecyclerView.Adapter<FlightAdapter.ViewHolder>() {
    private val items = AsyncListDiffer(this, object : DiffUtil.ItemCallback<Flight>() {
        override fun areItemsTheSame(oldItem: Flight, newItem: Flight): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Flight, newItem: Flight): Boolean {
            return oldItem == newItem
        }

    })

    fun submitList(datas: List<Flight>) {
        items.submitList(datas)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FlightItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ViewHolder(private val binding: FlightItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val data = items.currentList[position]

            binding.tvDepartureAirPort.text = context.getString(
                R.string.flight_item_air_port, data.departureAirportId, data.departureAirport
            )

            binding.tvArrivalAirPort.text = context.getString(
                R.string.flight_item_air_port, data.arrivalAirportId, data.arrivalAirport
            )

            binding.imgFlyType.setImageResource(
                when (data.flyType) {
                    FlyType.ARRIVAL.str -> R.drawable.ic_arrival
                    FlyType.DEPARTURE.str -> R.drawable.ic_departure
                    else -> 0
                }
            )

            binding.tvScheduleTime.text =
                context.getString(R.string.flight_item_schedule_time, data.scheduleTime)

            binding.tvActualTime.text =
                context.getString(
                    R.string.flight_item_actual_time,
                    data.actualTime ?: context.getString(R.string.no_value)
                )

            binding.tvFlightNumber.text =
                context.getString(R.string.flight_item_number, data.flightNumber)

            binding.tvTerminalAndGate.text =
                context.getString(
                    R.string.flight_item_terminal_and_gate,
                    data.terminal ?: context.getString(R.string.no_value),
                    data.gate ?: context.getString(R.string.no_value)
                )
            binding.tvRemark.text = data.remark.remarkTransToShow()
        }
    }

    /**
     * 轉換Remark為顯示用字串
     */
    private fun String.remarkTransToShow(): String {
        val insertPos = this.indexOfFirst { it.isAlphabet() }
        return if (insertPos != -1) {
            this.addCharAtIndex('\n', insertPos)
        } else {
            this
        }
    }

}