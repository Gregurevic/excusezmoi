package edu.excusezmoi

import edu.excusezmoi.model.Excuse
import edu.excusezmoi.network.ExcuseNetworkEntity
import edu.excusezmoi.network.ExcuseNetworkMapper
import org.junit.Test

class ExcuseNetworkMapperUnitTest {
    private var excuseNetworkMapper = ExcuseNetworkMapper()

    @Test
    fun can_mapEntityToModel () {
        val excuse = ExcuseNetworkEntity(333, "test", "text")
        assert(excuseNetworkMapper.entityToModel(excuse) == Excuse(333, "test", "text"))
    }

    @Test
    fun can_mapModelToEntity () {
        val excuse = Excuse(333, "test", "text")
        assert(excuseNetworkMapper.modelToEntity(excuse) == ExcuseNetworkEntity(333, "test", "text"))
    }

    @Test
    fun can_mapEntityListToModelList () {
        val excuses = listOf(ExcuseNetworkEntity(333, "test", "text"), ExcuseNetworkEntity(334, "test", "text_text"))
        assert(excuseNetworkMapper.entityListToModelList(excuses) == listOf(Excuse(333, "test", "text"), Excuse(334, "test", "text_text")))
    }
}