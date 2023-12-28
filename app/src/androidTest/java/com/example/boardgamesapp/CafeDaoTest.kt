package com.example.boardgamesapp

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.boardgamesapp.data.database.CafeDao
import com.example.boardgamesapp.data.database.CafeDb
import com.example.boardgamesapp.data.database.asDbCafe
import com.example.boardgamesapp.data.database.asDomainCafe
import com.example.boardgamesapp.model.Cafe
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CafeDaoTest {
    private lateinit var cafeDao: CafeDao
    private lateinit var cafeDb: CafeDb

    private var cafe1 = Cafe(
        id = 1,
        nameNl = "Aba-jour"
    )

    private var cafe2 = Cafe(
        id = 2,
        nameNl = "Charlatan"
    )

    private suspend fun addOneCafeToDb() {
        cafeDao.insert(cafe1.asDbCafe())
    }

    private suspend fun addTwoCafesToDb() {
        cafeDao.insert(cafe1.asDbCafe())
        cafeDao.insert(cafe2.asDbCafe())
    }

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        cafeDb = Room.inMemoryDatabaseBuilder(context, CafeDb::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        cafeDao = cafeDb.cafeDao()
    }

    @After
    fun closeDb() {
        cafeDb.close()
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_insertCafeIntoDB() = runBlocking {
        addOneCafeToDb()
        val cafes = cafeDao.getAllItems().first()
        assertEquals(cafes[0].asDomainCafe(), cafe1)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllCafes_returnsAllCafesFromDB() = runBlocking {
        addTwoCafesToDb()
        val cafes = cafeDao.getAllItems().first()
        assertEquals(cafes[0].asDomainCafe(), cafe1)
        assertEquals(cafes[1].asDomainCafe(), cafe2)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetCafeByName_returnsCafeFromDB() = runBlocking {
        addTwoCafesToDb()
        val cafe = cafeDao.getItem("Charlatan").first()
        assertEquals(cafe.asDomainCafe(), cafe2)
    }

    @Test
    @Throws(Exception::class)
    fun daoDeleteAllCafes_deletesAllCafesFromDB() = runBlocking {
        addTwoCafesToDb()
        for (cafe in cafeDao.getAllItems().first()) {
            cafeDao.delete(cafe)
        }
        val cafes = cafeDao.getAllItems().first()
        assertEquals(cafes.size, 0)
    }
}
