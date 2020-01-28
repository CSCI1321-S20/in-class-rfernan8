package mud

case class Item(name: String, desc: String) {
    val sword = Item("Sword","A blade with a burning edge.")
    val axe = Item("Axe","Kills more than just trees.")
    val daggers = Item("Daggers","They won't see it coming.")
    val bat = Item("Bat", "Not the sharpest, but just as lethal.")
}