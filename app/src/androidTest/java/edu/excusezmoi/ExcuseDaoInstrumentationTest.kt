package edu.excusezmoi

import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import edu.excusezmoi.persistence.ExcuseCacheEntity
import edu.excusezmoi.persistence.ExcuseDao
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class ExcuseDaoInstrumentationTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var excuseDao: ExcuseDao

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun can_insert () {
        val excuse1 = ExcuseCacheEntity(333, "test", "text")
        val excuse2 = ExcuseCacheEntity(334, "test", "text")
        val excuses = listOf(excuse1, excuse2)
        runBlocking {
            excuseDao.insertExcuses(excuses)
            assert(excuseDao.selectExcuses().contains(excuse1))
            assert(excuseDao.selectExcuses().contains(excuse2))
        }
    }

    @Test
    fun can_nuke () {
        val excuses = listOf(ExcuseCacheEntity(333, "test", "text"), ExcuseCacheEntity(334, "test", "text"))
        runBlocking {
            excuseDao.insertExcuses(excuses)
            excuseDao.nukeExcuses()
            assert(excuseDao.selectExcuses().isEmpty())
        }
    }
}
