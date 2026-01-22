package tools.vitruv.methodologisttemplate.consistency

import java.util.ArrayList
import tools.vitruv.methodologisttemplate.model.PLM.DeepModel
import tools.vitruv.methodologisttemplate.model.PLM.OwnedElement
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EAnnotation
import org.eclipse.emf.ecore.EGenericType
import tools.vitruv.methodologisttemplate.model.PLM.Entity
import tools.vitruv.methodologisttemplate.model.PLM.PLMFactory
import tools.vitruv.methodologisttemplate.model.PLM.Classification
import tools.vitruv.methodologisttemplate.model.PLM.Connection
import tools.vitruv.methodologisttemplate.model.PLM.DeepModel
import tools.vitruv.methodologisttemplate.model.PLM.Domain
import tools.vitruv.methodologisttemplate.model.PLM.Element
import tools.vitruv.methodologisttemplate.model.PLM.Level
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl
import org.eclipse.emf.ecore.util.EcoreUtil
import tools.vitruv.change.utils.ResourceAccess

class Util {
	static def OwnedElement getElementWithName(String name, DeepModel model) {
		val clabjects = new ArrayList()
		model.content.stream().map[it.content].forEach[clabjects.addAll(it)]
		clabjects.findFirst[it.name == name]
		
	}

	static def int getLevelIndex(Level level) {
		val deepModel = level.eContainer as DeepModel
		return deepModel.content.indexOf(level) - 1
	}
	
	static def int getLevel(OwnedElement element) {
		val level = element.eContainer as Level
		return getLevelIndex(level)
	}

	static def Level getLevelAtIndex(DeepModel deepModel, int level) {
		if (level >= 0 && level < deepModel.content.size) {
			return deepModel.content.get(level + 1)
		}
		return null
	}
	
	static def String getTag(String model, Domain domain) {
		model+"::"+domain.class.simpleName+"::"+domain.name
	}
	
	static def String getTag(String model, Element element) {
		model+"::"+element.class.simpleName+"::"+element.name
	}
	
	static def <T extends Entity> creater(EObject eo) {
		switch (eo) {
			case Entity: PLMFactory.eINSTANCE.createEntity
			case Classification:PLMFactory.eINSTANCE.createClassification
			case Connection:PLMFactory.eINSTANCE.createConnection
		}
	}
	
	static def resolve(EObject eObject, EObject toResolve, ResourceAccess resourceAccess) {
		var uri = URI.createURI((eObject as MinimalEObjectImpl).eProxyURI.toString.replace("producer", "supporter"))
		var resourceUri = URI.createURI(uri.toString.substring(0, uri.toString.indexOf("#")))
		return EcoreUtil.resolve(toResolve, resourceAccess.getModelResource(resourceUri)) as DeepModel
	}
}
