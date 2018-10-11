import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

object LogManager {

    fun clearLogPart(): Int {
        FileManager.archiveFolderContent("D:\\Pastime\\files\\log")
        return FileManager.deleteAllFilesInFolder("D:\\Pastime\\files\\log")
    }

    fun createLogDirectory(): Int {
        return FileManager.createDirectory("D:\\Pastime\\files\\log")
    }

    fun createLogFile(): Int {
        return FileManager.createFile("D:\\Pastime\\files\\log\\log.txt")
    }

    fun writeLog(message: String): Int {
        val logFile = File("D:\\Pastime\\files\\log\\log.txt")
        try {
            val writer = FileWriter(logFile, true)
            try {
                writer.write(message)
            } finally {
                writer.close()
            }
        } catch (e: IOException) {
            e.printStackTrace()
            return -1
        }

        return 0
    }

    fun init(): Int {
        clearLogPart()
        createLogDirectory()
        createLogFile()
        val dateFormat = SimpleDateFormat("yyyy/MM/dd - HH:mm:ss")
        val date = Date()
        return writeLog("Started at : " + dateFormat.format(date) + "\n")
    }

    fun end(): Int {
        val dateFormat = SimpleDateFormat("yyyy/MM/dd - HH:mm:ss")
        val date = Date()
        return writeLog("Ended at : " + dateFormat.format(date))
    }
}
