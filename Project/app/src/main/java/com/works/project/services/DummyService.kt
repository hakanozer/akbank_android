package com.works.project.services

import com.works.project.models.JWTUser
import com.works.project.models.Product
import com.works.project.models.Products
import com.works.project.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface DummyService {

    @POST("auth/login")
    fun login( @Body jwtUser: JWTUser ) : Call<User>

    @GET("products")
    fun allProduct() : Call<Products>

    @GET("products/{id}")
    fun singleProduct( @Path("id") id: Long ) : Call<Product>



}