package mud

class Player(val name: String, private var inventory: List[Item], location: Room) {
    def processCommand(command: String): Unit {
        ???
    }
    def getFromInventory(itemName: String): Option[Item] {
        ???
    }
    def addToInventory(itemName: Item): Unit {
        ???
    }
    def inventoryListing(): String {
        ???
    }
    def move(dir: String): Unit {
        ???
    }
}