package com.example.memories.Repository


data class Album(var id:String="", var title:String="", var time:String="" , var messaage:String="", var photos: MutableList<Photo> = mutableListOf())