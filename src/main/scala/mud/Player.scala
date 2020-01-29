package mud

class Player(val name: String, private var inventory: List[Item], private var location: Room) {

    val movement_commands = List("north","n","south","s","east","e","west","w")
    val print_commands = List("inv","inventory","look","help")
    init()
    
    def init(): Unit = {
        printCommands()
        println(location.description())
    }

    def processCommand(command: String): Unit = {
        if (movement_commands.contains(command)){
            move(command)
        }
        else if (print_commands.contains(command)){
            if (command == "help") printCommands()
            else if (command == "look") println(location.description())
            else println(inventoryListing())
        }
        else if (command.contains("get")){
            val item_name = command.substring(4,(command.length)).trim()
            addToInventory(location.getItem(item_name))
        }
        else if (command.contains("drop")){
            getFromInventory(command.substring(5,(command.length)).trim())

        }
        else println("Not a valid command.")
            
    }

    def getFromInventory(itemName: String): Unit = {            
        inventory.find(_.name.toLowerCase == itemName.toLowerCase) match {
        case Some(item) =>
            inventory = inventory.filter(_ != item)
            location.dropItem(item)
            println(item.name + " was dropped.")
        case None => 
            println(itemName + " is not in your inventory.")
        }
    }

    def addToInventory(itemName: Option[Item]): Unit = {
        if (itemName != None) {
        inventory ::= itemName.get
        println(itemName.get.name + " was added to your inventory.")
        }
        else println("That item is not on this planet.")
    }

    def inventoryListing(): Unit = {
        println("Inventory:")
        for (x <- 0 to inventory.length-1) {
            println("\t" + inventory(x).name + " - " + inventory(x).desc)
        }
    }

    def move(dir: String): Unit = {
        var next_room = location.getExit((movement_commands.indexOf(dir))/2)
            if (next_room != None){
                location = next_room.get
                println(location.description())
            }
            else println("No exit in that direction.")
    }

    def printCommands(): Unit = {
    println("""----------------- Action: Command -----------------------
    Move: north (n), south (s), east (e), west (w)
    Reprint room description: look
    See your inventory: inv/inventory
    Pick up an item: get 'item name'
    Drop an item: drop 'item name'
    Quit game: exit
    See all commands: help
---------------------------------------------------------""")
    }

}