package com.example

import akka.actor.{ActorSystem, Props}

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object ApplicationMain extends App {
  val system = ActorSystem("MyActorSystem")
  val dbActor = system.actorOf(Props[DbActor], "DbActor")
  dbActor ! UpsertRequest("key", "value")
  dbActor ! AnyRef
  Await.result(system.whenTerminated, Duration.Inf)
}

