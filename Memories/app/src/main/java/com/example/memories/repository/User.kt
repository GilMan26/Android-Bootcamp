package com.example.memories.repository

data class User(var id: String="", var name: String="", var url: String="", var albums: ArrayList<Album> = ArrayList(), var timeline: ArrayList<Photo> = java.util.ArrayList())