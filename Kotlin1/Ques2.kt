package Kotlin1

fun main() {

    val str="Hello hello friend friend how how you doing doig how"
    findOccurences(str)

}

fun findOccurences(str: String) {
    val arrayString = str.split(" ")
    val map: HashMap<String, Int> = HashMap()

    for (item in arrayString) {
        if (map.containsKey(item))
            map[item] = map[item]!! + 1
        else
            map.put(item, 1)
    }
    for (key in map.keys) {
        print("Occurrence for $key : ${map[key]} \n")
    }
}