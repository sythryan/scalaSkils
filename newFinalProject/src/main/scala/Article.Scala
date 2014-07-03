package main.scala

abstract case class Article (
  val id: String,
  val title: String,
  val author: String,
  val published: String,
  val updated: String,
  val theAbstract: String)