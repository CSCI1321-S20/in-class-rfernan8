package mud

class Room(val name: String, val desc: String, private var items: List[Item], private val exits: Array[Int]) {

  val directions = List(0,1,2,3) // north-0, south-1, east-2, west-3
  val exit_arr = Array[String](exits.length)
  
  for (x <- exits) {
  }
  
  
  
  
  
  //)

  def description(): String = name + "\n" + desc + "\n" + "Exits: " + exits.mkString(",") + "\n" + "Items: " + items.mkString(",") // exits is currently ints

  def getExit(dir: Int): Option[Room] = {
    exits.find(_ == dir) match {
    case Some(exit) =>
      // room
    case None => None  
    }
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
    val room_items = lines.next().split(",").toList
    val room_exits = (lines.next().split(",")).map(_.toInt)
    new Room(room_name, room_desc, room_items, room_exits)
  }
}