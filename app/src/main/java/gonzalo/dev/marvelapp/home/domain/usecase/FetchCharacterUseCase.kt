package gonzalo.dev.marvelapp.home.domain.usecase

import gonzalo.dev.marvelapp.common.di.RepositoryModule
import gonzalo.dev.marvelapp.home.domain.model.CharacterModel
import gonzalo.dev.marvelapp.home.domain.repository.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FetchCharacterUseCase @Inject constructor(
    @RepositoryModule.CharacterRepositoryQ private val repository: CharacterRepository
) {

    fun fetchCharacters(
        offset: Int?,
        publicKey: String,
        privateKey: String
    ): Flow<CharacterModel> = flow {
        repository.fetchCharacters(offset, publicKey, privateKey).collect {
            emit(it)
        }
    }.flowOn(Dispatchers.IO)
}