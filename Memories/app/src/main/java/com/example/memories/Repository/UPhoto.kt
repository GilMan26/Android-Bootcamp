package com.example.memories.Repository

import java.sql.Timestamp

data class UPhoto(var id:Long, var title:String, var albumID:Long, var url:String,  var time: Timestamp, var info:String) {


        var pId:Long=id
        var name:String=title
        var aId=albumID
        var imageUrl:String=url
        var timestamp:Timestamp=time
        var notes:String=info
}