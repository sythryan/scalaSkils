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
    def innerLength[A](theList: List[A], accumulator: Int): Int = theList match {
      case head :: tail => innerLength(tail, accumulator + 1)
      case Nil => accumulator
    }
    innerLength(theList, 0)
  }

  def reverse[A](theList: List[A]): List[A] = {
    def innerReverse[A](theList: List[A], accumulator: List[A]): List [A] = theList match {
      case head :: tail => innerReverse(tail, head :: accumulator)
      case Nil => accumulator
    }
    innerReverse(theList, List())
  }

  def isPalindrome[A](theList: List[A]): Boolean = {
    theList == reverse(theList)
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

  def encode[A](theList: List[A]): List[(Int, A)] = {
    def lengthList[A](packedList: List[List[A]], accumulator: List[Int]): List[Int] = packedList match {
      case head :: tail => lengthList(tail, accumulator :+ head.length)
      case Nil  => accumulator
    }
    def unPackSingleElems[A](packedList: List[List[A]], accumulator: List[A]): List[A] = packedList match {
      case head :: tail => unPackSingleElems(tail, accumulator :+ head.last) // just pulled out a non specific element from the list
      case Nil => accumulator
    }        
    lengthList(pack(theList), List()).zip(unPackSingleElems(pack(theList), List()))
  }

  def encodeModified[A](theList: List[A]): List[Any] = {
    def pullOutSingles[A,B](theList: List[(A,B)], accumulator: List[Any]): List[Any] = theList match {
      case head :: tail if (head._1 == 1) => pullOutSingles(tail, accumulator :+ head._2)
      case head :: tail => pullOutSingles(tail, accumulator :+ head)
      case Nil => accumulator
    }
    pullOutSingles(encode(theList), List())
  }

  def decode[A](theList: List[(Int,A)]): List[A] = {     // could use a flat map here
    def multiplyElement[A](elem : A, by: Int): List [A] ={
      var accumulator: List[A] = List()
      for (i <- 1 to by) {
        accumulator = accumulator ::: List(elem)
      }
      accumulator
    }
    def innerDecode[A](theList: List[(Int,A)], accumulator: List[A]): List[A] = theList match {
      case head :: tail => innerDecode(tail, accumulator ::: multiplyElement(head._2, head._1))
      case Nil => accumulator
    }
    innerDecode(theList,List())
  }
   
  def encodeDirect[A](ls: List[A]): List[(Int, A)] = {  // had to look at solution
    if (ls.isEmpty) Nil
    else {
      val (packed, next) = ls span { _ == ls.head }
      (packed.length, packed.head) :: encodeDirect(next)
    }
  }
        // in repel  ^^
        // val (packed, next) = ls span { _ == ls.head }
        // packed: List[Symbol] = List('a, 'a)
        // next: List[Symbol] = List('b, 'c, 'g)
        
  def duplicate[A](theList: List[A]): List[A] = {
    def innerDuplicate[A](theList: List[A], accumulator: List[A]): List[A] = theList match {
      case head :: tail => innerDuplicate(tail, accumulator :+ head :+ head)
      case Nil => accumulator
    }
    innerDuplicate(theList, List())
  }

  // flat map solution ^ def duplicate[A](ls: List[A]): List[A] = ls flatMap { e => List(e, e) }

  def duplicateN[A](n: Int, theList: List[A]): List[A] =
    theList flatMap {e => List.make(n,e)}

  def drop[A](n: Int, theList: List[A]): List[A] = { // had to look at solution
    theList.zipWithIndex filter { v => (v._2 + 1) % n != 0 } map { _._1 }
  }
    // zipWithIndex is creating a tuple, example (a, 0) (a, 1)

  def split[A](n: Int, theList: List[A]): (List[A], List[A]) = {
    def innerSplit[A](indexedList: List[(A, Int)], accumulatorOne: List[A], accumulatorTwo: List[A] ): (List[A], List[A]) = indexedList match {
      case head :: tail if (head._2 + 1 > n) => innerSplit(tail, accumulatorOne, accumulatorTwo :+ head._1)
      case head :: tail => innerSplit(tail, accumulatorOne :+ head._1, accumulatorTwo)
      case Nil => (accumulatorOne, accumulatorTwo)
    }
    innerSplit(theList.zipWithIndex, List(), List())
  }

  // could simply use theList.splitAt(n)

  def slice[A](i: Int, k: Int, theList: List[A]): List[A] = {
    theList.dropRight(theList.length - k).drop(i)
  }

  def rotate[A](n: Int, theList: List[A]): List[A] = {
    if (n > 0) {
      val movePart = slice(0, n, theList) 
      theList.drop(n) ::: movePart
    } else if (n == 0) {
      theList
    } else {
      val movePart = slice(theList.length + n, theList.length, theList)
      movePart ::: theList.dropRight(-1 * n)
    }
  }

  def removeAt[A](k: Int, theList: List[A]): (List[A],A) = {
    (theList.dropRight(theList.length-k) ::: theList.drop(k + 1), theList(k))
  }

  def insertAt[A](elem: A, n: Int, theList: List[A]): List[A] = {
    slice(0, n, theList) ::: List(elem) ::: slice(n, theList.length, theList)
  }

  def randomSelect[A](n: Int, theList: List[A]): List[A] = {
    import scala.util.Random
    def innerRandomSelect(iterations: Int, accumulator: List[A], remainingList: List[A]): List[A] = iterations match {
      case i if (i > n) => accumulator
      case _ => 
        val removeIndex = Random.nextInt(remainingList.length)
        innerRandomSelect(iterations + 1, accumulator :+ remainingList(removeIndex), removeAt(removeIndex, remainingList)._1)
    }
    innerRandomSelect(1, List(), theList)
  }

  def lotto(n: Int, k: Int): List[Int] = {
    import scala.util.Random
    def innerLotto(iterations: Int, accumulator: List[Int]): List[Int] = iterations match {
      case i if (i > n) => accumulator
      case _ => innerLotto(iterations + 1, accumulator :+ Random.nextInt(k))
    }
    innerLotto(1, List())
  }

  def randomPermute[A](theList: List[A]): List[A] = {
    println(theList)
    println(randomSelect(theList.length, theList))
    randomSelect(theList.length, theList)   
  }

  def combinations[A](n: Int, ls: List[A]): List[List[A]] = {
    def flatMapSublists[A,B](ls: List[A])(f: (List[A]) => List[B]): List[B] = ls match {
      case Nil => Nil
      case sublist@(_ :: tail) => f(sublist) ::: flatMapSublists(tail)(f)
    }

    flatMapSublists(ls) { sl =>
      combinations(n - 1, sl.tail) map {sl.head :: _}
    }
  }
}