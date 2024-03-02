package com.example.airportfly.extension

/**
 * 在字串指定位置添加字元
 */
fun String.addCharAtIndex(char: Char, index: Int) =
    StringBuilder(this).apply { insert(index, char) }.toString()