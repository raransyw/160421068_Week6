package com.example.`160421068_Week4`.model

import com.google.gson.annotations.SerializedName

data class Cars(
    var id:String?,
    var make:String?,
    var model:String?,
    var year:String?,
    var colors:List<String>?,
    var features:CarsSpecs?,
    var images:String?
)

data class CarsSpecs(
    var engine:String?,
    var transmisson:String?
)

data class Student(
    var id:String?,
    @SerializedName("student_name")
    var name:String?,
    @SerializedName("birth_of_date")
    var dob:String?,
    var phone:String?,
    @SerializedName("photo_url")
    var photoUrl:String?
)



