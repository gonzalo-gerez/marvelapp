package gonzalo.dev.core.usecase

import gonzalo.dev.core.datasource.dto.CharacterResponse
import gonzalo.dev.core.datasource.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

class FetchCharacterUseCase constructor(private val repository: CharacterRepository) {

    fun fetchCharacters(offset: Int?): Flow<CharacterResponse> = repository.fetchCharacters(offset)
}