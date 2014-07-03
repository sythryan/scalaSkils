package main.scala

import org.apache.http.impl.client.DefaultHttpClient
import java.net.{URLConnection, URL}
import java.util.ArrayList
import java.util.Scanner
import org.apache.http.protocol._
import org.apache.commons.io._
import org.apache.http.message.BasicNameValuePair
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.HttpEntity
import org.apache.http.util.EntityUtils
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.{HttpGet, HttpPost}
import java.io.{BufferedReader, InputStreamReader, InputStream}
import scala.xml._
import java.io._
import scala.Console


object retrieve {

	def main(args: Array[String]): Unit = {
		val httpclient: HttpClient   = new DefaultHttpClient();
    val context:    HttpContext  = new BasicHttpContext
    			
		val httpGetOne = new HttpGet("http://www.theverge.com/rss/frontpage.xml")
		val response =  XML.loadString(IOUtils.toString((httpclient.execute(httpGetOne, context).getEntity.getContent)))

		println(response)	
	} 
}