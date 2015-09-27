
/* First created by JCasGen Sat Sep 26 16:44:15 EDT 2015 */

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import type.ComponentAnnotation_Type;

/** Tokens.
 * Updated by JCasGen Sat Sep 26 18:08:56 EDT 2015
 * @generated */
public class Token_Type extends ComponentAnnotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Token_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Token_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Token(addr, Token_Type.this);
  			   Token_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Token(addr, Token_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Token.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("Token");
 
  /** @generated */
  final Feature casFeat_toStringValue;
  /** @generated */
  final int     casFeatCode_toStringValue;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getToStringValue(int addr) {
        if (featOkTst && casFeat_toStringValue == null)
      jcas.throwFeatMissing("toStringValue", "Token");
    return ll_cas.ll_getStringValue(addr, casFeatCode_toStringValue);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setToStringValue(int addr, String v) {
        if (featOkTst && casFeat_toStringValue == null)
      jcas.throwFeatMissing("toStringValue", "Token");
    ll_cas.ll_setStringValue(addr, casFeatCode_toStringValue, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Token_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_toStringValue = jcas.getRequiredFeatureDE(casType, "toStringValue", "uima.cas.String", featOkTst);
    casFeatCode_toStringValue  = (null == casFeat_toStringValue) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_toStringValue).getCode();

  }
}



    