package com.example.myfirstapp.data.repository

import com.example.myfirstapp.data.model.people.PeopleModel
import com.example.myfirstapp.data.model.room.RoomModel
import retrofit2.Response

interface Repository {

    suspend fun getPeopleList(): Response<PeopleModel>

    suspend fun getRoomList(): Response<RoomModel>

//    suspend fun getDetailsList(): Response<DetailsModel>
}