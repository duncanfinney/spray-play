package com.example

import org.specs2.mutable.Specification
import spray.testkit.Specs2RouteTest
import spray.http._
import StatusCodes._

class MyServiceSpec extends Specification with Specs2RouteTest with MyService {
  def actorRefFactory = system
  
  "MyService" should {

    "say hello when you GET /hello" in {
      Get("/hellos") ~> myRoute ~> check {
        responseAs[String].toLowerCase must contain ("hello")
      }
    }
  }
}
