package com.works.days_2.utils

open class Base {

    var _a:Int = 0
    constructor() {

    }

    constructor(a: Int) {
        _a = a
    }

    open fun call() : Int {
        return _a * _a;
    }



}