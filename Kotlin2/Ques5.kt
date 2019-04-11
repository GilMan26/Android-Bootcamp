package Kotlin2

fun main(){
    println(getGrade(33))
}

fun getGrade(marks:Int):String{
    when(marks ){
        in 50..60->return "Good"
        in 60..70-> return "Very Good"
        in 70..80-> return "Excelent"
        in 80..100-> return "Rockstar"
        else->return "Fired"
    }
}