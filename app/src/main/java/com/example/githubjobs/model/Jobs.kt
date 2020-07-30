package com.example.githubjobs.model

import java.io.Serializable

data class Jobs(
    val id: String?,
    val createdAt: String?,
    val title: String?,
    val location: String?,
    val type: String?,
    val description: String?,
    val howToApply: String?,
    val company: String?,
    val companyURL: String?,
    val companyLogo: String?,
    val URL: String?

): Serializable