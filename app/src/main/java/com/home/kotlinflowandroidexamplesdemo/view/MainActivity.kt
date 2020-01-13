package com.home.kotlinflowandroidexamplesdemo.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.home.kotlinflowandroidexamplesdemo.R
import com.home.kotlinflowandroidexamplesdemo.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

class MainActivity : AppCompatActivity(), MainPresenter.MainPresenterCallback {

    private val presenter = MainPresenter(this)

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        runQuestion()
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    private fun runQuestion() {
        presenter.runFirstQuestion()
        presenter.runSecondQuestion()
        presenter.runThirdQuestion()
    }

    @SuppressLint("SetTextI18n")
    override fun firstQuestionResult(result: List<Int>) {
        text_view_first_question_result.text = "答︰$result"
    }

    @SuppressLint("SetTextI18n")
    override fun secondQuestionResult(result: List<Int>) {
        text_view_second_question_result.text = "答︰$result"
    }

    @SuppressLint("SetTextI18n")
    override fun thirdQuestionResult(result: List<String>) {
        text_view_third_question_result.text = "答︰$result"
    }
}
