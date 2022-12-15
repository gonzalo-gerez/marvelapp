package gonzalo.dev.core.data.repository;

import gonzalo.dev.core.data.dto.CharacterResponse
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun fetchCharacters(
        offset: Int?,
        publicKey: String,
        privateKey: String
    ): Flow<CharacterResponse>
}
