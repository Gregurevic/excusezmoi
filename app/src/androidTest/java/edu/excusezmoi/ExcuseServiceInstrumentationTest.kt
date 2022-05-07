package edu.excusezmoi

import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import edu.excusezmoi.network.ExcuseService
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class ExcuseServiceInstrumentationTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var excuseService: ExcuseService

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun can_getExcuses () {
        runBlocking {
            val excuses = excuseService.getExcuses()
            assert(!excuses.isNullOrEmpty())
        }
    }

    @Test
    fun can_getExcusesByCategory () {
        runBlocking {
            val excuses = excuseService.getExcuseByCategory("office")
            excuses.forEach { excuse -> assert(excuse.category == "office") }
        }
    }

    @Test
    fun can_modifyExcuses () {
        runBlocking {
            excuseService.postExcuse("test", "Test excuse text.")
            val excuse = excuseService.getExcuseByCategory("test")[0]
            assert("Test excuse text." == excuse.text)
            excuseService.patchExcuse(excuse.id, excuse.category, "Some other excuse text.")
            assert("Some other excuse text." == excuseService.getExcuseByCategory("test")[0].text)
            excuseService.destroyExcuse(excuse.id)
            assert(excuseService.getExcuseByCategory("test").isNullOrEmpty())
        }
    }
}
