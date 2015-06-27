package com.example

import spray.json.DefaultJsonProtocol

case class Person(firstName: String, lastName: String, age: Option[Int] = None)

object PersonJsonProtocol extends DefaultJsonProtocol {
  implicit val personFormat = jsonFormat3(Person)
}

