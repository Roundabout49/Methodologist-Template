package tools.vitruv.methodologisttemplate.vsum;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.ecore.delegate.OCLDelegateDomain;
import org.eclipse.ocl.ecore.delegate.OCLInvocationDelegateFactory;
import org.eclipse.ocl.ecore.delegate.OCLSettingDelegateFactory;
import org.eclipse.ocl.ecore.delegate.OCLValidationDelegateFactory;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.junit.jupiter.api.BeforeAll;
import tools.vitruv.change.propagation.ChangePropagationSpecification;
import tools.vitruv.framework.testutils.integration.ViewBasedVitruvApplicationTest;

@SuppressWarnings("all")
public class ProdToSupVitruvApplicationTest extends ViewBasedVitruvApplicationTest {
  @Extension
  protected ProdToSupViewFactory viewFactory;

  @Extension
  protected /* DeepModel */Object producer;

  @Extension
  protected /* DeepModel */Object supporter;

  @Extension
  protected final ResourceSet resSet = new ResourceSetImpl();

  private final Map<EObject, Map<String, EObject>> classNameToEObject = new HashMap<EObject, Map<String, EObject>>();

  private static final Map<String, String> correspondences = new HashMap<String, String>();

  @BeforeAll
  public static void initOCL() {
    OCL.initialize(null);
    OCLDelegateDomain.initialize(null);
    OCLInvocationDelegateFactory _oCLInvocationDelegateFactory = new OCLInvocationDelegateFactory();
    EOperation.Internal.InvocationDelegate.Factory.Registry.INSTANCE.put(
      "http://www.eclipse.org/emf/2002/Ecore/OCL", _oCLInvocationDelegateFactory);
    OCLSettingDelegateFactory _oCLSettingDelegateFactory = new OCLSettingDelegateFactory();
    EStructuralFeature.Internal.SettingDelegate.Factory.Registry.INSTANCE.put(
      "http://www.eclipse.org/emf/2002/Ecore/OCL", _oCLSettingDelegateFactory);
    OCLValidationDelegateFactory _oCLValidationDelegateFactory = new OCLValidationDelegateFactory();
    EValidator.ValidationDelegate.Registry.INSTANCE.put(
      "http://www.eclipse.org/emf/2002/Ecore/OCL", _oCLValidationDelegateFactory);
    InputOutput.<String>println("OCL Delegates initialized:");
    InputOutput.<Object>println(
      EOperation.Internal.InvocationDelegate.Factory.Registry.INSTANCE.get("http://www.eclipse.org/emf/2002/Ecore/OCL"));
  }

  @BeforeAll
  public static void setupFactories() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field PLMPackage is undefined"
      + "\neINSTANCE cannot be resolved");
  }

  @BeforeAll
  public static void prepareCorrespondences() {
    ProdToSupVitruvApplicationTest.correspondences.put("DeviceModel", "DeviceModel");
    ProdToSupVitruvApplicationTest.correspondences.put("MobilePhoneModel", "MobilePhoneModel");
    ProdToSupVitruvApplicationTest.correspondences.put("Company", "Company");
    ProdToSupVitruvApplicationTest.correspondences.put("Factory", "Factory");
    ProdToSupVitruvApplicationTest.correspondences.put("MobilePhoneFactory", "MobileFactory");
    ProdToSupVitruvApplicationTest.correspondences.put("HuaweiMobilePhoneFactory", "HuaweiMobilePhoneFactory");
    ProdToSupVitruvApplicationTest.correspondences.put("HuaweiMobilePhoneModel", "HuaweiMobilePhoneModel");
    ProdToSupVitruvApplicationTest.correspondences.put("Device", "Device");
    ProdToSupVitruvApplicationTest.correspondences.put("MobilePhoneDevice", "MobilePhoneDevice");
    ProdToSupVitruvApplicationTest.correspondences.put("HuaweiMobilePhoneDevice", "HuaweiMobilePhoneDevice");
    ProdToSupVitruvApplicationTest.correspondences.put("Huawei", "Huawei");
    ProdToSupVitruvApplicationTest.correspondences.put("Factory124", "Factory124");
    ProdToSupVitruvApplicationTest.correspondences.put("S400", "S400");
    ProdToSupVitruvApplicationTest.correspondences.put("S500", "S500");
    ProdToSupVitruvApplicationTest.correspondences.put("S400_001", "S400_001");
    ProdToSupVitruvApplicationTest.correspondences.put("S400_002", "S400_002");
  }

  @Override
  protected boolean enableTransitiveCyclicChangePropagation() {
    return false;
  }

  protected Object getProducerLevel() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe field ProdToSupVitruvApplicationTest.producer refers to the missing type DeepModel"
      + "\ncontent cannot be resolved");
  }

  protected final void loadAndRegisterModels() {
    throw new Error("Unresolved compilation problems:"
      + "\nDeepModel cannot be resolved to a type."
      + "\nDeepModel cannot be resolved to a type."
      + "\nThe method or field Domain is undefined"
      + "\nThe method or field Domain is undefined"
      + "\nThe method get(int) is undefined for the type EObject"
      + "\nThe method get(int) is undefined for the type EObject"
      + "\nThe method or field PLMPackage is undefined"
      + "\nThe method or field PLMPackage is undefined"
      + "\nThe method or field PLMPackage is undefined"
      + "\nThe method or field PLMPackage is undefined"
      + "\nThe field ProdToSupVitruvApplicationTest.producer refers to the missing type DeepModel"
      + "\nThe field ProdToSupVitruvApplicationTest.supporter refers to the missing type DeepModel"
      + "\nThe field ProdToSupVitruvApplicationTest.producer refers to the missing type DeepModel"
      + "\nThe field ProdToSupVitruvApplicationTest.supporter refers to the missing type DeepModel"
      + "\nThe field ProdToSupVitruvApplicationTest.producer refers to the missing type DeepModel"
      + "\nThe field ProdToSupVitruvApplicationTest.supporter refers to the missing type DeepModel"
      + "\nThe field ProdToSupVitruvApplicationTest.producer refers to the missing type DeepModel"
      + "\nThe field ProdToSupVitruvApplicationTest.supporter refers to the missing type DeepModel"
      + "\nThe field ProdToSupVitruvApplicationTest.producer refers to the missing type DeepModel"
      + "\nThe field ProdToSupVitruvApplicationTest.supporter refers to the missing type DeepModel"
      + "\neINSTANCE cannot be resolved"
      + "\ndomain cannot be resolved"
      + "\neINSTANCE cannot be resolved"
      + "\ndomain cannot be resolved"
      + "\neINSTANCE cannot be resolved"
      + "\ndeepModel cannot be resolved"
      + "\neINSTANCE cannot be resolved"
      + "\ndeepModel cannot be resolved");
  }

  protected void registerCorrespondence(final String producerClassName, final String supporterClassName) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field PLMPackage is undefined"
      + "\nThe method or field PLMPackage is undefined"
      + "\nThe field ProdToSupVitruvApplicationTest.producer refers to the missing type DeepModel"
      + "\nThe field ProdToSupVitruvApplicationTest.supporter refers to the missing type DeepModel"
      + "\nThe field ProdToSupVitruvApplicationTest.producer refers to the missing type DeepModel"
      + "\nThe field ProdToSupVitruvApplicationTest.supporter refers to the missing type DeepModel"
      + "\neINSTANCE cannot be resolved"
      + "\nentity cannot be resolved"
      + "\neINSTANCE cannot be resolved"
      + "\nentity cannot be resolved");
  }

  protected void registerCorrespondenceAuto(final EObject left, final EObject right, final String tag) {
    throw new Error("Unresolved compilation problems:"
      + "\nDomain cannot be resolved to a type."
      + "\nDomain cannot be resolved to a type."
      + "\nElement cannot be resolved to a type."
      + "\nname cannot be resolved"
      + "\nname cannot be resolved");
  }

  protected void registerCorrespondence(final EObject left, final EObject right, final String tag) {
    throw new Error("Unresolved compilation problems:"
      + "\nReactionsCorrespondence cannot be resolved to a type."
      + "\nThe method or field ReactionsCorrespondence is undefined"
      + "\nThe method or field CorrespondenceFactory is undefined"
      + "\neINSTANCE cannot be resolved"
      + "\ncreateReactionsCorrespondence cannot be resolved");
  }

  protected EObject findEObjectWithName(final String name, final EObject in) {
    return this.classNameToEObject.get(this.getDeepModel(in)).get(name);
  }

  private void registerEObjectWithName(final EObject in) {
    throw new Error("Unresolved compilation problems:"
      + "\nElement cannot be resolved to a type."
      + "\nElement cannot be resolved to a type."
      + "\nThe method or field name is undefined for the type EObject"
      + "\nThe method or field name is undefined for the type EObject"
      + "\n!== cannot be resolved");
  }

  public EObject getDeepModel(final EObject object) {
    throw new Error("Unresolved compilation problems:"
      + "\nElement cannot be resolved to a type."
      + "\nDomain cannot be resolved to a type."
      + "\nDeepModel cannot be resolved to a type."
      + "\nThe method or field Domain is undefined"
      + "\neResource cannot be resolved"
      + "\ncontents cannot be resolved"
      + "\nfilter cannot be resolved"
      + "\nget cannot be resolved"
      + "\ndeepModel cannot be resolved"
      + "\nget cannot be resolved");
  }

  protected Path getProjectModelPath(final String modelName, final String modelFileExtension) {
    return Path.of("folder").resolve(((modelName + ".") + modelFileExtension));
  }

  @Override
  protected Iterable<? extends ChangePropagationSpecification> getChangePropagationSpecifications() {
    throw new Error("Unresolved compilation problems:"
      + "\nProd2supChangePropagationSpecification cannot be resolved.");
  }
}
