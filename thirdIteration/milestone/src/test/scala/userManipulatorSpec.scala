package milestone

import org.specs2.mutable.Specification
import org.specs2.specification.{Scope, After, BeforeAfterEach}

object UserManipulatorSpec extends Specification with UserManipulator {

  val userOne = User("1", "Joe", 55.34, List(Transaction("tran1", -23.43), Transaction("tran2", 43.00)))
  val userTwo = User("2", "Haley", 2055.38, List(Transaction("tran1", 205.01)))
  val userThree = User("3", "Mildred", 403.67, List())

  trait MockRepository extends UserRepository {
    var userStorage = scala.collection.mutable.Map[String,User]((userOne.id, userOne), (userTwo.id, userTwo), (userThree.id, userThree))
    def getAll: List[User] = {
      userStorage.values.toList  
    }
    def create(x: User): User = {
      userStorage(x.id) = x
      x
    }
    def update(x: User): User = {
      userStorage(x.id) = x
      x
    }
    def delete(x: User): User = {
      userStorage -= x.id
      x
    }
  }

  "find user one" should {
    "return userOne" in {
       userOne.name === findUser("1").getOrElse(throw nonExistantUser)
    }
  }
}