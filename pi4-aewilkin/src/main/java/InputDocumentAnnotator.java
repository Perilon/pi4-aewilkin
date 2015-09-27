import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.fit.util.JCasUtil;

import type.Passage;
import type.Question;
import type.Token;
import type.QASet;
import type.InputDocument;

public class InputDocumentAnnotator extends JCasAnnotator_ImplBase {

  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    
    FSIndex passageIndex = aJCas.getAnnotationIndex(Passage.type);
    FSIndex questionIndex = aJCas.getAnnotationIndex(Question.type);
//    FSIndex tokenIndex = aJCas.getAnnotationIndex(Token.type);
    FSIndex inputDocumentIndex = aJCas.getAnnotationIndex(InputDocument.type);   
    
    
    FSIndex QASetIndex = aJCas.getAnnotationIndex(QASet.type);
    
    
    
    InputDocument inputDocument = new InputDocument(aJCas);
    
    
    
    Iterator qaIter = QASetIndex.iterator();
    
    
    while (qaIter.hasNext()) {                    // Error on this line!
      QASet qaSet = (QASet) qaIter.next();
      
      Question question = qaSet.getQuestion();
      
//      Now get the tokens in the question, put their string versions in a string array.
      
      FSIndex tokenIndex = aJCas.getAnnotationIndex(Token.type);
      
      List<Token> tokenQuestionList = JCasUtil.selectCovered(aJCas, Token.class, question.getBegin() - 1, question.getEnd());
      
      int questionListLen = tokenQuestionList.size();
          
      String[] tokenQuestionStringArray = new String[questionListLen];

      for (int i = 0; i < questionListLen; i++) {
        tokenQuestionStringArray[i] = tokenQuestionList.get(i).getToStringValue();
      }
      
//      Now for each answer, get the tokens, put their string versions in a string array, and calculate the score.
      
      FSArray passageFSArray = qaSet.getPassageFSArray();
      
      
      int passageFSArrayLen = passageFSArray.size();
      
      for (int i = 0; i < passageFSArrayLen; i++) {
        Passage passage = (Passage) passageFSArray.get(i);
        
//        System.out.println(passage);
        
        int begin = passage.getBegin();
        int end = passage.getEnd();
        
        List<Token> tokenPassageList = JCasUtil.selectCovered(aJCas, Token.class, passage.getBegin() - 1, passage.getEnd());
        
        int passageListLen = tokenPassageList.size();
        
        String[] tokenPassageStringArray = new String[passageListLen];
        
        for (int j = 0; j < passageListLen; j++) {
          tokenPassageStringArray[j] = tokenPassageList.get(j).getToStringValue();
        }

        int matchesCounter = 0;
        
        for (int k = 0; k < tokenQuestionStringArray.length; k++) {
//          System.out.println(tokenQuestionStringArray[k]);
          for (int L = 0; L < tokenPassageStringArray.length; L++) {
//            System.out.println(tokenPassageStringArray[k]);
            if (tokenQuestionStringArray[k].equals(tokenPassageStringArray[L])) {
              matchesCounter++;
            }
          }
        }
        
        passage.setScore(matchesCounter / passageListLen);
        passage.addToIndexes();
        
//        System.out.println(passage);

      }
      
      inputDocument.addToIndexes();
      
    }
  }
}
