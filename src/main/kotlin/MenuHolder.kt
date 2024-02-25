interface MenuHolder {
    val menu: MutableList<MenuAction>
    fun selectAction(index: Int): Boolean
}