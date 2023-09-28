package controllers

import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents, Action, AnyContent, Request}
import play.api.libs.json.Json
import scala.concurrent.{ExecutionContext, Future}
import models.{Movie, MovieDTO}
import repositories.MovieRepository

class MovieController @Inject()(
    cc: ControllerComponents,
    movieRepo: MovieRepository
)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  def listMovies: Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    movieRepo.list().map { movies =>
      Ok(Json.toJson(movies))
    }
  }

  def createMovie: Action[MovieDTO] = Action.async(parse.json[MovieDTO]) { request =>
    val movieDTO = request.body
    movieRepo.create(movieDTO).map { _ =>
      Created(Json.obj("status" -> "success"))
    }.recover {
      case e: Throwable =>
        InternalServerError(Json.obj("status" -> "failed", "error" -> e.getMessage))
    }
  }

  def readMovie(id: Int): Action[AnyContent] = Action.async { _ =>
    movieRepo.read(id).map {
      case Some(movie) => Ok(Json.toJson(movie))
      case None => NotFound(Json.obj("status" -> "failed", "error" -> "Not Found"))
    }
  }

  def updateMovie(id: Int): Action[MovieDTO] = Action.async(parse.json[MovieDTO]) { request =>
    val movieDTO = request.body
    movieRepo.update(id, movieDTO).map {
      case 0 => NotFound(Json.obj("status" -> "failed", "error" -> "Not Found"))
      case _ => Ok(Json.obj("status" -> "success"))
    }.recover {
      case e: Throwable =>
        InternalServerError(Json.obj("status" -> "failed", "error" -> e.getMessage))
    }
  }

  def deleteMovie(id: Int): Action[AnyContent] = Action.async { _ =>
    movieRepo.delete(id).map {
      case 0 => NotFound(Json.obj("status" -> "failed", "error" -> "Not Found"))
      case _ => Ok(Json.obj("status" -> "success"))
    }.recover {
      case e: Throwable =>
        InternalServerError(Json.obj("status" -> "failed", "error" -> e.getMessage))
    }
  }
}
