package Kotlin1

fun main(){
    println(checkPair())
}

fun checkPair():Int{
    val arr= arrayOf(1,1,2,2,3,3,4,5,5)
    var res=0
    for(items in arr)
        res=res xor items
    return res
}