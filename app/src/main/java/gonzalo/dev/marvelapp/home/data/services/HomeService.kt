package gonzalo.dev.marvelapp.home.data.services

import gonzalo.dev.marvelapp.common.annotation.Timeout
import gonzalo.dev.marvelapp.home.data.dto.CharacterResponse
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