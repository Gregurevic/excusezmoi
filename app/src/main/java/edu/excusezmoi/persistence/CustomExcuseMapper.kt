package edu.excusezmoi.persistence

import edu.excusezmoi.model.Excuse
import edu.excusezmoi.util.EntityMapper
import javax.inject.Inject

class CustomExcuseMapper@Inject constructor() : EntityMapper<CustomExcuseEntity, Excuse> {
    override fun entityToModel(entity: CustomExcuseEntity): Excuse {
        return Excuse(
            id = entity.id,
            category = entity.category,
            text = entity.text
        )
    }

    override fun modelToEntity(domainModel: Excuse): CustomExcuseEntity {
        return CustomExcuseEntity(
            id = domainModel.id,
            category = domainModel.category,
            text = domainModel.text
        )
    }

    fun entityListToModelList(entities: List<CustomExcuseEntity>): List<Excuse> {
        return entities.map { entityToModel(it) }
    }
}
