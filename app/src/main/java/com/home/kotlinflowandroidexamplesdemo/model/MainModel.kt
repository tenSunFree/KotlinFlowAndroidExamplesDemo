package com.home.kotlinflowandroidexamplesdemo.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MainModel {

    fun getNumbersOneToTen(): Flow<Int> {
        return flowOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    }

    fun getPresidentialCandidate(): Flow<String> {
        return flowOf("宋楚瑜", "韓國瑜", "蔡英文")
    }
}