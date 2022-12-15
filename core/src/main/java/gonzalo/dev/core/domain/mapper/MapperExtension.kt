package gonzalo.dev.core.domain.mapper

import gonzalo.dev.core.data.dto.CharacterResponse
import gonzalo.dev.core.domain.model.CharacterDataModel
import gonzalo.dev.core.domain.model.CharacterModel


fun CharacterResponse.toCharacter(): CharacterModel {
    return CharacterModel(
        data.offset,
        data.count,
        data.results.map {
            CharacterDataModel(
                id = it.id,
                name = it.name,
                description = it.description,
                thumbnail = it.thumbnail,
                resourceURI = it.resourceURI,
            )
        })
}