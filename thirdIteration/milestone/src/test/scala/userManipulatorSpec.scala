package milestone

import org.specs2.mutable.Specification
import org.specs2.specification.{Scope, After, BeforeAfterEach}

object UserManipulatorSpec extends Specification with UserManipulator {

  val userOne = User("1", "Joe", 55.34, List(Transaction("tran1", -23.43), Transaction("tran2", 43.00)))
  val userTwo = User("2", "Haley", 2055.38, List(Transaction("tran1", 205.01)))
  val userThree = User("3", "Mildred", 403.67, List())

  create(userOne)
  create(userTwo)
  create(userThree)

  "find user one" should {
    "return userOne" in {
       userOne === findUser("1")
    }
  }

  "find user two" should {
    "return userTwo" in {
       userTwo === findUser("2")
    }
  }

  "find user three" should {
    "return userThree" in {
       userThree === findUser("3")
    }
  }

  "finding a user that does not exist" should {
    "return an empty user" in {
      nonExistantUser === findUser("fake")
    }
  }

  "find users with balances above $100.00" should {
    "return userTwo and userThree" in {
      List(userTwo, userThree) === findAllUsersAbove(100.0)
    }
  }

  "find users with balances above $4,000.00" should {
    "return an empty list" in {
      List() === findAllUsersAbove(4000.0)
    }
  }

  "bank value" should {
    "return $2514.39" in {
      2514.39 === bankValue
    }
  }

  "adding transactions to a user" should {
    "return a user with transactions containing the previous and new ones" in {
      addTransactions("1", List (Transaction("tran3", 5.0))) === 
        User("1", "Joe", 55.34, List(Transaction("tran1", -23.43), Transaction("tran2", 43.00), Transaction("tran3", 5.0)))
    }
  }

  "adding transactions to a user with no transactions" should {
    "return a user with only the transactions added" in {
      addTransactions("3", List(Transaction("tran1", -23.43), Transaction("tran2", 43.00))) ===
        User("3", "Mildred", 403.67, List(Transaction("tran1", -23.43), Transaction("tran2", 43.00)))
    }
  } 
}