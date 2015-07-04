package com.example

import akka.actor.Actor
import spray.routing._
import spray.http._
import MediaTypes._

import PersonJsonProtocol._
import spray.json._
import spray.httpx.SprayJsonSupport._

class PersonService extends Actor with HttpService {

  def actorRefFactory = context

  def receive = runRoute(myRoute)

  lazy val myRoute =
    path("persons") {
      get {
        complete {
          List(
            Person("Duncan", "Finney", Some(22)),
            Person("Rhiannon", "Finney", Some(21)),
            Person("Gwyneth", "Finney", Some(18)),
            Person("Barbara", "Finney", Some(50)),
            Person("Angus", "Finney", Some(53))
          )
        }
      }
    } ~
    post {
      entity(as[Person]) { person =>
        complete {
          s"Hello ${person.firstName} ${person.lastName}"
        }
      }
    } ~
    path("persons" / Segment) { personId =>
      get {
        complete {
          s"You requested person ${personId}"
        }
      }
    }
}