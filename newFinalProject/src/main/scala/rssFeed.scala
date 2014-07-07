package main.scala


import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.protocol._
import org.apache.commons.io._
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import scala.xml.{XML, Elem, Node}
import spray.json._
import DefaultJsonProtocol._ 

// implicit object ArticleFormat extends Format[TypeOfAccount] with FormMapping[TypeOfAccount] {
//     def writes(value: TypeOfAccount) = JsObject(Seq(
//       "totalAccess" -> toJson(value.totalAccess),
//       "freeAccess" -> toJson(value.freeAccess),
//       "completeAccess" -> toJson(value.completeAccess),
//       "moneyMarket" -> toJson(value.moneyMarket),
//       "accesspartner" -> toJson(value.accesspartner),
//       "savings" -> toJson(value.savings)))

//     def reads(json: JsValue) = tryJsonRead(TypeOfAccount(
//       totalAccess = (json \ "totalAccess").as[Boolean],
//       freeAccess = (json \ "freeAccess").as[Boolean],
//       completeAccess = (json \ "completeAccess").as[Boolean],
//       moneyMarket = (json \ "moneyMarket").as[Boolean],
//       accesspartner = (json \ "accesspartner").as[Boolean],
//       savings = (json \ "savings").as[Boolean]))

//     def formMapping = mapping(
//       "totalAccess" -> boolean,
//       "freeAccess" -> boolean,
//       "completeAccess" -> boolean,
//       "moneyMarket" -> boolean,
//       "accesspartner" -> boolean,
//       "savings" -> boolean)(TypeOfAccount.apply)(TypeOfAccount.unapply)
//   }

case class Article (
  val id: String,
  val title: String,
  val author: String,
  val published: String,
  val updated: String,
  val theAbstract: String)

object MyJsonProtocol extends DefaultJsonProtocol {
	implicit val ArticleFormat = jsonFormat(Article, "id", "title", "author", "published", "updated", "theAbstract")
}

object retrieve {

	import MyJsonProtocol._

	private[this] def parseArticles(feed: Elem) = {
		def parseASingleArticle(parent: Node) = {
			for {
				published <- (parent \ "published")
				updated <- (parent \ "updated")
				title <- (parent \ "title")
				id <- (parent \ "id")
				author <- (parent \ "author" \ "name")
			} yield {
				Article(id.text, title.text, author.text, published.text, updated.text, "not implemented").toJson
			} 
		}	
			(feed \\ "entry").toList.flatMap(parent => parseASingleArticle(parent))
	}

	def loadNewXML = {
		val httpclient: HttpClient   = new DefaultHttpClient();
    val context:    HttpContext  = new BasicHttpContext
    			
		val httpGetOne = new HttpGet("http://www.theverge.com/rss/frontpage.xml")
		val response = XML.loadString(IOUtils.toString((httpclient.execute(httpGetOne, context).getEntity.getContent)))
		parseArticles(response)
	} 
}