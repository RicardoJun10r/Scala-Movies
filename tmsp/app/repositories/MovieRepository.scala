package repositories

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future}
import slick.jdbc.PostgresProfile.api._
import models.{Movie, MovieDTO}

class MovieRepository{
    private val Movies = TableQuery[MoviesTable]
    val db = Database.forConfig("database")

    private class MoviesTable(tag: Tag) extends Table[Movie](tag, "movies_tb"){
        def id = column[Option[Int]]("id", O.PrimaryKey, O.AutoInc)
        def titulo = column[String]("titulo")
        def resumo = column[String]("resumo")
        def * =
        (id, titulo, resumo) <> ((Movie.apply _).tupled, Movie.unapply)
    }

    def list(): Future[Seq[Movie]] = db.run(Movies.result)

    def create(movie: MovieDTO) = db.run(Movies += Movie.fromDTO(movie)).map { _ =>
        ()
    }

    def read(id: Int) = db.run(Movies.filter(_.id === id).result.headOption)

    def update(id: Int, movie: MovieDTO) =
        db.run(
        Movies.filter(i => i.id === id).update(Movie.fromDTO(movie))
        )

    def delete(id: Int) = db.run(Movies.filter(i => i.id === id).delete)

}