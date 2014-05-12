package moneyExample

object Currency {
  
  def dollar (bill: Double): Currency =
    new Dollar (bill)

  def coin (coinValue: Double): Currency =
    new Coin (coinValue)
  
}