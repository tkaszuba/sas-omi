package poc


import scala.collection.JavaConverters._
import com.sas.metadata.remote.CMetadata
import poc.ObjectExtender._

object export {

       def get_job_export_options(name: String) = {
            val repo = OMI.use("TEST_REPO")
            val job = repo.get.Job(name, s"@Name = '${name}'")

            println(job) 
            /* rescue Exception => e
                logger.error "Job #{name}: #{e}"
            end

            if job.nil? then
                logger.error " the job #{name} was not found in the repository"
                return nil
            end

            # repositories are now only maintained as a folder under TEST_REPO
            job_repository_name = job.path.split('/')[2]

            # exception for /LOCAL which contains more subrepositories
            if job_repository_name == "LOCAL" then
                job_repository_name = job.path.split('/')[3]
            end

            Find.find( File.expand_path( dir ) ) do |path|
                next unless path =~ /\/#{job_repository_name}$/
                path += '/Jobs'
                File.directory? path or FileUtils.mkdir_p path
                File.open("#{path}/#{job.Name}.yml", 'w+') do |yml|
                    yml.puts Hash[
                                 'repository' => job_repository_name,
                                 'path'       => job.path,
                                 'subjobs'    => job.JobActivities.map { |ja|
                                     ja.Steps.map { |s|
                                         s.Transformations.select { |t| t.isa_Job? }.map { |j| j.Name }
                                     }}.flatten
                             ].to_yaml
                end
                return [path, job.Name, job.xmo]
            end
           */
         }
}
