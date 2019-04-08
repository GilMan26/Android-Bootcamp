package Kotlin3

fun classNotFound(){
    try {
        Class.forName("MandeepSingh")
    } catch (e: ClassNotFoundException) {
        println(e)
    }
}

class ExceptionThrower {
    companion object {
        val calculation = 1 / 0
    }
}

fun main(){
    classNotFound()

    println()

    var exceptionThrower: ExceptionThrower

    try {
        exceptionThrower = ExceptionThrower()
    } catch (e: ExceptionInInitializerError) {

    } finally {
        exceptionThrower = ExceptionThrower()
    }
}