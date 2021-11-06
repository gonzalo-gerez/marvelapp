package gonzalo.dev.core.datasource.repository;

import gonzalo.dev.core.datasource.dto.CharacterResponse
import kotlinx.coroutines.flow.Flow

public interface CharacterRepository {

    fun fetchCharacters(offset: Int?): Flow<CharacterResponse>
}
