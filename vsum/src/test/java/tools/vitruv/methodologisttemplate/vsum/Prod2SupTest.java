package tools.vitruv.methodologisttemplate.vsum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tools.vitruv.methodologisttemplate.model.PLM.Clabject;
import tools.vitruv.methodologisttemplate.model.PLM.Level;
import tools.vitruv.methodologisttemplate.model.PLM.DeepModel;
import tools.vitruv.methodologisttemplate.model.PLM.Domain;
import tools.vitruv.methodologisttemplate.model.PLM.PLMFactory;
import tools.vitruv.methodologisttemplate.model.PLM.Entity;
import tools.vitruv.methodologisttemplate.consistency.Util;
import tools.vitruv.framework.views.View;

public class Prod2SupTest extends ProdToSupVitruvApplicationTest {
	
	@Test
	public void test() {
		loadAndRegisterModels();
		 viewFactory.changeProdView((v)-> {
			 var entity = PLMFactory.eINSTANCE.createEntity();
			 entity.setName("S400_003");
			 var classification = PLMFactory.eINSTANCE.createClassification();
			 classification.setInstance(entity);
			 classification.setType((Clabject) Util.getElementWithName("S400", producer));
			 getSecondLevel(v).getContent().add(entity);
			 getSecondLevel(v).getContent().add(classification);
		 });
		 viewFactory.validateProdView((v) -> {
			Assertions.assertNotNull(Util.getElementWithName("S400_003", getSupporterDeepModel(v)));
			Assertions.assertEquals("S400", (((Entity) Util.getElementWithName("S400_003", getSupporterDeepModel(v))).getDirectType().getName()));
		 });
	}
	
	private Level getSecondLevel(View v) {
		var dm = ((Domain) v.getRootObjects().iterator().next()).getDeepModel().get(0);

		System.out.println("Runtime class: " + dm.getClass());
		System.out.println("Runtime loader: " + dm.getClass().getClassLoader());
		System.out.println("Expected loader: " + DeepModel.class.getClassLoader());

		return ((DeepModel) ((Domain) v.getRootObjects().iterator().next()).getDeepModel().get(0)).getLevelAtIndex(3);		
	}
	
	private DeepModel getSupporterDeepModel(View v) {
		var iterator = v.getRootObjects().iterator();
		iterator.next();
		return ((DeepModel) ((Domain) iterator.next()).getDeepModel().get(0));	
	}
	
	
}
