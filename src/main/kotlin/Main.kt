import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    var currentScreen: MenuHolder
    val core = Core()
    var isContinue = true
    var command: Int
    val screenUpdateCallback = object : ScreenUpdateCallback {
        override fun updateScreen(newScreen: MenuHolder) {
            currentScreen = newScreen
        }
    }
    val archList = ArchivesList(screenUpdateCallback)
    archList.openArchivesListScreen()
    currentScreen = archList


    do {
        command = core.readCommand(scanner)
        isContinue = core.invokeCommand(currentScreen, command)
    } while (isContinue)
}