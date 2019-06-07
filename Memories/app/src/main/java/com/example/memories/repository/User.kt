package com.example.memories.repository

data class User(var id: String, var name: String, var url: String, var albums: ArrayList<Album>, var timeline: ArrayList<Photo>)