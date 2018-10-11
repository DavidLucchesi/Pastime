import java.io.BufferedReader
import java.io.FileWriter
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

object WebCrawler {

    private fun init() {
        val dateFormat = SimpleDateFormat("yyyy/MM/dd - HH:mm:ss")
        val date = Date()
        LogManager.writeLog("Web Crawler started at : " + dateFormat.format(date))
    }

    private fun end() {
        val dateFormat = SimpleDateFormat("yyyy/MM/dd - HH:mm:ss")
        val date = Date()
        LogManager.writeLog("Web Crawler ended at : " + dateFormat.format(date))
    }

    private fun getHTTPPage(page: String): List<String> {
        LogManager.writeLog("Sending 'GET' request to website : $page")
        val url = URL("https://$page")
        with(url.openConnection() as HttpURLConnection) {
            requestMethod = "GET"
            LogManager.writeLog("Response Code : $responseCode")
            BufferedReader(InputStreamReader(inputStream)).use {
                val response = StringBuffer()
                var inputLine = it.readLine()
                while (inputLine != null) {
                    response.append(inputLine)
                    inputLine = it.readLine()
                }
                val regex = "(?!</)(?!<=)<"
                return response.split(regex.toRegex())

            }
        }
    }

    private fun displayHTTPPage(fields: List<String>, page: String) {
        LogManager.writeLog("Displaying HTTP Page for $page")
        for (i in fields) {
            if (i == fields[0])
                print(i)
            else
                println("<$i")
        }
        LogManager.writeLog("HTTP Page for $page displayed")
    }

    private fun stockHTTPPage(fields: List<String>, page: String) {
        LogManager.writeLog("Stocking HTTP Page for $page")
        val dateFormat = SimpleDateFormat("yyyyMMddHHmmss")
        val date = Date()
        FileManager.createDirectory("D:\\Pastime\\files\\website")
        val pageWithoutSpecialCharacters = page.replace("/", "-S-").replace("?", "-B-").replace("%", "-P-").replace("*", "-W-")
        val websiteFile = "D:\\Pastime\\files\\website\\$pageWithoutSpecialCharacters-" + dateFormat.format(date) + ".txt"
        FileManager.createFile(websiteFile)

        try {

            for (i in fields) {
                if (i == fields[0]) {
                    val writer = FileWriter(websiteFile, true)
                    writer.use { writerUsed -> writerUsed.write(i) }
                } else {
                    val writer = FileWriter(websiteFile, true)
                    writer.use { writerUsed -> writerUsed.write("<$i\n") }
                }
            }
            LogManager.writeLog("HTTP Page for $page stocked")
        } catch (e: IOException) {
            LogManager.writeLog("A problem occurred during while stocking HTTP Page for $page")
            e.printStackTrace()
        }


    }

    private fun getLinks(fields: List<String>, page: String): List<String> {
        LogManager.writeLog("Getting links from $page")
        val urlRegex = "https?://(www\\.)?[-a-zA-Z0-9@:%._+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_+.~#?&/=]*)".toRegex()
        val urls: MutableList<String> = arrayListOf()
        for (i in fields) {
            for (j in urlRegex.findAll(i)) {
                urls.add(j.value)
            }
        }
        LogManager.writeLog("Links from $page got")
        return urls
    }

    private fun displayLinks(fields: List<String>, page: String) {
        LogManager.writeLog("Displaying links from $page")
        for (s in fields) {
            println(s)
        }
        LogManager.writeLog("Links from $page displayed")
    }

    private fun stockLinks(fields: List<String>, page: String) {
        LogManager.writeLog("Stocking links for $page")
        val dateFormat = SimpleDateFormat("yyyyMMddHHmmss")
        val date = Date()
        FileManager.createDirectory("D:\\Pastime\\files\\website\\links")
        val pageWithoutSpecialCharacters = page.replace("/", "-S-").replace("?", "-B-").replace("%", "-P-").replace("*", "-W-")
        val websiteFile = "D:\\Pastime\\files\\website\\links\\$pageWithoutSpecialCharacters-" + dateFormat.format(date) + ".txt"
        FileManager.createFile(websiteFile)

        try {

            for (i in fields) {
                val writer = FileWriter(websiteFile, true)
                writer.use { writerUsed -> writerUsed.write("$i\n") }
            }
            LogManager.writeLog("Links for $page stocked")
        } catch (e: IOException) {
            LogManager.writeLog("A problem occurred during while stocking links for $page")
            e.printStackTrace()
        }


    }

    @Suppress("unused")
    fun runDisplayPage(searchField: String) {
        init()
        displayHTTPPage(getHTTPPage(searchField), searchField)
        end()
    }

    @Suppress("unused")
    fun runStockPage(searchField: String) {
        init()
        stockHTTPPage(getHTTPPage(searchField), searchField)
        end()
    }

    @Suppress("unused")
    fun runDisplayLinks(searchField: String) {
        init()
        displayLinks(getLinks(getHTTPPage(searchField), searchField), searchField)
        end()
    }

    @Suppress("unused")
    fun runStockLinks(searchField: String) {
        init()
        stockLinks(getLinks(getHTTPPage(searchField), searchField), searchField)
        end()
    }
}
