package mud

object Main {
    def main (args: Array[String]): Unit = {
        
        println("Welcome! What would you like to be called?")
        val name = readLine()
        printCommands()
        val p1 = new Player(name, Nil, Room.rooms(0))
        
        var command = readLine().toLowerCase()
        while (command != "exit"){
            if (command == "help"){
                printCommands()
            }
            else p1.processCommand(command)
            command = readLine().toLowerCase()
        
        println("Goodbye!")
        }
    
        def printCommands(): Unit = {
            println("----------------- Action: Command ------------------")
            println("Movement: north (n), south (s), east (e), west (w)")
            println("Reprint room description: look")
            println("See your inventory: inv/inventory")
            println("Pick up an item: get 'item name'")
            println("Drop an item: drop 'item name'")
            println("Quit game: exit")
            println("See all commands: help")
            println("----------------------------------------------------")
        }


    }
}