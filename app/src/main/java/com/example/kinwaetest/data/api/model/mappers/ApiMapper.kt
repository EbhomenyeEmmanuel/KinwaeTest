package com.example.kinwaetest.data.api.model.mappers

interface ApiMapper<E, D> {

  fun mapToDomain(apiEntity: E): D
}