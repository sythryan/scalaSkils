package main.scala

object choices {
  
  import myFilters._
  import scala.xml.Elem
  
	def choiceInput (choice: Int, myFile: String, feed: Elem) = choice match {
    	case 0 => null        														// exiting program
    	case 1 =>  myFilters.printAll(myFile)										// print all info
    	case 2 => { 																// print info of titles searched
    		print("Enter a word or series of words to search: ")
    		val searchWord = Console.readLine
    		searchTitle(feed, searchWord)  
    	}
    	case 3 => {																	// print info of dates searched
    		print("Enter a day of week or month in this format: Mon, Tues ... or Oct, Feb ... : ")
    		val searchWord1 = Console.readLine
    		searchDate(feed, searchWord1)
    	}
    	case _ => println("Not a valid input, please reread the choices: ")			// invalid input
		  
	}
}