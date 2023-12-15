package com.example.myreminders_garciadopcio

/**A data class called Note that stores a title and a description. It is used to generate notes that we will store later.
*/

data class Note(
    //var id: String,
    val title: String = "",
    val description: String = "",
    val state: Boolean = false
)
