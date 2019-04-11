package Kotlin2

import Kotlin2.Trainee.Companion.age
import Kotlin2.Trainee.Companion.fname
import Kotlin2.Trainee.Companion.lname

fun main(){
    println("$fname $lname is $age years old")
    Trainee()
}

class Trainee(){
    var fname:String = ""
    var lname:String =""
    var age:Int = 0


    companion object {
        val fname="Mandeep"
        val lname="Singh"
        val age=21
    }

    init {
        fname="Ganesh"
        lname="Gaitonde"
        age=45
        println("$fname $lname  is $age years old" )
    }

}