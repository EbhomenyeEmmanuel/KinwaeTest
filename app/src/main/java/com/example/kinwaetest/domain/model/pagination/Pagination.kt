package com.example.kinwaetest.domain.model.pagination

data class Pagination(
    val totalPages: Int,
    val totalItems: Int,
    val itemCount: Int,
    val page: Int
) {

    companion object {
        //const val UNKNOWN_TOTAL = -1
    }

    val canLoadMore: Boolean
        get() = page < totalPages
}
