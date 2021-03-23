/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.stateparkapp.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.stateparkapp.model.entity.StateParks
import kotlinx.coroutines.flow.Flow

/**
 * Defines methods for using the SleepNight class with Room.
 */

@Dao
interface StateParksDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(park: StateParks)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(parks: List<StateParks>)

    @Update
    suspend fun update(parks: StateParks)

    @Query("SELECT * from state_parks ORDER BY name ASC")
    fun getAll(): LiveData<List<StateParks>>

    /**
     * Deletes all values from the table.
     *
     * This does not delete the table, only its contents.
     */

    @Query("DELETE FROM state_parks")
    suspend fun clear()

//    /**
//     * Selects and returns all rows in the table,
//     *
//     * sorted alphabetically in descending order.
//     */
//    @Query("SELECT * FROM state_parks ORDER BY parksId DESC")
//    fun getAllParks(key: String): StateParks

//    /**
//     * Selects and returns the latest park.
//     */
//    @Query("SELECT * FROM state_parks ORDER BY parksId DESC LIMIT 1")
//    suspend fun getPark(): StateParks?
//
//    /**
//     * Selects and returns the park with given parkId.
//     */
//    @Query("SELECT * from state_parks WHERE parksId = :key")
//    fun getParkWithId(key: Long): LiveData<StateParks>
}

