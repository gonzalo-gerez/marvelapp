package gonzalo.dev.marvelapp.home.data.source.remote

import gonzalo.dev.marvelapp.common.annotation.Utils
import gonzalo.dev.marvelapp.home.data.dto.CharacterResponse
import gonzalo.dev.marvelapp.home.data.services.HomeService
import gonzalo.dev.marvelapp.home.data.source.CharacterDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CharacterRemoteSource @Inject constructor(private val service: HomeService) :
    CharacterDataSource {

    override fun fetchCharacters(
        offset: Int?,
        publicKey: String,
        privateKey: String
    ): Flow<CharacterResponse> {
        return flow {
            val result = service.fetchCharacters(
                apiKey = publicKey,
                offset = offset,
                hash = Utils.toMD5(privateKey, publicKey)
            )
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
}