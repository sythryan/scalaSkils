object ForComp {

  def main(args: Array[String]): Unit = {
    val myStrings = List ("1", "22", "333", null, "");
    val lengthList = sizeOfStrings (myStrings)
    lengthList.map (println(_))
  }

  def sizeOfStrings(myList: List [String]): List [Int] = {
    for (word <- myList) 
      yield  word match {
        case ""   => -1
        case null => 0
        case _    => word.length
      }
  }
}