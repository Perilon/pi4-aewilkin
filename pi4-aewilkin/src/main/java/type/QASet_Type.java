
/* First created by JCasGen Sat Sep 26 17:18:45 EDT 2015 */
package type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;

import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;

/** Keeps track of which questions go with which answers
 * Updated by JCasGen Sat Sep 26 18:11:59 EDT 2015
 * @generated */
public class QASet_Type extends ComponentAnnotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (QASet_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = QASet_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new QASet(addr, QASet_Type.this);
  			   QASet_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new QASet(addr, QASet_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = QASet.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("type.QASet");



  /** @generated */
  final Feature casFeat_Question;
  /** @generated */
  final int     casFeatCode_Question;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getQuestion(int addr) {
        if (featOkTst && casFeat_Question == null)
      jcas.throwFeatMissing("Question", "type.QASet");
    return ll_cas.ll_getRefValue(addr, casFeatCode_Question);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setQuestion(int addr, int v) {
        if (featOkTst && casFeat_Question == null)
      jcas.throwFeatMissing("Question", "type.QASet");
    ll_cas.ll_setRefValue(addr, casFeatCode_Question, v);}
    
  
 
  /** @generated */
  final Feature casFeat_PassageFSArray;
  /** @generated */
  final int     casFeatCode_PassageFSArray;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getPassageFSArray(int addr) {
        if (featOkTst && casFeat_PassageFSArray == null)
      jcas.throwFeatMissing("PassageFSArray", "type.QASet");
    return ll_cas.ll_getRefValue(addr, casFeatCode_PassageFSArray);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setPassageFSArray(int addr, int v) {
        if (featOkTst && casFeat_PassageFSArray == null)
      jcas.throwFeatMissing("PassageFSArray", "type.QASet");
    ll_cas.ll_setRefValue(addr, casFeatCode_PassageFSArray, v);}
    
   /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @return value at index i in the array 
   */
  public int getPassageFSArray(int addr, int i) {
        if (featOkTst && casFeat_PassageFSArray == null)
      jcas.throwFeatMissing("PassageFSArray", "type.QASet");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_PassageFSArray), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_PassageFSArray), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_PassageFSArray), i);
  }
   
  /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @param v value to set
   */ 
  public void setPassageFSArray(int addr, int i, int v) {
        if (featOkTst && casFeat_PassageFSArray == null)
      jcas.throwFeatMissing("PassageFSArray", "type.QASet");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_PassageFSArray), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_PassageFSArray), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_PassageFSArray), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public QASet_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_Question = jcas.getRequiredFeatureDE(casType, "Question", "type.Question", featOkTst);
    casFeatCode_Question  = (null == casFeat_Question) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Question).getCode();

 
    casFeat_PassageFSArray = jcas.getRequiredFeatureDE(casType, "PassageFSArray", "uima.cas.FSArray", featOkTst);
    casFeatCode_PassageFSArray  = (null == casFeat_PassageFSArray) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_PassageFSArray).getCode();

  }
}



    