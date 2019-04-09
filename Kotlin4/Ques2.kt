package Kotlin4

fun main(){
    var list=ArrayList<Person>()
    list.add(Person("Mandeep",21))
    list.add(Person("Anuvansh", 24))
    list.add(Person("Prashant", 22))
    list.add(Person("Aman",23))
    list.add(Person("Ujjwal", 22))

    var res=list.filter { it.age >=22 }.map { it.name }
    println(res)
}

class Person(var name:String, var age:Int)