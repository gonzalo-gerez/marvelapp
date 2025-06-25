package gonzalo.dev.marvelapp.home.domain.mapper

import gonzalo.dev.marvelapp.home.data.dto.CharacterResponse
import gonzalo.dev.marvelapp.home.domain.model.CharacterDataModel
import gonzalo.dev.marvelapp.home.domain.model.CharacterModel


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