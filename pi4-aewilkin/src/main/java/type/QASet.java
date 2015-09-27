

/* First created by JCasGen Sat Sep 26 17:18:45 EDT 2015 */
package type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



import org.apache.uima.jcas.cas.FSArray;


/** Keeps track of which questions go with which answers
 * Updated by JCasGen Sat Sep 26 20:57:18 EDT 2015
 * XML source: /home/perilon/git/pi4-aewilkin/pi4-aewilkin/src/main/resources/descriptors/aaeDescriptor.xml
 * @generated */
public class QASet extends ComponentAnnotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(QASet.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected QASet() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public QASet(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public QASet(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public QASet(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
  //*--------------*
  //* Feature: Question

  /** getter for Question - gets The question in a QASet
   * @generated
   * @return value of the feature 
   */
  public Question getQuestion() {
    if (QASet_Type.featOkTst && ((QASet_Type)jcasType).casFeat_Question == null)
      jcasType.jcas.throwFeatMissing("Question", "type.QASet");
    return (Question)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((QASet_Type)jcasType).casFeatCode_Question)));}
    
  /** setter for Question - sets The question in a QASet 
   * @generated
   * @param v value to set into the feature 
   */
  public void setQuestion(Question v) {
    if (QASet_Type.featOkTst && ((QASet_Type)jcasType).casFeat_Question == null)
      jcasType.jcas.throwFeatMissing("Question", "type.QASet");
    jcasType.ll_cas.ll_setRefValue(addr, ((QASet_Type)jcasType).casFeatCode_Question, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: PassageFSArray

  /** getter for PassageFSArray - gets An FSArray of the passages purporting to answer a question in a QASet
   * @generated
   * @return value of the feature 
   */
  public FSArray getPassageFSArray() {
    if (QASet_Type.featOkTst && ((QASet_Type)jcasType).casFeat_PassageFSArray == null)
      jcasType.jcas.throwFeatMissing("PassageFSArray", "type.QASet");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((QASet_Type)jcasType).casFeatCode_PassageFSArray)));}
    
  /** setter for PassageFSArray - sets An FSArray of the passages purporting to answer a question in a QASet 
   * @generated
   * @param v value to set into the feature 
   */
  public void setPassageFSArray(FSArray v) {
    if (QASet_Type.featOkTst && ((QASet_Type)jcasType).casFeat_PassageFSArray == null)
      jcasType.jcas.throwFeatMissing("PassageFSArray", "type.QASet");
    jcasType.ll_cas.ll_setRefValue(addr, ((QASet_Type)jcasType).casFeatCode_PassageFSArray, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for PassageFSArray - gets an indexed value - An FSArray of the passages purporting to answer a question in a QASet
   * @generated
   * @param i index in the array to get
   * @return value of the element at index i 
   */
  public Passage getPassageFSArray(int i) {
    if (QASet_Type.featOkTst && ((QASet_Type)jcasType).casFeat_PassageFSArray == null)
      jcasType.jcas.throwFeatMissing("PassageFSArray", "type.QASet");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((QASet_Type)jcasType).casFeatCode_PassageFSArray), i);
    return (Passage)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((QASet_Type)jcasType).casFeatCode_PassageFSArray), i)));}

  /** indexed setter for PassageFSArray - sets an indexed value - An FSArray of the passages purporting to answer a question in a QASet
   * @generated
   * @param i index in the array to set
   * @param v value to set into the array 
   */
  public void setPassageFSArray(int i, Passage v) { 
    if (QASet_Type.featOkTst && ((QASet_Type)jcasType).casFeat_PassageFSArray == null)
      jcasType.jcas.throwFeatMissing("PassageFSArray", "type.QASet");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((QASet_Type)jcasType).casFeatCode_PassageFSArray), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((QASet_Type)jcasType).casFeatCode_PassageFSArray), i, jcasType.ll_cas.ll_getFSRef(v));}
  }

    