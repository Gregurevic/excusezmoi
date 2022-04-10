package edu.excusezmoi.network

import edu.excusezmoi.model.Excuse
import edu.excusezmoi.util.EntityMapper
import javax.inject.Inject

class ExcuseNetworkMapper @Inject constructor() : EntityMapper<ExcuseNetworkEntity, Excuse>{
    override fun entityToModel(entity: ExcuseNetworkEntity): Excuse {
        return Excuse(
            id = entity.id,
            category = entity.category,
            text = entity.text
        )
    }

    override fun modelToEntity(domainModel: Excuse): ExcuseNetworkEntity {
        return ExcuseNetworkEntity(
            id = domainModel.id,
            category = domainModel.category,
            text = domainModel.text
        )
    }

    fun entityListToModelList(entities: List<ExcuseNetworkEntity>): List<Excuse> {
        return entities.map { entityToModel(it) }
    }
}
