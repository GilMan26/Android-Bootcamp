package com.example.memories.Repository

import java.sql.Timestamp

data class Photo(var id:Long, var title:String, var albumID:Long, var url:String,  var time: Timestamp, var info:String)