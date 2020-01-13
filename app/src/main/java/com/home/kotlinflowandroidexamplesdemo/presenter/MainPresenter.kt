package com.home.kotlinflowandroidexamplesdemo.presenter

import com.home.kotlinflowandroidexamplesdemo.model.MainModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.math.sqrt

class MainPresenter(private val callback: MainPresenterCallback) {

    interface MainPresenterCallback {
        fun firstQuestionResult(result: List<Int>)
        fun secondQuestionResult(result: List<Int>)
        fun thirdQuestionResult(result: List<String>)
    }

    private val model = MainModel()

    fun runFirstQuestion() {
        val flow = model.getNumbersOneToTen()
        CoroutineScope(Dispatchers.Main).launch {
            val output = flow
                .onEach { delay(100) }
                .filter { isPrime(it) }
                .toList()
            callback.firstQuestionResult(output)
        }
    }

    /**
     * 判斷number是否為質數
     */
    private fun isPrime(number: Int): Boolean {
        if (number <= 3) {
            return number > 1
        }
        // 不在6的倍數兩側的一定不是質數
        if (number % 6 != 1 && number % 6 != 5) {
            return false
        }
        val sqrt = sqrt(number.toDouble()).toInt()
        var i = 5
        while (i <= sqrt) {
            if (number % i == 0 || number % (i + 2) == 0) {
                return false
            }
            i += 6
        }
        return true
    }

    @ExperimentalCoroutinesApi
    fun runSecondQuestion() {
        val flow = model.getNumbersOneToTen()
        CoroutineScope(Dispatchers.Main).launch {
            val output = flow
                .onEach { delay(200) }
                .scanReduce { accumulator, value -> accumulator * value }
                .toList()
            callback.secondQuestionResult(output)
        }
    }

    @FlowPreview
    fun runThirdQuestion() {
        val flow = model.getPresidentialCandidate()
        var number = 1
        CoroutineScope(Dispatchers.Main).launch {
            val output = flow
                .onEach { delay(1000) }
                .flatMapMerge {
                    flow {
                        emit("$number︰$it")
                        number++
                    }
                }.toList()
            callback.thirdQuestionResult(output)
        }
    }
}