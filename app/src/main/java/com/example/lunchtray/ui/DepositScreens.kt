package com.example.lunchtray

import androidx.annotation.StringRes
import com.example.lunchtray.R

enum class DepositScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    Step1(title = R.string.step1_title), // первоначальный взнос + ставка
    Step2(title = R.string.step2_title), // ежемесячное пополнение + месяцы
    Result(title = R.string.result_title) // итог
}
