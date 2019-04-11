package Kotlin2

fun main(){
    val sbi=SBI()
    val icici= ICICI()
    val boi = BOI()
    val bank=Bank()

    sbi.getDetails()
    icici.getDetails()
    boi.getDetails()
    bank.getDetails()
}

open class Bank{

    open fun getDetails(){
        val roi:Float = 3.5f
        val amount:Int=100011
        println("Bank : $roi : for $amount")
    }
}

class SBI: Bank() {
    override fun getDetails() {
        val roi:Float = 3.6f
        val amount:Int=100067
        println("SBI : $roi : for $amount")
    }
}

class ICICI:Bank(){
    override fun getDetails() {
        val roi:Float = 3.75f
        val amount:Int=100045
        println("ICICI : $roi : for $amount")
    }
}

class BOI:Bank(){
    override fun getDetails() {
        val roi:Float = 4.0f
        val amount:Int=100023
        println("BOI : $roi : for $amount")
    }
}