package moneyExample

class Dollar (bill: Double) extends Currency {
  def value : Double = bill
  def Break (breakIntoBills : Int): Int = {
    if (breakIntoBills > bill.toInt) {
      0
    } else {
      bill.toInt / breakIntoBills
    }
  }
}