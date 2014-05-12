package moneyExample

object main {
  def main(args: Array[String]): Unit = {
    val myCoin = Currency.coin(0.25)
    println ("Name:   " + myCoin.name)
    println ("Value:  " + myCoin.value)
    val myBill = Currency.dollar (20)
    println ("Value:  " + myBill.value)
    println (myBill.value + " breaks into " + myBill.break(5) + " fives")
    println (myBill.value + " breaks into " + myBill.break(1) + " ones")
    println (myBill.value + " breaks into " + myBill.break(10) + " tens")
    println (myBill.value + " breaks into " + myBill.break(20) + " twenties")
    println (myBill.value + " breaks into " + myBill.break(50) + " fifties")
  }
}