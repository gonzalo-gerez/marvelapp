package gonzalo.dev.core.data.repository

import gonzalo.dev.core.common.Utils
import gonzalo.dev.core.data.dto.CharacterResponse
import gonzalo.dev.core.data.services.HomeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CharacterFromNetwork @Inject constructor(private val homeService: HomeService) :
    CharacterRepository {

    override fun fetchCharacters(
        offset: Int?,
        publicKey: String,
        privateKey: String
    ): Flow<CharacterResponse> = flow {
        val result = homeService.fetchCharacters(
            apiKey = publicKey,
            offset = offset,
            hash = Utils.toMD5(privateKey, publicKey)
        )
        emit(result)
    }.flowOn(Dispatchers.IO)
}