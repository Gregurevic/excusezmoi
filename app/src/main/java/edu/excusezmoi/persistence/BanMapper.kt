package edu.excusezmoi.persistence

import edu.excusezmoi.model.Ban
import edu.excusezmoi.util.EntityMapper
import javax.inject.Inject

class BanMapper@Inject constructor() : EntityMapper<BanEntity, Ban> {
    override fun entityToModel(entity: BanEntity): Ban {
        return Ban(
            id = entity.id
        )
    }

    override fun modelToEntity(domainModel: Ban): BanEntity {
        return BanEntity(
            id = domainModel.id
        )
    }

    fun entityListToModelList(entities: List<BanEntity>): List<Ban> {
        return entities.map { entityToModel(it) }
    }
}
