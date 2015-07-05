package com.example

import akka.actor.Actor
import spray.routing._
import spray.http._
import com.mongodb.casbah.Imports._
import com.mongodb.util.JSON
import spray.json._
import DefaultJsonProtocol._ // if you don't supply your own Protocol (see below)

class UserService extends Actor with HttpService {

  def actorRefFactory = context

  def receive = runRoute(myRoute)

  val mongoUsername = "app"
  //TODO: env variales
  val mongoPassword = ""
  val mongoServer = "oceanic.mongohq.com:10092"
  val mongoDbName=""

  lazy val myRoute =
    path("users") {
      get {
        complete {
          val con = MongoClient(MongoClientURI(s"mongodb://${mongoUsername}:${mongoPassword}@${mongoServer}/${mongoDbName}"))
          val db = con(mongoDbName)
          val col = db("organizations")
          val orgs = col.find.toList
          JSON.serialize(orgs)
        }
      }
    }
}