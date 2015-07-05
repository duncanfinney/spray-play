package com.example

import spray.json.DefaultJsonProtocol

case class User(firstName: String, lastName: String, email: String);

object UserJsonProtocol extends DefaultJsonProtocol {
  implicit val userFormat = jsonFormat3(User)
}

