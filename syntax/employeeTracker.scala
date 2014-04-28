import util.Random

// does not handle incorrect input

object employeeTracker {

  case class Employee (name: String, monthsEmployeed: Int) {
    def employeeAge () = Random.nextInt (90)
  }

  def main(args: Array[String]): Unit = {
    val employeeList = List (Employee("Jeff", 24), 
                             Employee("Jon", 12), 
                             Employee ("Samantha", 28), 
                             Employee ("Emily", 6), 
                             Employee ("Mildred", 3))    
    var answer = "y"
    println("would you like to see the employee list? (y/n)")
    answer = readLine()

    while (answer != "n") {
      println()
      println("------------------------Employees-----------------------------")
      employeeList.map { employee =>
        print(employee.name + " has worked here for " + employee.monthsEmployeed + " months and is ") 
        print(employee.employeeAge())
        println(" years old")
      }
      println("--------------------------------------------------------------")
      println()
      println("would you like to see the employee list again? (y/n)")
      answer = readLine()
    }
  }
} 