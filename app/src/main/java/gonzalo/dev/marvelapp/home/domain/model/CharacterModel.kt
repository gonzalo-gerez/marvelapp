package gonzalo.dev.marvelapp.home.domain.model

import gonzalo.dev.marvelapp.home.data.dto.Thumbnail
import java.io.Serializable

class CharacterModel(
    val offset: Int,
    val count: Int,
    val data: List<CharacterDataModel>
) : Serializable

class CharacterDataModel(
    val id: Long,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail,
    val resourceURI: String,
    var clicked: Boolean = false
) : Serializable