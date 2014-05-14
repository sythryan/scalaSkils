package milestone

case class User(id: String, name: String, balance: Double, transactions: List[Transaction])
case class Transaction(id: String, amount: Double)

trait UserRepository {       //interface
  def getAll: List[User]
  def create(x: User): User
  def update(x: User): User
  def delete(x: User): User
}

trait MemoryBasedUserRepository  extends UserRepository {        //interface implementation
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

trait UserManipulator extends MemoryBasedUserRepository {
  val nonExistantUser = User("", "", 0.0, List())

  def findUser(theID: String): User = {
    def recursiveFindUser(userList: List[User]): Option[User] = userList match {
      case Nil => None
      case head :: tail if (head.id == theID) => Some(head)
      case head :: tail => recursiveFindUser(tail)
    } 
    recursiveFindUser(getAll).getOrElse(nonExistantUser)    
  }

  def findAllUsersAbove (balance: Double): List[User] = {
    def recursiveFindallUsersAbove(userList: List[User], accumulator: List[User]): List[User] = userList match {
      case Nil => accumulator
      case head :: tail if (head.balance > balance) => recursiveFindallUsersAbove(tail, accumulator :+ head)
      case head :: tail => recursiveFindallUsersAbove(tail, accumulator)
    }
    recursiveFindallUsersAbove(getAll, List())
  }

  def bankValue: Double = {
    BigDecimal(getAll.foldLeft(0.0) ((b, a) => b + a.balance)).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
  }

  def addTransactions (theID: String, theTransactions: List[Transaction]): User = {
    val currentUser = findUser(theID)
    val updatedUser = User(currentUser.id, currentUser.name, currentUser.balance, currentUser.transactions ++ theTransactions)   
    if (currentUser != nonExistantUser){
      update(updatedUser)
      updatedUser
    } else {
      nonExistantUser
    }
  }
}