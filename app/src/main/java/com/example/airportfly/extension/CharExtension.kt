package com.example.airportfly.extension

/**
 * 字元是否為英文字母
 */
fun Char.isAlphabet(): Boolean {
    return (this in 'A'..'Z') || (this in 'a'..'z')
}