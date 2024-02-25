class Note(
    val name: String,
    val textNote: String,
    private val archive: Archive,
    private val updateScreenCallback: ScreenUpdateCallback
) : MenuHolder {
    private val core = Core()
    override var menu: MutableList<MenuAction> = mutableListOf(
        MenuAction("Открыть заметку") {
            readNote()
            true
        },
        MenuAction("Назад") {
            back()
            true
        })

    override fun selectAction(index: Int): Boolean {
        return menu[index].action.invoke()
    }

    fun openNoteScreen() {
        println("Открыт экран $name")
        core.printMenu(menu.map { it.name }, name)
        updateScreenCallback.updateScreen(this)
    }

    fun readNote() {
        println("Заметка '$name':")
        println(textNote)
        this.openNoteScreen()
    }

    fun back() {
        println("Возвращаемся на предыдущий экран...")
        archive.openArchiveScreen()
    }
}