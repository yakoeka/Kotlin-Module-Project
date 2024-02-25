import java.util.Scanner

class ArchivesList(private val updateScreenCallback: ScreenUpdateCallback) : MenuHolder {
    private val core = Core()
    override var menu: MutableList<MenuAction> = mutableListOf(
        MenuAction("Создать архив") {
            createArchive()
            true
        },
        MenuAction("Выход") {
            exit()
            false
        }
    )

    override fun selectAction(index: Int): Boolean {
        return menu[index].action.invoke()
    }

    fun openArchivesListScreen() {
        println("Открыт экран списка архивов")
        core.printMenu(menu.map { it.name }, "Список архивов")
        updateScreenCallback.updateScreen(this)
    }

    fun createArchive() {
        val scanner = Scanner(System.`in`)
        println("Введите название нового архива:")
        val newArchName = scanner.nextLine()

        if (newArchName.isNotEmpty()) {
            val newArchive = Archive(newArchName, this, updateScreenCallback)
            menu.add(menu.size - 1, MenuAction(newArchName) {
                newArchive.openArchiveScreen()
                true
            })
            println("Архив $newArchName успешно создан")
        } else {
            println("Название архива не может быть пустым")
        }

        this.openArchivesListScreen()
    }

    fun exit() {
        println("Выполнен выход из программы Архив заметок")
    }
}