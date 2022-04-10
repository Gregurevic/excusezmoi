package edu.excusezmoi.util

interface EntityMapper<Entity, DomainModel> {

    fun entityToModel(entity: Entity): DomainModel

    fun modelToEntity(domainModel: DomainModel): Entity
}