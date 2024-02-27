package com.example.airportfly.data

import com.google.gson.annotations.SerializedName

data class Flight(
    @SerializedName("FlyType")
    val flyType: String,    // A-入境航班 / D-出境航班
    @SerializedName("AirlineID")
    val airlineId: String,  // 航空公司IATA國際代碼
    @SerializedName("Airline")
    val airline: String,    // 航空公司名稱
    @SerializedName("FlightNumber")
    val flightNumber: String,   // 航機班號
    @SerializedName("DepartureAirportID")
    val departureAirportId: String, // 起點機場IATA國際代碼
    @SerializedName("DepartureAirport")
    val departureAirport: String,   // 起點機場名稱
    @SerializedName("ArrivalAirportID")
    val arrivalAirportId: String,   // 目的地機場IATA國際代碼
    @SerializedName("ArrivalAirport")
    val arrivalAirport: String, // 目的地機場名稱
    @SerializedName("ScheduleTime")
    val scheduleTime: String,   // 表訂出發/到達時間
    @SerializedName("ActualTime")
    val actualTime: String?=null, // 實際出發/到達時間
    @SerializedName("EstimatedTime")
    val estimatedTime: String? = null,    // 預估出發/到達時間
    @SerializedName("Remark")
    val remark: String, // 航班狀態描述
    @SerializedName("Terminal")
    val terminal: String?=null, // 航廈
    @SerializedName("Gate")
    val gate: String?=null, // 登機門
    @SerializedName("UpdateTime")
    val updateTime: String, // 資料更新時間
)
