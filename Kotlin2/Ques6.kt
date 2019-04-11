package Kotlin2

fun main(){
    var arr= mutableListOf<Int>()
    for(i in 1..10)
        arr.add(i)
    for(items in arr)
        println(items)
    arr[1]=100
    println("------------------")
    for (items in arr)
        println(items)
}