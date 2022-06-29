import org.apache.log4j.Logger
import org.junit.Test

import java.io.File
import scala.::
import scala.io.Source



/**
 * @ClassName MemGraphTest
 * @Description TODO
 * @Author huchuan
 * @Date 2022/1/18
 * @Version 0.1
 */
@Test
class MemGraphTest {
  val memGraph = new MemGraph()
  val logger = Logger.getLogger(this.getClass)

  @Test
  def createTest(): Unit ={
    val queryFilesPath = System.getProperty("user.dir")+"/src/test/resources/queries"
    val queriesDir = new File(queryFilesPath)
    val files = queriesDir.listFiles()

    var cyphers:List[String] = List()

    files.foreach(e=>{
      val source = Source.fromFile(e)
      val lines = source.mkString
      try{
        memGraph.run(lines)
      }catch {
        case ex:Exception=>{
          cyphers = e.getName :: cyphers
        }
      }
    })

    logger.info("not support cyphers:")
    cyphers = cyphers.sorted
    cyphers.foreach(e=>logger.info(e))
  }
}
