package com.alexiscrack3.spinny.login

import io.reactivex.Single
import retrofit2.http.POST

interface LoginService {

    @POST("auto/sign_in")
    fun signIn(): Single<Unit>
}
