package edu.excusezmoi.persistence

import edu.excusezmoi.model.Excuse
import edu.excusezmoi.util.EntityMapper
import javax.inject.Inject

class ModificationMapper@Inject constructor() : EntityMapper<ModificationEntity, Excuse> {
    override fun entityToModel(entity: ModificationEntity): Excuse {
        return Excuse(
            id = entity.id,
            category = entity.category,
            text = entity.text
        )
    }

    override fun modelToEntity(domainModel: Excuse): ModificationEntity {
        return ModificationEntity(
            id = domainModel.id,
            category = domainModel.category,
            text = domainModel.text
        )
    }

    fun entityListToModelList(entities: List<ModificationEntity>): List<Excuse> {
        return entities.map { entityToModel(it) }
    }
}
