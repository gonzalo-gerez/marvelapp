package gonzalo.dev.marvelapp.home.implementations

import gonzalo.dev.core.datasource.dto.CharacterResponse
import gonzalo.dev.core.datasource.repository.CharacterRepository
import gonzalo.dev.marvelapp.home.service.HomeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CharacterFromNetwork @Inject constructor(private val homeService: HomeService) :
    CharacterRepository {

    override fun fetchCharacters(offset: Int?): Flow<CharacterResponse> = flow {
        val result = homeService.fetchCharacters(offset = offset)
        emit(result)
    }.flowOn(Dispatchers.IO)
}