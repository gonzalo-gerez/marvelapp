package gonzalo.dev.core.data.services

import gonzalo.dev.core.common.Timeout
import gonzalo.dev.core.data.dto.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

@Timeout(timeout = 10)
interface HomeService {

    @GET("/v1/public/characters")
    suspend fun fetchCharacters(
        @Query("apikey") apiKey: String,
        @Query("ts") ts: Long = 1,
        @Query("offset") offset: Int?,
        @Query("limit") limit: Int = 20,
        @Query("hash") hash: String
    ): CharacterResponse
}