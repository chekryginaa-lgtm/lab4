package com.example.lunchtray

import androidx.annotation.StringRes
import com.example.lunchtray.R

enum class DepositScreen(@StringRes val title: Int) {
    Start(R.string.start_title),
    Step1(R.string.step1_title),
    Step2(R.string.step2_title),
    Result(R.string.result_title)
}
