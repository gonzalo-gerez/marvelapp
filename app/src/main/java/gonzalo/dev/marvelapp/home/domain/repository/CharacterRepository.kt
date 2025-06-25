package gonzalo.dev.marvelapp.home.domain.repository;

import gonzalo.dev.marvelapp.home.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun fetchCharacters(
        offset: Int?,
        publicKey: String,
        privateKey: String
    ): Flow<CharacterModel>
}
