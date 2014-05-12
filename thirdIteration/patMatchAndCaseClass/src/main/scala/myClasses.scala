package patMatch

sealed trait Values
case class Numbers (myInt: Int) extends Values
case class Strings (myString: String) extends Values
case class UnaryFunction (myFunction: Any => Any) extends Values