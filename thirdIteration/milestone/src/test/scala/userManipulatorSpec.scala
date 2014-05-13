import org.specs2.mutable.Specification
import org.specs2.specification.{Scope, After, BeforeAfterEach}
import milestone._

  trait MockRepository extends UserRepository {
    val userOne = User("1", "Joe", 55.34, List(Transaction("tran1", -23.43), Transaction("tran2", 43.00)))
    val userTwo = User("2", "Haley", 2055.38, List(Transaction("tran1", 205.01)))
    val userThree = User("3", "Mildred", 403.67, None)
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

// object UserManipulatorSpec extends Specification with UserManipulator {
//   "find userr"
// }