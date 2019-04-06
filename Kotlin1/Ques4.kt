package Kotlin1

import java.util.*

fun main(){
    val sc= Scanner(System.`in`)
    println("Enter a string : ")
    calc(str = sc.nextLine())
}

fun calc(str:String){
    val l=str.length
    var uCount=0
    var dCount=0
    var lCount=0
    var oCount=0

    for(i in str){
        if (i.isUpperCase())
            uCount++
        else if (i.isLowerCase())
            lCount++
        else if (i.isDigit())
            dCount++
        else
            oCount++
    }
    println("Uppercase  : "+uCount+" : "+((uCount*100)/l).toFloat()+"%")
    println("Lowecase  : "+lCount+" : "+((lCount*100)/l).toFloat()+"%")
    println("Digits  : "+dCount+" : "+((dCount*100)/l).toFloat()+"%")
    println("Others  : "+oCount+" : "+((oCount*100)/l).toFloat()+"%")


}