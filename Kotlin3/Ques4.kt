package Kotlin3

import java.util.*

fun main(){
    val sc=Scanner(System.`in`)
    println("Enter three numbers : ")
    val a=sc.nextInt()
    val b=sc.nextInt()
    val c=sc.nextInt()
    println("Sum of the numbers : ")
    println(a.add(b,c))



}

fun Int.add(a:Int, b:Int):Int{
    return this+b+a
}