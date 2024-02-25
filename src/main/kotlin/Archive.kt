import java.util.Scanner

class Archive(val nameArch: String, private val archivesList: ArchivesList, private val updateScreenCallback: ScreenUpdateCallback) : MenuHolder {
    private val core = Core()
    override var menu: MutableList<MenuAction> = mutableListOf(
        MenuAction("Создать заметку") {
            createNote()
            true
        },
        MenuAction("Назад") {
            back()
            true
        }
    )
    var notesMap: MutableMap<String, String> = mutableMapOf()

    override fun selectAction(index: Int): Boolean {
        return menu[index].action.invoke()
    }

    fun openArchiveScreen() {
        println("Открыт экран $nameArch")
        core.printMenu(menu.map { it.name }, nameArch)
        updateScreenCallback.updateScreen(this)
    }

    fun createNote() {
        val scanner = Scanner(System.`in`)
        println("Введите название заметки:")
        val newNoteName = scanner.nextLine()
        println("Введите текст заметки (введите 'EOF' для завершения):")
        val noteText = StringBuilder()
        while (true) {
            val line = scanner.nextLine()
            if (line == "EOF") break
            noteText.append(line).append("\n")
        }
        if (newNoteName.isNotEmpty() && noteText.isNotEmpty()) {
            notesMap[newNoteName] = noteText.toString()
            menu.add(menu.size - 1, MenuAction(newNoteName) {
                Note(
                    newNoteName,
                    notesMap[newNoteName]!!,
                    this,
                    updateScreenCallback
                ).openNoteScreen()
                true
            })
            println("Заметка $newNoteName сохранена")
        } else {
            println("Заметка не создана, так как название или содержимое заметки не может быть пустым")
        }
        this.openArchiveScreen()
    }

    fun back() {
        println("Возвращаемся на предыдущий экран...")
        archivesList.openArchivesListScreen()
    }
}