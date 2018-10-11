object Main {

    @JvmStatic
    fun main(args: Array<String>) {
        LogManager.init()
        //WebCrawler.runDisplayPage("google.fr")
        WebCrawler.runStockPage("google.fr")
        FileManager.clearTmp()
        LogManager.end()
    }
}
