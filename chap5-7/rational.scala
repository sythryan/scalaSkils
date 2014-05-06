class Rational (n: Int, d: Int) {
  private val g = gcd(n.abs, d.abs)
  val numer :Int = n / g
  val denom :Int = d / g
  def this(n: Int) = this (n, 1)
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
  private def gcd(a: Int, b: Int): Int = {
    if (b == 0) a else gcd(b, a % b)
  }
}