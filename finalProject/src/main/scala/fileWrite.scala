package main.scala

import scala.xml._
import java.io._

object fileWrite {
  
	def write (fileName: String, feed: Elem){ 
	  
		if (new File(fileName).exists()) {
		   new File(fileName).delete()  
		}
		
		val writter = new FileWriter(new File(fileName), true)
		
		(feed \\ "item").foreach {parent =>
			writter.write((parent \ "title" ).text + "\n")
			writter.write((parent \ "link").text + "\n")
			writter.write((parent \ "pubDate").text + "\n" + "\n")
		}
	    writter.close
	}
}
