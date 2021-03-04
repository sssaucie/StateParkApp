package com.example.stateparkapp.model

import kotlinx.coroutines.Deferred

//
//private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"
//interface StateParksApiService {
//    @GET("json")
//    fun getChapters():
//    // The Coroutine Call Adapter allows us to return a Deferred, a Job with a result
//            Deferred<GdgResponse>
//}
//private val moshi = Moshi.Builder()
//    .add(KotlinJsonAdapterFactory())
//    .build()
//
//private val retrofit = Retrofit.Builder()
//    .addConverterFactory(MoshiConverterFactory.create(moshi))
//    .addCallAdapterFactory(CoroutineCallAdapterFactory())
//    .baseUrl(BASE_URL)
//    .build()
//
//object GdgApi {
//    val retrofitService : GdgApiService by lazy { retrofit.create(GdgApiService::class.java) }
//}