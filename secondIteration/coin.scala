package moneyExample

class Coin (coinValue : Double) extends Currency {
  def value : Double = coinValue
  def amountInDollar : Int  // returns how many coins it takes to make a dollar, example: quater would return 4
}