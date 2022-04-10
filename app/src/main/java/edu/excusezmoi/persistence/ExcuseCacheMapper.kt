package edu.excusezmoi.persistence

import edu.excusezmoi.model.Excuse
import edu.excusezmoi.util.EntityMapper
import javax.inject.Inject

class ExcuseCacheMapper@Inject constructor() : EntityMapper<ExcuseCacheEntity, Excuse> {
    override fun entityToModel(entity: ExcuseCacheEntity): Excuse {
        return Excuse(
            id = entity.id,
            category = entity.category,
            text = entity.text
        )
    }

    override fun modelToEntity(domainModel: Excuse): ExcuseCacheEntity {
        return ExcuseCacheEntity(
            id = domainModel.id,
            category = domainModel.category,
            text = domainModel.text
        )
    }

    fun entityListToModelList(entities: List<ExcuseCacheEntity>): List<Excuse> {
        return entities.map { entityToModel(it) }
    }
}
