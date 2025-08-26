import kotlin.system.exitProcess

data class Notification(
    val id: Int,
    val message: String,
    val type: NotificationType
)

enum class NotificationType {
    INFO, WARNING, ERROR
}

interface Notifier {
    fun notify(notification: Notification)
}

class CliNotifier : Notifier {
    override fun notify(notification: Notification) {
        when (notification.type) {
            NotificationType.INFO -> println("\u001B[34mINFO: ${notification.message}\u001B[0m")
            NotificationType.WARNING -> println("\u001B[33mWARNING: ${notification.message}\u001B[0m")
            NotificationType.ERROR -> {
                println("\u001B[31mERROR: ${notification.message}\u001B[0m")
                exitProcess(1)
            }
        }
    }
}

class ResCliToolNotifier : CliNotifier() {
    override fun notify(notification: Notification) {
        super.notify(notification)
        // add additional logic for responsive cli tool notifier here
    }
}

fun main() {
    val notifier = ResCliToolNotifier()
    notifier.notify(Notification(1, "Hello, World!", NotificationType.INFO))
    notifier.notify(Notification(2, "Be careful!", NotificationType.WARNING))
    notifier.notify(Notification(3, "Something went wrong!", NotificationType.ERROR))
}