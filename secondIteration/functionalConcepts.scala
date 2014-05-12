object FunctionalConcepts {

  def add(x:Int)(y:Int) = x + y

  def increment (x: Int): Int = add(x)(1)

  def decrement (x: Int): Int = add(x)(-1)

  def mapOverList(ints: List[Int], f: Int => Int): List[Int] = {
    def innerMapOverList(ints: List[Int], f: Int => Int): List[Int] = {
      ints match {
        case Nil => ints
        case head :: tail => f(head) :: innerMapOverList(tail, f)
      }
    }
    innerMapOverList (ints, f)
  }

  def main(args: Array[String]): Unit = {
    val myList = List (1, 2, 3, 5, 3, 7)
    val incrementList = mapOverList(myList, increment)
    println ("Increment list: " + incrementList)
    val decrementList = mapOverList(myList, decrement)
    println ("Decrement list " + decrementList)
  }
}