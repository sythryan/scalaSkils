package main.scala


import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.protocol._
import org.apache.commons.io._
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import scala.xml.{XML, Elem, Node}
import spray.json._
import DefaultJsonProtocol._ 

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

	private[this] def createAbstract(original: String): String = {
		if (original.length > 20) original.slice(0, 20) else original
	}

	private[this] def parseASingleArticle(parent: Node) =
		for {
			published <- (parent \ "published")
			updated <- (parent \ "updated")
			title <- (parent \ "title")
			id <- (parent \ "id")
			author <- (parent \ "author" \ "name")
		} yield {
			Article(id.text, title.text, author.text, published.text, updated.text, createAbstract(title.text) + "...").toJson
		} 
	

	private[this] def parseArticles(feed: Elem) = {
		(feed \\ "entry").toList.flatMap(parent => parseASingleArticle(parent))
	}

	//needs generalization
	def createResponse = {
		val httpclient: HttpClient   = new DefaultHttpClient();
    val context:    HttpContext  = new BasicHttpContext
    			
		val httpGetOne = new HttpGet("http://www.theverge.com/rss/frontpage.xml")
		XML.loadString(IOUtils.toString((httpclient.execute(httpGetOne, context).getEntity.getContent)))
	}

	def loadparsedXML = {
		parseArticles(createResponse)
	} 

	def searchTitles(searchString: String) = {
	  var count = 0
	  (createResponse \\ "entry").foreach { parent =>
	  	if ((parent \ "title").text.toLowerCase().contains(searchString.toLowerCase())){
				parseASingleArticle(parent)
	  	  count += 1
	  	}
	  }
	  if (count < 1){
	  	"no matching searches"
	  }
	}
}