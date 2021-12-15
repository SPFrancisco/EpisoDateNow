package com.example.episodatenow.model

class Pagination (var page : Int, var total : Int, var pages : Int) {
    val totalPages : Int = total / 20
}