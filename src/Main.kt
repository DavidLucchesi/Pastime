object Main {

    @JvmStatic
    fun main(args: Array<String>) {
        LogManager.init()
        //WebCrawler.runDisplayPage("fnac.com")
        WebCrawler.runStockPage("fnac.com")
        //WebCrawler.runDisplayLinks("fnac.com")
        WebCrawler.runStockLinks("fnac.com")
        FileManager.clearTmp()
        LogManager.end()
    }
}
