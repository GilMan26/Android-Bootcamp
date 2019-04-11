package Kotlin2

fun main(){
    var map= mutableMapOf<Int, String>()
    for(i in 1..10)
        map.put(i, "Element : "+i)
    for(items in map)
        println(items)
}