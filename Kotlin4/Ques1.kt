package Kotlin4

fun main(){
    val lambdaSI:(Int, Float, Int) ->Float = {p:Int, r:Float, t:Int -> ((p*r*t)/100)}
    getSI(10000,3.5f, 3, lambdaSI)
}

fun getSI(p:Int, r:Float, t:Int, action:(Int, Float,Int)->Float){
    var interest=action(p, r, t)
    println(interest)
}