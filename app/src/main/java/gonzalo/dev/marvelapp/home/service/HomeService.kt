package gonzalo.dev.marvelapp.home.service

import gonzalo.dev.core.datasource.dto.CharacterResponse
import gonzalo.dev.marvelapp.BuildConfig
import gonzalo.dev.marvelapp.common.annotation.Timeout
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query
import java.math.BigInteger
import java.security.MessageDigest

@Timeout(timeout = 10)
interface HomeService {

    @GET("/v1/public/characters")
    suspend fun fetchCharacters(
        @Query("apikey") apiKey: String = BuildConfig.PUBLIC_API_KEY,
        @Query("ts") ts: Long = 1,
        @Query("offset") offset: Int?,
        @Query("limit") limit: Int = 20,
        @Query("hash") hash: String =
            BigInteger(
                1,
                MessageDigest.getInstance("MD5")
                    .digest(("1" + BuildConfig.PRIVATE_API_KEY + BuildConfig.PUBLIC_API_KEY).toByteArray())
            ).toString(16).padStart(32, '0')
    ): CharacterResponse
}