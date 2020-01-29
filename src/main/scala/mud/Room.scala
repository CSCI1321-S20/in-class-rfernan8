package mud

class Room(val name: String, val desc: String, private var items: List[Item], private val exits: Array[Int]) {

  val directions = List(0,1,2,3) // north-0, south-1, east-2, west-3
  private var pretty_exits = List[String]()
  if (exits(0) != -1) pretty_exits ::= "North"
  if (exits(1) != -1) pretty_exits ::= "South"
  if (exits(2) != -1) pretty_exits ::= "East"
  if (exits(3) != -1) pretty_exits ::= "West"
  
  def description(): String = {
    var desc_string = name + "\n" + desc + "\nExits: "
    for (x <- 0 to pretty_exits.length-1) {
      if (x != pretty_exits.length-1) desc_string += (pretty_exits(x) + ", ")
      else desc_string += pretty_exits(x)
    }
    desc_string += "\nItems: "
    if (items.isEmpty){
      desc_string += "None"
    }
    else {
      for (y <- 0 to items.length-1) {
        if (y != items.length-1) desc_string += (items(y).name + ", ")
        else desc_string += items(y).name
      }
    }
    desc_string
  }

  def getExit(dir: Int): Option[Room] = {
    if (exits(dir) == -1) {
      None
    }
    else Some(Room.rooms(exits(dir)))
  }

  def getItem(itemName: String): Option[Item] = {
    items.find(_.name.toLowerCase == itemName.toLowerCase) match {
      case Some(item) =>
        items = items.filter(_ != item)
        Some(item)
      case None => None
    }
  }

  def dropItem(item: Item): Unit = items ::= item
}

object Room {
  val rooms = readRooms()

  def readRooms(): Array[Room] = {
    val source = scala.io.Source.fromFile("world.txt")
    val lines = source.getLines()
    val r = Array.fill(lines.next.toInt)(readRoom(lines))
    source.close()
    r
  }

  def readRoom(lines: Iterator[String]): Room = { 
    val room_name = lines.next()
    val room_desc = lines.next()
    val room_items = List.fill(lines.next().toInt)(Item(lines.next(), lines.next()))
    val room_exits = (lines.next().split(",")).map(_.toInt)
    new Room(room_name, room_desc, room_items, room_exits)
  }
}