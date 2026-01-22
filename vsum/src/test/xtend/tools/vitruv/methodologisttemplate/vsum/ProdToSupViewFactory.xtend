package tools.vitruv.methodologisttemplate.vsum

import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor
import tools.vitruv.methodologisttemplate.model.PLM.Domain;
import tools.vitruv.framework.views.View
import tools.vitruv.framework.vsum.VirtualModel
import tools.vitruv.framework.testutils.integration.TestViewFactory

@FinalFieldsConstructor
class ProdToSupViewFactory extends TestViewFactory {
	
	new(VirtualModel model) {
		super(model)
	}
	
	private def View createProdView() {
		createViewOfElements("Prod", #{Domain})
	}
	
	private def View createSupView() {
		
	}

	

	def void changeProdView((View)=>void modelModification) {
		changeViewRecordingChanges(createProdView, modelModification)
	}

	def void changePcmView((View)=>void modelModification) {
		changeViewRecordingChanges(createSupView, modelModification)
	}

	def void validateProdView((View)=>void viewValidation) {
		validateView(createProdView, viewValidation)
	}

	def void validatePcmView((View)=>void viewValidation) {
		validateView(createSupView, viewValidation)
	}
}
