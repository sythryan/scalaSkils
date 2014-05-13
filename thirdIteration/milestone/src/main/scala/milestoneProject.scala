object milestoneProject {

  case class User(id: String, name: String, balance: Double, transactions: List[Transaction])
  case class Transaction(id: String, amount: Double)
  
  trait UserRepository {
    def getAll: List[User]
    def create(x: User): User
    def update(x: User): User
    def delete(x: User): User
  }
  
  trait memoryBasedUserRepository  extends UserRepository {
    var userStorage = scala.collection.mutable.Map[String,User]()
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

  class userManipulator extends memoryBasedUserRepository {
    def findUser(theID: String): Option[User] = theID {
      def recursiveFindUser(userList: List[User]): Option[User] = userList match {
        case Nil => None
        case head :: tail => if (head.id == theID) Some(head) else recursiveFindUser(tail)
      } 
      recursiveFindUser(getAll)    
    }

    // def findAllUsersAbove (balance: Double, users: List[User]): List[User] = {
    //   def recursiveFindallUsersAbove(userList: List[User]): List[User] = userList.balance match {
    //     case _ => 
    //   }
    // }
  }
  
// Find a single user by ID.
// Find all users above a given balance
// Find the value of the bank (that is, the sum of all balances from all users)
// Add a list of transactions to a single user.
}