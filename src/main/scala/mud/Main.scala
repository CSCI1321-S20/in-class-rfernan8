package mud

object Main {
    def main (args: Array[String]): Unit = {
        
        println("Welcome! What would you like to be called?")
        val name = readLine()
        val p1 = new Player(name, Nil, Room.rooms(0))
        
        var command = readLine().toLowerCase()
        while (command != "exit"){
            p1.processCommand(command)
            command = readLine().toLowerCase()
        }
        println("Goodbye!")
    }
}