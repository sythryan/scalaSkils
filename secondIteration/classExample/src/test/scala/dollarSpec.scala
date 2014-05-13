package moneyExample
import org.specs2.mutable.Specification
import org.specs2.specification.{Scope, After, BeforeAfterEach}

object dollarSpec extends Specification {

  val oneDollar = Currency.dollar(1)
  val twoDollar = Currency.dollar(2)
  val fiveDollar = Currency.dollar(5)
  val tenDollar = Currency.dollar(10)
  val twentyDollar = Currency.dollar(20)
  val fiftyDollar = Currency.dollar(50)
  val oneHundredDollar = Currency.dollar(100)

  "when we set dollar to a value it" should {
    "be equal to the set value" in {
     oneDollar.value === 1
     twoDollar.value === 2
     fiveDollar.value === 5
     tenDollar.value === 10
     twentyDollar.value === 20
     fiftyDollar.value === 50
     oneHundredDollar.value === 100
    }
  }

  "when we break our dollar into another dollar it" should {
    "equal the new bill times the quantity" in {

     oneDollar.break(1) === 1
     oneDollar.break(2) === 0
     oneDollar.break(5) === 0
     oneDollar.break(10) === 0
     oneDollar.break(20) === 0
     oneDollar.break(50) === 0
     oneDollar.break(100) === 0

     fiveDollar.break(1) === 5
     fiveDollar.break(5) === 1
     fiveDollar.break(10) === 0
     fiveDollar.break(20) === 0
     fiveDollar.break(50) === 0
     fiveDollar.break(100) === 0

     tenDollar.break(1) === 10
     tenDollar.break(5) === 2
     tenDollar.break(10) === 1
     tenDollar.break(20) === 0
     tenDollar.break(50) === 0
     tenDollar.break(100) === 0

     twentyDollar.break(1) === 20
     twentyDollar.break(5) === 4
     twentyDollar.break(10) === 2
     twentyDollar.break(20) === 1
     twentyDollar.break(50) === 0
     twentyDollar.break(100) === 0

     fiftyDollar.break(1) === 50
     fiftyDollar.break(5) === 10
     fiftyDollar.break(10) === 5
     fiftyDollar.break(20) === 2
     fiftyDollar.break(50) === 1
     fiftyDollar.break(100) === 0

     oneHundredDollar.break(1) === 100
     oneHundredDollar.break(5) === 20
     oneHundredDollar.break(10) === 10
     oneHundredDollar.break(20) === 5
     oneHundredDollar.break(50) === 2
     oneHundredDollar.break(100) === 1

    }
  }

}