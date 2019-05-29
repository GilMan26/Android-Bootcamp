package com.example.memories.Repository

import java.sql.Timestamp

data class Album(var id:Long, var title:String, var time:String , var image:String, var messaage:String) {
    var aId: Long=id
    var name: String=title
    var timestamp: String=time
    var cover: String=image
    var notes: String=messaage

}