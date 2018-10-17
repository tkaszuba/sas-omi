package poc


import scala.collection.JavaConverters._
import com.sas.metadata.remote.CMetadata
import poc.ObjectExtender._

object export {

       def get_job_export_options(name: String) = {
            val repo = OMI.use("TEST_REPO")
            val job = repo.get.Job(name, s"@Name = '${name}'")

            println(job) 
         }
}
