package nineNineProblems

trait problems {

  def last[A](theList: List[A]): A = {
    theList.reverse.head // or theList.last
  }

  def penultimate[A](theList: List[A]): A = theList match {
    case a :: _ :: Nil => a
    case head :: tail  => penultimate(tail)
    case _ => throw new NoSuchElementException
  }

  def nth[A](n: Int, theList: List[A]): A = {
    theList(n)
  }

  def length[A](theList: List[A]): Int = {
    theList.length
  }

  def reverse[A](theList: List[A]): List[A] = {
    theList.reverse
  }

  def isPalindrome[A](theList: List[A]): Boolean = {
    theList == theList.reverse
  }

  def flatten[A](theList: List[A]): List[Any] = theList flatMap {  //had to look at answer
    case a: List[_] => flatten(a)
    case a => List(a) 
  }

  def compress[A](theList: List[A]): List[A] = {   // had to look at answer, put personal 'spin' on tail recursion version
    def innerCompress[A](theList: List[A], accumulator: List[A]): List[A] = theList match {
      case head :: tail  => innerCompress(tail.dropWhile(_ == head), accumulator :+ head)
      case Nil => accumulator
    }
    innerCompress(theList, List())
  }

  def pack[A](theList: List[A]): List[List[A]] = {
    def innerPack(theList: List[A], accumulator: List[List[A]]): List[List[A]] = theList match {
      case head :: tail => innerPack(tail.dropWhile(_ == head), accumulator :+ theList.takeWhile(_ == head).toList)
      case Nil => accumulator 
    }
    innerPack(theList, List()) 
  }

  def encode[A](theList: List[A]) = {
    def lengthList[A](packedList: List[List[A]], accumulator: List[Int]): List[Int] = packedList match {
      case head :: tail => lengthList(tail, accumulator :+ head.length)
      case Nil  => accumulator
    }
    def unPackSingleElem[A](packedList: List[List[A]], accumulator: List[A]): List[A] = packedList match {
      case head :: tail if (head.length > 0) => unPackSingleElem(tail, accumulator :+ head.last) // just pulled out an element from the list
      case Nil => accumulator
    }        
    lengthList(pack(theList), List()).zip(unPackSingleElem(pack(theList), List()))
  }

  
}