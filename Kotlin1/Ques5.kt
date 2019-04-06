package Kotlin1

fun main(){
    val arr1= arrayOf(1,2,3,4,5,6,7,10)
    val arr2= arrayOf(2,3,1,5,9,9,11)
    for(i in arr1){
        for(j in arr2){
            if(i==j)
                println(i)
        }
    }


}
