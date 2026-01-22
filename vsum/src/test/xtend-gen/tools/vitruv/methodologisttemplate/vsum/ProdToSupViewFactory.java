package tools.vitruv.methodologisttemplate.vsum;

import java.util.function.Consumer;
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import tools.vitruv.framework.testutils.integration.TestViewFactory;
import tools.vitruv.framework.views.CommittableView;
import tools.vitruv.framework.views.View;
import tools.vitruv.framework.vsum.VirtualModel;

/* @FinalFieldsConstructor */@SuppressWarnings("all")
public class ProdToSupViewFactory extends TestViewFactory {
  public ProdToSupViewFactory(final VirtualModel model) {
    super(model);
  }

  private View createProdView() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field Domain is undefined");
  }

  private View createSupView() {
    return null;
  }

  public void changeProdView(final Procedure1<? super View> modelModification) {
    try {
      this.changeViewRecordingChanges(this.createProdView(), new Consumer<CommittableView>() {
          public void accept(CommittableView t) {
            modelModification.apply(t);
          }
      });
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  public void changePcmView(final Procedure1<? super View> modelModification) {
    try {
      this.changeViewRecordingChanges(this.createSupView(), new Consumer<CommittableView>() {
          public void accept(CommittableView t) {
            modelModification.apply(t);
          }
      });
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  public void validateProdView(final Procedure1<? super View> viewValidation) {
    try {
      this.validateView(this.createProdView(), new Consumer<View>() {
          public void accept(View t) {
            viewValidation.apply(t);
          }
      });
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  public void validatePcmView(final Procedure1<? super View> viewValidation) {
    try {
      this.validateView(this.createSupView(), new Consumer<View>() {
          public void accept(View t) {
            viewValidation.apply(t);
          }
      });
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
