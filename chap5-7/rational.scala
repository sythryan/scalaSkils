class Rational (n: Int, d: Int) {
  val numer :Int = n
  val denom :Int = d
  require (d != 0)
  override def toString = numer + "/" + denom
  def add(that: Rational): Rational = {
    new Rational (
      numer * that.denom + denom * that.numer,
      denom * that.denom
    )
  }
  def lessThan(that: Rational): Boolean = {
    numer * that.denom < that.numer * denom
  }
  def max(that: Rational): Rational = {
    if (this.lessThan(that)) that else this
  }
}