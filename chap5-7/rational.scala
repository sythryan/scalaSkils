class Rational (n: Int, d: Int) {
  def this(n: Int) = this (n, 1)

  require (d != 0)
  private val g: Int = gcd(n.abs, d.abs)
  override def toString = numer + "/" + denom

  val numer :Int = n / g
  val denom :Int = d / g

  def + (that: Rational): Rational =
    new Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom
    )

  def + (i: Int): Rational =
    new Rational(numer + i * denom, denom)

  def - (that: Rational): Rational =
    new Rational(
      numer * that.denom - that.numer * denom,
      denom * that.denom
    )

  def - (i: Int): Rational =
    new Rational(numer - i * denom, denom)

  def * (that: Rational): Rational =
    new Rational(numer * that.numer, denom * that.denom)

  def * (i: Int): Rational =
    new Rational(numer * i, denom)

  def / (that: Rational): Rational =
    new Rational(numer * that.denom, denom * that.numer)

  def / (i: Int): Rational =
    new Rational(numer, denom * i)

  def lessThan(that: Rational): Boolean =
    numer * that.denom < that.numer * denom

  def max(that: Rational): Rational = 
    if (this.lessThan(that)) that else this

  private def gcd(a: Int, b: Int): Int = 
    if (b == 0) a else gcd(b, a % b)
}