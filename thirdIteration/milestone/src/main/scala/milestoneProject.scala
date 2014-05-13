object milestoneProject {

  case class User(id: String, name: String, balance: Double, transactions: List[Transaction])
  case class Transaction(id: String, amount: Double)

  var memoryBasedUserRepository = scala.collection.mutable.Map[String,User]()
  
  trait UserRepository {
    def getAll: List[User]
    def create(x: User): User = {
      memoryBasedUserRepository(x.id) = x
    }
    def update(x: User): User = {
      memoryBasedUserRepository(x.id) = x
    }
    def delete(x: User): User
  }
  
  // Transaction case class, containing an amount and an ID unique to that user). You will then need to create an implementation, 
  // MemoryBasedUserRepository, that stores all users in a data structure that your program holds locally. Think about what kind 
  // of data structure to use so your performance will not suffer if you get a large number of users!
}