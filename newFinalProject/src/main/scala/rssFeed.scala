package main.scala


import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.protocol._
import org.apache.commons.io._
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import scala.xml.XML


object retrieve {

	private [this] def parseArticles(document: String, feed: Elem): List[Article] = {

		(feed \\ "entry").foreach {parent =>
			for {
				published <- (parent \ "published")
				updated <- (parent \ "updated")
				title <- (parent \ "title")
				id <- (parent \ "id")
				author <- (parent \ "author" \ "name")
			} yield {
				Article(id, title, author, published, updated, "not implemented")
			}
		}
	}

	def loadNewXML = {
		val httpclient: HttpClient   = new DefaultHttpClient();
    val context:    HttpContext  = new BasicHttpContext
    			
		val httpGetOne = new HttpGet("http://www.theverge.com/rss/frontpage.xml")
		val response = XML.loadString(IOUtils.toString((httpclient.execute(httpGetOne, context).getEntity.getContent)))

	} 
}