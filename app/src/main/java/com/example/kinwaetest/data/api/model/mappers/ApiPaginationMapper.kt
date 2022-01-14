package com.example.kinwaetest.data.api.model.mappers

import com.example.kinwaetest.data.api.model.ApiPagination
import com.example.kinwaetest.domain.model.pagination.Pagination
import javax.inject.Inject

class ApiPaginationMapper @Inject constructor(): ApiMapper<ApiPagination, Pagination>{
    override fun mapToDomain(apiEntity: ApiPagination): Pagination {
        return  Pagination(apiEntity.totalPages, apiEntity.totalItems, apiEntity.itemCount, apiEntity.page)
    }
}