package com.example.myfirstapp.data.remote

import com.example.myfirstapp.data.model.people.PeopleModel
import com.example.myfirstapp.data.model.room.RoomModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiDetails {

    @GET(ApiReference.PEOPLE)
    suspend fun getPeopleList(): Response<PeopleModel>

    @GET(ApiReference.ROOM)
    suspend fun getRoomList(): Response<RoomModel>

//    @GET(ApiReference.PEOPLE)
//    suspend fun getDetailsList(): Response<DetailsModel>

}