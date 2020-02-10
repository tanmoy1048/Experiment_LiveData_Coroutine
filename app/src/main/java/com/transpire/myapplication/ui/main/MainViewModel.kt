package com.transpire.myapplication.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    val viewModelEmitValue = MyRepo.emitLiveValue

    lateinit var viewModelEmitFunctionValue:LiveData<String>
    fun viewModelEmitLiveFunction(x:Int){
        viewModelEmitFunctionValue = MyRepo.emitLiveFunction(x)
    }
}

object MyRepo{
    var value = 1


    val emitLiveValue = liveData {
        emit("First Emit")
        delay(2000)
        emit("second value")
    }

    fun emitLiveFunction(x:Int) = liveData {
        emit("value: $x")
        emit(doSomeWork(x))
        delay(1000)
        emit("square: ${x*x}")
    }

    suspend fun doSomeWork(x:Int):String{
        withContext(IO){
            delay(4000)
        }
        return "Return Value ${x*x*x}"
    }
}