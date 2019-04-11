package Kotlin2

fun main(){
    val obj=Operations()
    println(obj.add(2,3))
    println(obj.add(20.34, 30.21))
    println(obj.multiply(5,6))
    println(obj.concat("Hey ", "There"))
    println(obj.concat("Hey ", "There ", "Friend"))
}

class Operations(){
    fun add(a:Int, b:Int)=a+b

    fun add(a:Double, b:Double)=a+b

    fun multiply(a:Int, b:Int)=a*b

    fun concat(a:String, b:String)=a+b

    fun concat(a:String, b:String, c:String)=a+b+c
}
