package moneyExample

class Coin (coinValue : Double) extends Currency {
  def value : Double = coinValue
  def name : String = coinValue match {
    case 0.50 => "Half Dollar"
    case 0.25 => "Quater"
    case 0.10 => "Dime"
    case 0.05 => "Nickel"
    case 0.01 => "Penny"
    case _    => "Unknown" 
  }
}