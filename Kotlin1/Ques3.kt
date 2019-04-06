package Kotlin1

import java.util.*

fun main(){
    val sc=Scanner(System.`in`)
    println("Enter a string")
    val str=sc.nextLine()
    println("Enter a character")
    val c=sc.next()
    println(checkOccurence(str, c))
}

fun checkOccurence(str:String, c:String):Int{
    val l=str.length
    val temp=str.replace(c, "")
    return l-temp.length
}