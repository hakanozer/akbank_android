package com.works.project.services

import com.works.project.models.JWTUser
import com.works.project.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface DummyService {

    @POST("auth/login")
    fun login( @Body jwtUser: JWTUser ) : Call<User>

}