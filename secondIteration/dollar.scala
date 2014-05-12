class Dollar (Bill: Double) extends currency {
  def value : Double = Bill
  def Break (breakIntoBills : Int): Int  // returns, for example if bill was 5 and Break (1) sould return 5
}