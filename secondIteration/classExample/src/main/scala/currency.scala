package moneyExample

abstract class Currency {
  def value : Double
}

object Currency {

  def dollar (bill: Double): Dollar =
    new Dollar (bill)

  def coin (coinValue: Double): Coin =
    new Coin (coinValue)
  
}