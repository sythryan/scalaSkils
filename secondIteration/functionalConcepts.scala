object FunctionalConcepts {

  def add(x:Int)(y:Int) = x + y

  def increment (x: Int): Int = add(x)(1)
  
  def decrement (x: Int): Int = add(x)(-1)

  def mapOverList(ints: List[Int], f: Int => Int): List[Int] = {
    ints match {
      case Nil => ints
      case head :: tail => f(head) :: mapOverList(tail, f)
    }
  }
}