package moneyExample

class Dollar (bill: Double) extends Currency {
  def value : Double = bill
  def break (breakIntoBills : Int): Int = { // does not handle un even breaks, example: 50 / 20
    if (breakIntoBills > bill.toInt) {
      0
    } else {
      bill.toInt / breakIntoBills
    }
  }
}