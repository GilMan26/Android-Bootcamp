package com.example.memories.repository


data class Album(var id: String = "", var title: String = "", var time: String = "", var messaage: String = "", var photos: MutableList<Photo> = mutableListOf())