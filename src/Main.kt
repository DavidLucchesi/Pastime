object Main {

    @JvmStatic
    fun main(args: Array<String>) {
        LogManager.init()
        WebCrawler.run("google.fr")
        FileManager.clearTmp()
        LogManager.end()
    }
}
