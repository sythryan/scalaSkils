package moneyExample
import org.specs2.mutable.Specification
import org.specs2.specification.{Scope, After, BeforeAfterEach}

object currencySpec extends Specification {
  "when we call the factory method for dollar we" should {
    "create an instance of dollar" in {
     val myDollar: Option[Dollar] = Some(Currency.dollar(5))
     myDollar must beSome[Dollar]
    }
  }
  "when we call the factory method for coin we" should {
    "create an instance of coin" in {
      val myCoin: Option[Coin] = Some(Currency.coin(5))
      myCoin must beSome[Coin]
    }
  }
}