package patMatch

object main {

  def interpreter(x: Values) = x match {
    case x: Numbers => 
      print ("The value was Numbers and ")
      x.myInt match {
        case a if a < 10 => println (x.myInt + " is less than ten.")
        case _           => println (x.myInt + " is greater than ten.")
      }
    case x: Strings => println ("The value was Strings: " + x.myString)
    case x: UnaryFunction => println ("The value was UnaryFunction: " + x.myFunction)
  }

  def main(args: Array[String]): Unit = {
    val num = new Numbers(3)
    val str = new Strings("example")
    val func = new UnaryFunction (x => x)
    interpreter(num)
    interpreter(str)
    interpreter(func)
  }
}

