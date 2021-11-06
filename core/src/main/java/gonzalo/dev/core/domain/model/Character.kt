package gonzalo.dev.core.domain.model

import gonzalo.dev.core.datasource.dto.Thumbnail
import java.io.Serializable

class Character(
    val id: Long,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail,
    val resourceURI: String,
    var clicked: Boolean = false
) : Serializable