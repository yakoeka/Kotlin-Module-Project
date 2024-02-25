import java.util.Scanner

class Core {
    fun String.isNumber(): Boolean {
        return try {
            this.toInt()
            true
        } catch (e: NumberFormatException) {
            false
        }
    }

    fun readCommand(scanner: Scanner): Int {
        var commandNumberStr: String = Scanner(System.`in`).nextLine()
        if (commandNumberStr.isNumber()) {
            return commandNumberStr.toInt()
        }
        while (!commandNumberStr.isNumber()) {
            println("Введен некорректный формат. Введите номер нужной команды")
            commandNumberStr = Scanner(System.`in`).nextLine()
        }
        return commandNumberStr.toInt()
    }

    fun <T : MenuHolder> invokeCommand(obj: T, command: Int): Boolean {
        if (command in obj.menu.indices) {
            return obj.selectAction(command)
        }
        println("Введен несуществующий номер команды")
        return true
    }

    fun <T> printMenu(menu: List<T>, screenName: String) {
        println("$screenName:")
        menu.forEachIndexed { index, item ->
            println("$index. $item")
        }
    }
}