package gonzalo.dev.core.domain.usecase

import gonzalo.dev.core.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow

interface FetchCharacterUseCase {

    fun fetchCharacters(
        offset: Int?,
        publicKey: String,
        privateKey: String
    ): Flow<CharacterModel>
}