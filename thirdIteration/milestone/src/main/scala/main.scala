package milestone

import scala.util.Random

object main extends MemoryBasedUserRepository with UserManipulator {

  def randomTransactionList: List[Transaction] = {
    var Transactions: List[Transaction] = List()
    for (i <- 1 to Random.nextInt(5)) {
      Transactions = Transactions :+ Transaction (Random.nextString(10), Random.nextDouble * (Random.nextInt(401) - 200))
    }
    Transactions
  }

  // users are always generated with a positive balance
  def randomUser: User = 
    User(Random.nextString(100), Random.nextString(8) + " " + Random.nextString(8), Random.nextDouble * Random.nextInt(100), randomTransactionList, 0, List())

  def main(args: Array[String]): Unit = {

    for (i <- 1 to 100) {
      create(randomUser)
    }

    for (i <- 1 to 1000) {
      val chosenUser = getAll(Random.nextInt(getAll.length))
      addTransactions(chosenUser.id, randomTransactionList)
    }

    println()
    println("----------- User Balances ------------")
    val theUser = getAll
    for (i <- 0 to 99) {
      println(theUser(i).name)
      println("Current Balance : $" + BigDecimal(theUser(i).balance).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble.toString)
      println("Over drafts     : " + theUser(i).overDrafts)
      println("     -- Balance History -- ")
      for (y <- 0 to theUser(i).balanceHistory.length - 1) {
        println("     $" + BigDecimal(theUser(i).balanceHistory(y)).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble.toString)
      }
      println("--------------------------------------------------")
    }
  } 
}