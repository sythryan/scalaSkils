package moneyExample

abstract class Currency {
  def value : Double
}

object Currency {

  def dollar (bill: Double): Currency =
    new Dollar (bill)

  def coin (coinValue: Double): Currency =
    new Coin (coinValue)
  
}
