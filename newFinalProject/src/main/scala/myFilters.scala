package main.scala

import scala.io.Source
import scala.xml.Elem


object myFilters {
  
  def searchTitle (feed: Elem, searchWord: String) {
	  var count = 0
	  (feed \\ "item").foreach { parent =>
	  	if ((parent \ "title").text.toLowerCase().contains(searchWord.toLowerCase())){
	  	  println((parent \ "title" ).text)
	  	  println((parent \ "link").text)
	  	  println((parent \ "pubDate").text + "\n")
	  	  count += 1
	  	}
	  }
	  if (count < 1){
	  	println("no matching searches")
	  }
  }
  
  def searchDate (feed: Elem, searchWord: String) {
    var count = 0
    (feed \\ "item").foreach { parent => 
      if ((parent \ "pubDate").text.toLowerCase().contains(searchWord.toLowerCase())) {
	  	  println((parent \ "title" ).text)
	  	  println((parent \ "link").text)
	  	  println((parent \ "pubDate").text + "\n")
	  	  count += 1
	  	}
	  }
	  if (count < 1){
	  	println("no matching searches")
	  }        
  }
  
  def printAll (myFile: String) {
      println("The file contains:" )
      Source.fromFile(myFile).foreach{ 
         print
      }
  }
}