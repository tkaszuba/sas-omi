package poc

import scala.language.dynamics

import com.sas.metadata.remote.MdObjectBase
import com.sas.metadata.remote.MdObjectBaseUtil
import com.sas.metadata.remote.PrimaryType
import com.sas.metadata.remote.Tree
import com.sas.metadata.remote.Root


object ObjectExtender { 

  class GotMethod (obj:Object) extends Dynamic {

    def selectDynamic(sym: String) = println(sym)

    def applyDynamic(sym: String)(args: Any*) = {
       println(s"dynamic call $sym")
    }

  }
  
  class HasMethod (obj:Object) extends Dynamic {
    def selectDynamic(sym: String) = {
        obj match {
           case md : MdObjectBaseUtil => 
             if (md.getAssociationNames.contains(sym)) md.getMdObjectAssociation(sym).size > 0 else false
           case _ => false
        }
    }
  }

  class IsaMethod (obj:Object) extends Dynamic {
    def selectDynamic(name: String) = obj.getClass.getName == s"com.sas.metadata.remote.impl.${name}Impl" 
  }

  class EachMethod (obj:Object) extends Dynamic {
    def selectDynamic(name: String) = obj.getClass.getName == s"com.sas.metadata.remote.impl.${name}Impl"
  }

  class GetMethod (obj:Object) extends Dynamic {
    def applyDynamic(name: String)(args: String*) = {
       OMI.getObjectsOfType(name, args.toList.head)
    }

    //def selectDynamic(name: String) = OMI.getObjectsOfType(name, query) 
  }


 
  implicit class OmiObject (obj: Object) {

    def got = new GotMethod(obj)
    def has = new HasMethod(obj)
    def isa = new IsaMethod(obj)
    def each = new EachMethod(obj)
    def get = new GetMethod(obj)

    def repository  = {
        obj match {
           case md : MdObjectBase => OMI.getRepositoryByShortId(md.getFQID.split('.').last)
           case _ => null
        }
    }

    // Return the path in the SAS Metadata Repository
    def path : String = {
        obj match {
           //case st : String => false
           case tree : Tree => "%s/%s".format(if (tree.getParentTree != null) tree.getParentTree.path else ("", tree.getName))
           case root : Root => 
             if (has.Trees) root.getTrees.get(0).path else "" //s"${root.getName}"
           case _ => ""
        }
    }

    def xmo : String = {
        obj match {
           case pt : PrimaryType => "%s/%s(%s)".format(pt.path, pt.getName, pt.getPublicType)
           case _ => null
        }
    }

  }

}
