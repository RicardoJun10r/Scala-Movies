package models

import javax.inject.Inject
import play.api.libs.json.{Json, Format}

case class Movie(
    id: Option[Int],
    titulo: String,
    resumo: String
)

object Movie {
    implicit val format: Format[Movie] = Json.format[Movie]

    def fromDTO(dto: MovieDTO): Movie =
        new Movie(
            dto.id,
            dto.titulo,
            dto.resumo
        )
}

case class MovieDTO(
    id: Option[Int],
    titulo: String,
    resumo: String
)

object MovieDTO{
    implicit val format: Format[MovieDTO] = Json.format[MovieDTO]
}