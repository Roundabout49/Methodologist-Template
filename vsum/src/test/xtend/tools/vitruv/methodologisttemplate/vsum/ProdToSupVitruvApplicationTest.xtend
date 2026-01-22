package tools.vitruv.methodologisttemplate.vsum

import java.nio.file.Path
import java.util.HashMap
import java.util.Map
import java.util.function.Supplier
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EOperation
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.EValidator
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.ocl.ecore.OCL;
/*
import org.eclipse.ocl.ecore.delegate.OCLDelegateDomain;
import org.eclipse.ocl.ecore.delegate.OCLInvocationDelegateFactory
import org.eclipse.ocl.ecore.delegate.OCLSettingDelegateFactory
import org.eclipse.ocl.ecore.delegate.OCLValidationDelegateFactory
*/
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl
import org.junit.jupiter.api.BeforeAll
import tools.vitruv.methodologisttemplate.model.PLM.DeepModel;
import tools.vitruv.methodologisttemplate.model.PLM.Domain;
import tools.vitruv.methodologisttemplate.model.PLM.Element;
import tools.vitruv.methodologisttemplate.model.PLM.PLMPackage;
import tools.vitruv.dsls.reactions.runtime.correspondence.CorrespondenceFactory
import tools.vitruv.dsls.reactions.runtime.correspondence.ReactionsCorrespondence
import tools.vitruv.framework.vsum.internal.InternalVirtualModel
import tools.vitruv.framework.testutils.integration.ViewBasedVitruvApplicationTest
import mir.reactions.prod2sup.Prod2supChangePropagationSpecification

class ProdToSupVitruvApplicationTest extends ViewBasedVitruvApplicationTest {
	protected var extension ProdToSupViewFactory viewFactory
	
	protected var extension DeepModel producer
	protected var extension DeepModel supporter
	protected val extension ResourceSet resSet = new ResourceSetImpl()
	val Map<EObject, Map<String, EObject>> classNameToEObject = new HashMap
	static val Map<String, String> correspondences = new HashMap

	// Enable invocation delegation for OCL
	@BeforeAll
	def static void initOCL() {
		OCL.initialize(null)
		/*
		OCL.initialize(null)
        OCLDelegateDomain.initialize(null)
		EOperation.Internal.InvocationDelegate.Factory.Registry.INSTANCE.put(
			"http://www.eclipse.org/emf/2002/Ecore/OCL",
			new OCLInvocationDelegateFactory()
		)
		EStructuralFeature.Internal.SettingDelegate.Factory.Registry.INSTANCE.put(
			"http://www.eclipse.org/emf/2002/Ecore/OCL",
			new OCLSettingDelegateFactory()
		)
		EValidator.ValidationDelegate.Registry.INSTANCE.put(
			"http://www.eclipse.org/emf/2002/Ecore/OCL",
			new OCLValidationDelegateFactory()
		)
		println("OCL Delegates initialized:")
		println(
			EOperation.Internal.InvocationDelegate.Factory.Registry.INSTANCE
				.get("http://www.eclipse.org/emf/2002/Ecore/OCL")
		)
		*/
	}
	
	@BeforeAll
	def static void setupFactories() {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());
		EPackage.Registry.INSTANCE.put("http://melanee.org/PLM", PLMPackage.eINSTANCE)
		
	}
	
	// What is this??
	@BeforeAll
	def static void prepareCorrespondences() {
		correspondences.put("DeviceModel","DeviceModel")
		correspondences.put("MobilePhoneModel","MobilePhoneModel")
		correspondences.put("Company","Company")
		correspondences.put("Factory","Factory")
		correspondences.put("MobilePhoneFactory","MobileFactory")
		correspondences.put("HuaweiMobilePhoneFactory","HuaweiMobilePhoneFactory")
		correspondences.put("HuaweiMobilePhoneModel","HuaweiMobilePhoneModel")
		correspondences.put("Device","Device")
		correspondences.put("MobilePhoneDevice","MobilePhoneDevice")
		correspondences.put("HuaweiMobilePhoneDevice","HuaweiMobilePhoneDevice")
		correspondences.put("Huawei","Huawei")
		correspondences.put("Factory124","Factory124")
		correspondences.put("S400","S400")
		correspondences.put("S500","S500")
		correspondences.put("S400_001","S400_001")
		correspondences.put("S400_002","S400_002")
	}

	override protected enableTransitiveCyclicChangePropagation() {
		false
	}
	
	protected def getProducerLevel() {
		return producer.content
	}

//	@Disabled // for now, focus on use cases
//	@BeforeEach
//	protected def final void loadAndRegisterProducer() {
//		val producerResource = resSet.getResource(URI.createURI("resources/FactoryAsProducer.lml"), true)
//		producer = (resSet.getResource(URI.createURI("resources/FactoryAsProducer.lml"), true).contents.get(0) as Domain).deepModel.get(0) as DeepModel
////		resSet.createResource(URI.createURI("model/modelProducer.lml"))
//		viewFactory.changeOwnView[it.registerRoot(producerResource.contents.get(0), getProjectModelPath("producer", "lml").uri)]
//	}
	
	protected def final void loadAndRegisterModels() {
		println("Initial phase started____________________________________________")
		println(
			EOperation.Internal.InvocationDelegate.Factory.Registry.INSTANCE
				.get("http://www.eclipse.org/emf/2002/Ecore/OCL")
		)
		viewFactory = new ProdToSupViewFactory(virtualModel)

		val producerDomain = resSet.getResource(URI.createURI("FactoryAsProducer.lml"), true).contents.filter(Domain).get(0)
		val supporterDomain = resSet.getResource(URI.createURI("FactoryAsModelSupporter.lml"), true).contents.filter(Domain).get(0)

		producer = producerDomain.deepModel.get(0) as DeepModel
		supporter = supporterDomain.deepModel.get(0) as DeepModel

		registerCorrespondenceAuto(producerDomain, PLMPackage.eINSTANCE.domain, "producer")
		registerCorrespondenceAuto(supporterDomain, PLMPackage.eINSTANCE.domain, "supporter")
		registerCorrespondenceAuto(producerDomain, supporterDomain, "") // why not "both" tag?
		registerCorrespondenceAuto(producer, PLMPackage.eINSTANCE.deepModel, "producer")
		registerCorrespondenceAuto(supporter, PLMPackage.eINSTANCE.deepModel, "supporter")
		registerCorrespondenceAuto(producer, supporter, "") // why not "both" tag?

		classNameToEObject.put(producer, new HashMap)
		classNameToEObject.put(supporter, new HashMap)
		registerEObjectWithName(producer)
		registerEObjectWithName(supporter)
		
		for (corr : correspondences.entrySet) {
			registerCorrespondence(corr.key, corr.value)
		}
		
		viewFactory.changeProdView[
			it.registerRoot(producerDomain, getProjectModelPath("producer", "lml").uri)
			it.registerRoot(supporterDomain, getProjectModelPath("supporter", "lml").uri)
		]
		println("Initial phase done______________________________________________")
	}
	
	protected def registerCorrespondence(String producerClassName, String supporterClassName) {
		registerCorrespondenceAuto(findEObjectWithName(producerClassName, producer), PLMPackage.eINSTANCE.entity, "producer")
		registerCorrespondenceAuto(findEObjectWithName(supporterClassName, supporter), PLMPackage.eINSTANCE.entity, "supporter")
		registerCorrespondenceAuto(findEObjectWithName(producerClassName, producer), findEObjectWithName(supporterClassName, supporter), "both")
	}
	
	protected def registerCorrespondenceAuto(EObject left, EObject right, String tag) {
		registerCorrespondence(left, right, tag+"::"+left.class.simpleName+"::"+((left instanceof Domain)?(left as Domain).name:(left as Element).name))
	}
	
	protected def registerCorrespondence(EObject left, EObject right, String tag) {
		val correspondence = (virtualModel as InternalVirtualModel).correspondenceModel.getEditableView(ReactionsCorrespondence, new Supplier<ReactionsCorrespondence>(){
			override get() {
				return CorrespondenceFactory.eINSTANCE.createReactionsCorrespondence();
			}
		}).addCorrespondenceBetween(left, right, tag)
	}	
	
	protected def EObject findEObjectWithName(String name, EObject in) {
		classNameToEObject.get(in.deepModel).get(name)
	}
	
	private def void registerEObjectWithName(EObject in) {
		for(e : in.eContents) {
			if (e instanceof Element) {
				e.deepModel.eResource.URI
				if(classNameToEObject.containsKey(in.deepModel) && e.name !== null) {
					classNameToEObject.get(in.deepModel).put(e.name, e)
				}
			}
			if (e instanceof Element) registerEObjectWithName(e)
		}
	}
	
	def EObject getDeepModel(EObject object) {
		((object as Element).eResource.contents.filter(Domain).get(0) as Domain).deepModel.get(0) as DeepModel
	}
	
//	The @BeforeEach method loads and inserts the producer.lml model into the V-SUM. This methods tests wether the reactions
//  build a corresponding supporter model (without visualizations)
//	@Disabled // for now, prioritize relevant use cases
//	@Test
//	def void evaluateConstructionSimulation() {
//		var resSet = new ResourceSetImpl();
//		supporter = (resSet.getResource(URI.createURI("resources/FactoryAsModelSupporter.lml"), true).contents.get(0) as Domain).deepModel.get(0) as DeepModel
//		val producerResource = resSet.getResource(URI.createURI("resources/FactoryAsProducer.lml"), true)
//		producer = (producerResource.contents.get(0) as Domain).deepModel.get(0) as DeepModel
//		var comparator = EMFCompare.builder().build();
//		var comparison = comparator.compare(new DefaultComparisonScope(producer.eResource, supporter.eResource, producer.eResource))
//		EcoreUtil.resolveAll(producer)
//		EcoreUtil.resolveAll(supporter)
//		println(producer + " " + supporter)
//		println(comparison.differences)
//		Assertions.assertTrue(comparison.differences.isEmpty)
//	}

	protected def Path getProjectModelPath(String modelName, String modelFileExtension) {
		Path.of("folder").resolve(modelName + "." + modelFileExtension)
	}

	override protected getChangePropagationSpecifications() {
		// Not possible to add both because there is a check on sourceMetamodel==sourceMetamodel & targetMetamodel==targetMetamodel -> throw Exception
		return #[new Prod2supChangePropagationSpecification()]
	}

	
}
