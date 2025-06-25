package gonzalo.dev.marvelapp.home.data.source

import gonzalo.dev.marvelapp.home.data.dto.CharacterResponse
import kotlinx.coroutines.flow.Flow

interface CharacterDataSource {
    fun fetchCharacters(
        offset: Int?,
        publicKey: String,
        privateKey: String
    ): Flow<CharacterResponse>
}