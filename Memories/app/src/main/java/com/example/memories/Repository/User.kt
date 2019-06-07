package com.example.memories.Repository

data class User(var id: String, var name: String, var url: String, var albums: ArrayList<Album>, var timeline: ArrayList<Photo>)