package main.scala


import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.protocol._
import org.apache.commons.io._
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import scala.xml.{XML, Elem, Node}

object retrieve {

	case class Article (
    val id: String,
    val title: String,
    val author: String,
    val published: String,
    val updated: String,
    val theAbstract: String)

	private [this] def parseArticles(feed: Elem): List[Article] = {
		def parseASingleArticle(parent: Node) = {
			for {
				published <- (parent \ "published")
				updated <- (parent \ "updated")
				title <- (parent \ "title")
				id <- (parent \ "id")
				author <- (parent \ "author" \ "name")
			} yield {
				Article(id.text, title.text, author.text, published.text, updated.text, "not implemented")
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