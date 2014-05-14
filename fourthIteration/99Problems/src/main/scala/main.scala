object main {

  def last[A](theList: List[A]): A = {
    theList.reverse.head // or theList.last
  }



  def main(args: Array[String]): Unit = {
    val testList = List (1, 1, 2, 3, 5, 8)
    println(last(testList))
    
  }  
}