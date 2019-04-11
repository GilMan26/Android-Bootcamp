package Kotlin2

fun main(){
    var map= hashMapOf<Int, String>()
    for (i in 1..10)
        map.put(i, "Element : "+i)
    for(items in map)
        println(items)
    println("-------------------")
    for (i in 5..15)
        map.put(i, "Number : "+i)
    for(items in map)
        println(items)
    println("--------------------------")
}