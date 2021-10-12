package com.sphtechapp.sphtestapp.model

data class Result(
    val _links: Links,
    val fields: List<Field>,
    val limit: Int,
    val records: List<Record>,
    val resource_id: String,
    val total: Int
)