package com.transpire.myapplication.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.delay

class MainViewModel : ViewModel() {
    val viewModelValue = MyRepo.liveValue
    fun viewModelGetNextValue(){
        MyRepo.getNextValue()
    }

    val viewModelSquareValue = MyRepo.squareLiveValue
    fun viewModelGetSquareValue(x:Int){
        MyRepo.getSquareValue(x)
    }

    val viewModelEmitValue = MyRepo.emitLiveValue

    lateinit var viewModelEmitFunctionValue:LiveData<String>
    fun viewModelEmitLiveFunction(x:Int){
        viewModelEmitFunctionValue = MyRepo.emitLiveFunction(x)
    }
}

object MyRepo{
    var value = 1

    val liveValue = MutableLiveData<Int>()
    fun getNextValue(){
        liveValue.postValue(++value)
    }

    val squareLiveValue = MutableLiveData<Int>()
    fun getSquareValue(x:Int){
        squareLiveValue.postValue(x*x)
    }

    val emitLiveValue = liveData {
        emit("First Emit")
        delay(2000)
        emit("second value")
    }

    fun emitLiveFunction(x:Int) = liveData {
        emit("value: $x")
        delay(2000)
        emit("square: ${x*x}")
    }
}