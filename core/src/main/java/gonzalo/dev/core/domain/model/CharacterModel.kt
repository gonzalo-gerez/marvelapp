package gonzalo.dev.core.domain.model

import gonzalo.dev.core.data.dto.Thumbnail
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