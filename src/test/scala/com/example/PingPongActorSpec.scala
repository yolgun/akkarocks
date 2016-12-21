package com.example

import akka.actor.ActorSystem
import akka.testkit.{ImplicitSender, TestActorRef, TestKit}
import org.scalatest.WordSpecLike
import org.scalatest.Matchers
import org.scalatest.BeforeAndAfterAll
 

class DbActorSpec(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "Db actor" must {
    "save given key value" in {
      val dbActorRef = TestActorRef(new DbActor)
      dbActorRef ! UpsertRequest("key", "value")
      val dbActor = dbActorRef.underlyingActor
      dbActor.map.get("key") should equal(Some("value"))
    }
  }
}
