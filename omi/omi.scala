package poc

import java.util.Calendar
import scala.collection.JavaConverters._
import com.sas.metadata.remote.CMetadata
import poc.ObjectExtender._

object OMI {

  private val host = "localhost"
  private val port = "0000"
  private val user = "sas"
  private val pass = "{sas001}xxxxxxxxxxxxxxxxxxxxxxxxxxx"
  
  private var mdc:SasMDConnector = null

  def main(args: Array[String]): Unit = {
     mdc = new SasMDConnector(host, port, user, pass)
     println(status)
     val y = repositories.asScala.map(_.asInstanceOf[CMetadata])
     val x = y.map{_.repository}
     println(x)
     val repo = use("TEST_REPO")
     println(repo.path)
     println(repo.xmo)

     println(repo.isa.Root)

     repo.got.something2
     repo.has.something3

     test(repo)
 
     dispose()
     
     //Bug in mdconnecter
     println(isConnected)

     // Something keeps the connections open, bug in mdconnector
     System.exit(0)
  }
  
  def test(repo: CMetadata) = {
    println(Calendar.getInstance().getTime())
    for (i <- 1 to 100000)
      repo.get.Job(s"@Name = 'test job'")
    //println(job)
    println(Calendar.getInstance().getTime())

  }

  def repositories =  mdc.getRepositories()
  def status = mdc.displayServerInformation()
  def isConnected = mdc.isConnected()
  def use(repoName: String, fullName: Boolean = false): CMetadata  = mdc.useRepositoryByName(repoName, fullName)
  def getRepositoryByShortId(id: String) = mdc.getRepositoryByShortId(id) 
  def getObjectsOfType(name: String, query: String) = mdc.getObjectsOfType(name, query)

  def dispose() = {
     mdc.disposeMDObjectStore()
     mdc.connectionDispose()
  }

}


