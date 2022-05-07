package edu.excusezmoi

import edu.excusezmoi.model.Excuse
import edu.excusezmoi.persistence.ExcuseCacheEntity
import edu.excusezmoi.persistence.ExcuseCacheMapper
import org.junit.Test

class ExcuseCacheMapperUnitTest {
    private var excuseCacheMapper = ExcuseCacheMapper()

    @Test
    fun can_mapEntityToModel () {
        val excuse = ExcuseCacheEntity(333, "test", "text")
        assert(excuseCacheMapper.entityToModel(excuse) == Excuse(333, "test", "text"))
    }

    @Test
    fun can_mapModelToEntity () {
        val excuse = Excuse(333, "test", "text")
        assert(excuseCacheMapper.modelToEntity(excuse) == ExcuseCacheEntity(333, "test", "text"))
    }

    @Test
    fun can_mapEntityListToModelList () {
        val excuses = listOf(ExcuseCacheEntity(333, "test", "text"), ExcuseCacheEntity(334, "test", "text_text"))
        assert(excuseCacheMapper.entityListToModelList(excuses) == listOf(Excuse(333, "test", "text"), Excuse(334, "test", "text_text")))
    }

    @Test
    fun can_mapModelListToEntityList () {
        val excuses = listOf(Excuse(333, "test", "text"), Excuse(334, "test", "text_text"))
        assert(excuseCacheMapper.modelListToEntityList(excuses) == listOf(ExcuseCacheEntity(333, "test", "text"), ExcuseCacheEntity(334, "test", "text_text")))
    }
}