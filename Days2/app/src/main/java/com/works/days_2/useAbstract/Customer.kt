package com.works.days_2.useAbstract

class Customer(private val userNumber: Int) : User() {

    override fun userNumber(): Int {
        return userNumber
    }

}