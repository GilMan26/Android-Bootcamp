package Kotlin3

sealed class Person

class Trainee:Person(){
    var info="Trainee"
}

class Owner:Person(){
    var info="Owner"
}

class Manager:Person(){
    var info="Manager"
}

fun returnInfo(subClass:Person) = when (subClass){
    is Trainee -> subClass.info
    is Owner -> subClass.info
    is Manager -> subClass.info
}


fun main(){
    println(returnInfo(Trainee()))

}