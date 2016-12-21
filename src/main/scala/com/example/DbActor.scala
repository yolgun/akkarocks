package com.example

import akka.actor.{Actor, ActorLogging}

import scala.collection.mutable

/**
  * Created by yunus on 21.12.16.
  */
class DbActor extends Actor with ActorLogging {
  val map = new mutable.HashMap[String, Object]
  override def receive = {
    case UpsertRequest(key, value) => {
      log.info("Received {} with {}", key, value)
      map.put(key, value)
    }
    case o => {
      log.info("Received unknown: {}", o)
    }
  }
}

case class UpsertRequest(key: String, value: String)