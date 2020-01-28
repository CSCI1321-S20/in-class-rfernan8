package mud

class Player(val name: String, private var inventory: List[Item], private var location: Room) {

    val movement_commands = List("north","n","south","s","east","e","west","w")
    val print_commands = List("inv","inventory")
    init()
    
    def init(): Unit = {
        println(location.description())
    }

    def processCommand(command: String): Unit = {
        if (movement_commands.contains(command)){
            move(command)
        }
        else if (print_commands.contains(command)){
            println(inventoryListing())
        }
        else if (command.contains("get")){
            addToInventory(command.substring(4,(command.length))) 
        }
        else if (command.contains("drop")){
            getFromInventory(command.substring(5,(command.length)))

        }
        else println("Not a valid command.")
            
    }

    def getFromInventory(itemName: String): Unit = {            
        inventory.find(_.name.toLowerCase == itemName.toLowerCase) match {
        case Some(item) =>
            inventory = inventory.filter(_ != item)
            location.dropItem(item)
            println(itemName ++ " was dropped.")
        case None => 
            println(itemName + " is not in your inventory.")
        }
    }

    def addToInventory(itemName: Item): Unit = {
        if (location.getItem(itemName) != None){
            inventory ::= itemName
            println(itemName + " was added to your inventory.")
        }
        else {
            println("Item is not in this room.")
        }
    }

    def inventoryListing(): String = {
        println("Inventory:")
        for (x <- inventory) println("\t" + x.name + " - " + x.desc)
    }

    def move(dir: String): Unit = {
        var next_room = location.getExit((movement_commands.indexOf(dir))/2)
            if (next_room != None){
                location = next_room
                println(location.description())
            }
            else println("No exit in that direction.")
    }
}