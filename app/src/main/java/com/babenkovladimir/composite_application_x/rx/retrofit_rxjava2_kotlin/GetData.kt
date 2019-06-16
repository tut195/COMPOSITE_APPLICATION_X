package com.babenkovladimir.composite_application_x.rx.retrofit_rxjava2_kotlin

import io.reactivex.Observable
import retrofit2.http.GET

interface GetData {

    // Subscribe the request type and the relative URL

    @GET("prices?key=6a6c5241c14893749d96e3c83a4cf2a9")
    fun getData(): Observable<List<RetroCrypto>>

}