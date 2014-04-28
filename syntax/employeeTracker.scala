import util.Random

object employeeTracker {

  case class Employee (name: String, monthsEmployeed: Int) {
    private val Age = Random.nextInt (90)
    def employeeAge () = Age
  }
  
  def main(args: Array[String]): Unit = {
    val employeeOne = new Employee ("Jeff", 5)
    println(employeeOne.name)
    println(employeeOne.monthsEmployeed)
    println(employeeOne.employeeAge())

  }
} 