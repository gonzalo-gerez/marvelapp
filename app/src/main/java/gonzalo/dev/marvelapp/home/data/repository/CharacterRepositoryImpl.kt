package gonzalo.dev.marvelapp.home.data.repository

import gonzalo.dev.marvelapp.common.di.RepositoryModule
import gonzalo.dev.marvelapp.home.data.source.CharacterDataSource
import gonzalo.dev.marvelapp.home.domain.mapper.toCharacter
import gonzalo.dev.marvelapp.home.domain.model.CharacterModel
import gonzalo.dev.marvelapp.home.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val remoteSource: CharacterDataSource
) : CharacterRepository {

    override fun fetchCharacters(
        offset: Int?,
        publicKey: String,
        privateKey: String
    ): Flow<CharacterModel> {
        return flow {
            remoteSource.fetchCharacters(offset, publicKey, privateKey).collect {
                emit(it.toCharacter())
            }
        }
    }
}