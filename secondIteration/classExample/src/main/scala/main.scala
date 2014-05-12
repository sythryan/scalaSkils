package moneyExample

object main {
  def main(args: Array[String]): Unit = {
    val factory = Currency
    val myCoin = factory.coin(0.25)
    // println ("Name:   " + myCoin.name)
    println ("Value:  " + myCoin.value)
    val myBill = factory.dollar (20)
    println ("Value:  " + myBill.value)
  }
}