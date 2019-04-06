package Kotlin1

import java.util.*


fun main(){
    val reader = Scanner(System.`in`)
    println("Enter a String")
    val stringInput = reader.nextLine()
    println("Enter sub string and the new string : ")
    val sub=reader.nextLine()
    val new=reader.nextLine()

    println(replaceSub(stringInput, sub ,new ))
}

fun replaceSub(str:String, sub:String, new:String):String{
    val res:String=str.replace(sub, new)
    return res
}