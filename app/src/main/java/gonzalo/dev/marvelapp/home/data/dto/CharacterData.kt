package gonzalo.dev.marvelapp.home.data.dto

data class CharacterData(
    val id: Long,
    val name: String,
    val description: String,
    val modified: String,
    val thumbnail: Thumbnail,
    val resourceURI: String,
    val comics: Comics,
    val series: Series,
    val stories: Stories,
    val events: Events
)

data class Thumbnail(
    val path: String,
    val extension: String
)

data class CharacterResources(val available: Int, val collectionURI: String)

typealias Comics = CharacterResources
typealias Series = CharacterResources
typealias Stories = CharacterResources
typealias Events = CharacterResources