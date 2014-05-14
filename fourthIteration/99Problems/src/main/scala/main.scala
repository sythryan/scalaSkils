object main {

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

  def compress[A](theList: List[A]): List[A] = theList match {
    case a :: b :: tail if (a == b) => List(a) ::: compress(tail)
    case a :: b :: Nil  if (a == b) => List(a)
    case a :: Nil => List(a)
  }



  def main(args: Array[String]): Unit = {
    val testList = List (1, 1, 2, 3, 5, 8)
    val palindromeList = List (1, 2, 3, 2, 1)
    val nestedList = List (List(1, 1), 2, List(3, List(5, 8)))
    val symbolList = List ('a, 'a, 'a, 'a, 'b, 'c, 'c,'a, 'a, 'd, 'e, 'e, 'e, 'e)
    println("Last result       : " + last(testList))
    println("penultimate result: " + penultimate(testList))
    println("kth element result: " + nth(2, testList))
    println("Length result     : " + length(testList))
    println("reverse result    : " + reverse(testList))
    println("not palindrome    : " + isPalindrome(testList))
    println("is a palindrome   : " + isPalindrome(palindromeList))
    println("flatten  result   : " + flatten(nestedList))
    println("compress result   : " + compress(symbolList))
  }  
}