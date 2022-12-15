package gonzalo.dev.core.domain.usecase

import gonzalo.dev.core.data.repository.CharacterRepository
import gonzalo.dev.core.domain.mapper.toCharacter
import gonzalo.dev.core.domain.model.CharacterModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class FetchCharacterUseCaseImpl constructor(private val repository: CharacterRepository) :
    FetchCharacterUseCase {

    override fun fetchCharacters(
        offset: Int?,
        publicKey: String,
        privateKey: String
    ): Flow<CharacterModel> = flow {
        repository.fetchCharacters(offset, publicKey, privateKey).collect {
            emit(it.toCharacter())
        }
    }.flowOn(Dispatchers.IO)
}