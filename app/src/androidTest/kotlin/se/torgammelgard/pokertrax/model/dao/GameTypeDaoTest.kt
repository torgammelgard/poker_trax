package se.torgammelgard.pokertrax.model.dao

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import se.torgammelgard.pokertrax.model.database.AppDatabase
import se.torgammelgard.pokertrax.model.entities.GameType

@RunWith(AndroidJUnit4::class)
class GameTypeDaoTest {


    private lateinit var appDatabase: AppDatabase
    private lateinit var gameTypeDao: GameTypeDao

    @Before
    fun setup() {
        appDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), AppDatabase::class.java).build()
        gameTypeDao = appDatabase.gameTypeDao()
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    fun testGameTypeDao() {
        val gameType = GameType(1, "No limit")
        gameTypeDao.add(gameType)
        val allGameTypes = gameTypeDao.getAll()
        Assert.assertEquals(1, allGameTypes.size)
    }
}