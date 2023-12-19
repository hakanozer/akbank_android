package com.works.days_2.useAbstract

abstract class User {

    abstract fun userNumber() : Int

    fun userName() : String {
        val number = userNumber()
        if (number == 100) {
            return "Ali Bilmem"
        }else if  (number == 200) {
            return "Erkan Bilsin"
        }else {
            return "";
        }
    }

    fun userTotal () : Int {
        val number = userNumber()
        if (number == 100 ){
            return 100000
        }else if (number == 200) {
            return 200000
        }else {
            return 0
        }
    }

}