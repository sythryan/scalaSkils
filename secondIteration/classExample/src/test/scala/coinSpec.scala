package moneyExample
import org.specs2.mutable.Specification
import org.specs2.specification.{Scope, After, BeforeAfterEach}

object coinSpec extends Specification {
  val penny = Currency.coin(0.01)
  val nickel = Currency.coin(0.05)
  val dime = Currency.coin(0.10)
  val quarter = Currency.coin(0.25)
  val halfDollar = Currency.coin(0.50)
  val unknown = Currency.coin(0.34)

  "when we set coin to a value it" should {
    "be equal to the set value" in {
      penny.value === 0.01
      nickel.value === 0.05
      dime.value === 0.10
      quarter.value === 0.25
      halfDollar.value === 0.50
      unknown.value === 0.34
    }
  }

  "when we ask for the name of a coin it" should {
    "provide us with the correct name" in {
      penny.name === "Penny"
      nickel.name === "Nickel"
      dime.name === "Dime"
      quarter.name === "Quarter"
      halfDollar.name === "Half Dollar"
      unknown.name === "Unknown"
    }
  }
}