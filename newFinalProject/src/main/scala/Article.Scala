package main.scala


// for some reason the main file is not seeing this.
object ArticleObject {
  abstract case class Article (
    val id: String,
    val title: String,
    val author: String,
    val published: String,
    val updated: String,
    val theAbstract: String)
}
