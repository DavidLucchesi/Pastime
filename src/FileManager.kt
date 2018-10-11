import org.apache.commons.io.FileUtils

import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date

object FileManager {

    fun createFile(filePath: String): Int {
        try {
            val file = File(filePath)
            file.createNewFile()
        } catch (e: IOException) {
            e.printStackTrace()
            return -1
        }

        return 0
    }

    private fun deleteFile(filePath: String): Int {
        val file = File(filePath)
        if (file.exists()) {
            if (!file.delete())
                return -1
        }
        return 0
    }

    @Suppress("unused")
    private fun createNewFile(filePath: String): Int {
        val i = deleteFile(filePath)
        return i + createFile(filePath)
    }

    fun deleteAllFilesInFolder(folderPath: String): Int {
        val directory = File(folderPath)
        try {
            FileUtils.cleanDirectory(directory)
        } catch (e: IOException) {
            e.printStackTrace()
            return -1
        }

        return 0
    }

    @Suppress("unused")
    private fun copyFile(initialFilePath: String, finalFilePath: String): Int {
        val oldFile = File(initialFilePath)
        val newFile = File(finalFilePath)
        try {
            FileUtils.copyFile(oldFile, newFile)
        } catch (e: IOException) {
            e.printStackTrace()
            return -1
        }

        return 0
    }

    fun archiveFolderContent(folderPath: String): Int {
        val folder = File(folderPath)
        if (folder.list()!!.isNotEmpty()) {
            val dateFormat = SimpleDateFormat("-yyyyMMdd-HHmmss")
            val date = Date()
            val splittedPath = folderPath.split("\\\\".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val oldFolderName = splittedPath[splittedPath.size - 1]
            val newFolderName = oldFolderName + dateFormat.format(date)
            val archiveDestinationPath = "D:\\Pastime\\files\\archive\\$newFolderName"
            val archiveDestination = File(archiveDestinationPath)
            try {
                FileUtils.copyDirectory(folder, archiveDestination)
            } catch (e: IOException) {
                e.printStackTrace()
                return -1
            }

        }
        return 0
    }

    fun createDirectory(directory: String): Int {
        val newDirectory = File(directory)
        if (!newDirectory.exists()) {
            try {
                newDirectory.mkdir()
            } catch (se: SecurityException) {
                return -1
            }

        }
        return 0
    }

    fun clearTmp(): Int {
        return deleteAllFilesInFolder("D:\\Pastime\\files\\tmp")
    }
}
