import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

object WebCrawler {

    private fun init() {
        val dateFormat = SimpleDateFormat("yyyy/MM/dd - HH:mm:ss")
        val date = Date()
        LogManager.writeLog("Web Crawler launching at : " + dateFormat.format(date))
    }

    private fun end() {
        val dateFormat = SimpleDateFormat("yyyy/MM/dd - HH:mm:ss")
        val date = Date()
        LogManager.writeLog("Web Crawler ending at : " + dateFormat.format(date))
    }

    private fun displayHTTPPage(page: String) {
        val url = URL("https://$page")

        with(url.openConnection() as HttpURLConnection) {
            requestMethod = "GET"

            LogManager.writeLog("Sending 'GET' request to website : $page")
            LogManager.writeLog("Response Code : $responseCode")

            BufferedReader(InputStreamReader(inputStream)).use {
                val response = StringBuffer()
                var inputLine = it.readLine()
                while (inputLine != null) {
                    response.append(inputLine)
                    inputLine = it.readLine()
                }
                val regex = "(?!</)(?!<=)<"
                val regexReplacement = "<"
                val responseOnSeveralLines = response.split(regex.toRegex())
                for (i in responseOnSeveralLines) {
                    if (i == responseOnSeveralLines[0])
                        print(i)
                    else
                        println("$regexReplacement$i")
                }

            }
        }
    }

    fun run(searchField: String) {
        init()
        displayHTTPPage(searchField)
        end()
    }
}
