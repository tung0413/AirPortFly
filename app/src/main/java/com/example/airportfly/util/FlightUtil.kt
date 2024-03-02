package com.example.airportfly.util

enum class AirPortID(val str: String) {
    TPE("桃園中正機場"),
    CYI("嘉義"),
    CMJ("七美"),
    GNI("綠島"),
    HUN("花蓮"),
    KHH("高雄"),
    KNH("金門"),
    MZG("馬公"),
    MFK("馬祖"),
    KYD("蘭嶼"),
    PIF("屏東"),
    WOT("望安"),
    TSA("松山"),
    TXG("台中"),
    TTT("台東"),
    TBB("台南")
}

/**
 * 根據機場名稱取得對應的機場代碼
 */
fun getAirPortIDFromName(airPortName: String):AirPortID?{
    return AirPortID.entries.find { it.str ==  airPortName}
}

enum class FlyType(val str:String){
    ARRIVAL("A"),   // 入境
    DEPARTURE("D")  // 出境
}