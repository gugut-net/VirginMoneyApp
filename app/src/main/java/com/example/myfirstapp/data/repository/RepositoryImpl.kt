package com.example.myfirstapp.data.repository

import com.example.myfirstapp.data.model.people.PeopleModel
import com.example.myfirstapp.data.model.room.RoomModel
import com.example.myfirstapp.data.remote.ApiDetails
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    val apiDetails: ApiDetails

) : Repository {
    override suspend fun getPeopleList(): Response<PeopleModel> {
        return apiDetails.getPeopleList()
    }

    override suspend fun getRoomList(): Response<RoomModel> {
        return apiDetails.getRoomList()
    }
}