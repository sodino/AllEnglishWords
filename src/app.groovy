def baseDir = './../res/'
def fileWordsList = 'all.english.word.69287.log'

def wordUrl = 'http://www.iciba.com/index.php?a=getWordMean&c=search&word='

def inputFile = new File(baseDir + fileWordsList)

def count = 0

inputFile.eachLine {word ->
//    println(word)
    String fullUrl = wordUrl + word
    URL requestUrl = new URL (fullUrl)
    String wordJson = requestUrl.getText()
    def outputFile = new File("${baseDir}words/${word}.json")
    if (!outputFile.getParentFile().exists()) {
        outputFile.getParentFile().mkdirs()
    }
    if (outputFile.exists()) {
        outputFile.delete()
    }
    outputFile.createNewFile()

    wordJson = wordJson.replace("\\/", "/")

    wordJson = AsciiUtil.ascii2Native(wordJson)
    outputFile << wordJson
    println("write : ${word}")
}
