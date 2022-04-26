package com.example.a2022_04_24_sadafmackertich__nycschools.Network

data class SchoolSATDataObject(
    val dbn: String,
    val num_of_sat_test_takers: String,
    val sat_critical_reading_avg_score: String,
    val sat_math_avg_score: String,
    val sat_writing_avg_score: String,
    val school_name: String
)
