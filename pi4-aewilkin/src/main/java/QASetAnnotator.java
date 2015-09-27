import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

//import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
//import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
//import org.apache.uima.cas.FSIndex;
//import org.apache.uima.jcas.JCas;
//import org.apache.uima.jcas.cas.FSArray;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.fit.util.JCasUtil;

import type.QASet;
import type.Passage;
import type.Question;
import type.Token;

public class QASetAnnotator extends JCasAnnotator_ImplBase {

  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {

    FSIndex passageIndex = aJCas.getAnnotationIndex(Passage.type);
    FSIndex questionIndex = aJCas.getAnnotationIndex(Question.type);
    
    HashMap<String, String> QAIDs = new HashMap<String, String>();
    
    Iterator questionIter = questionIndex.iterator();
    
    /*Put the Question IDs into a hashmap for lookup later*/
    
//    while (questionIter.hasNext()) {
//      Question question = (Question) questionIter.next();
//      String qID = question.getId();
//      String questionSentence = question.getSentence();
//      
//      QAIDs.put(qID, questionSentence);
//    }
    
    /*For each question, add to the QASet indexes a QASet that contains the question and its associated answers.*/
    
    Iterator questionIter2 = questionIndex.iterator();
    
    while (questionIter2.hasNext()) {
      QASet annotation = new QASet(aJCas);
      
      Question question = (Question) questionIter2.next();
      
      annotation.setBegin(question.getBegin());
      annotation.setEnd(question.getEnd());
      
      annotation.setQuestion(question);
      
      String QID = question.getId();
     

      /*Put into the Passage FSArray of the QASet the passages whose IDs match each question in turn*/
      
      ArrayList<Passage> passageArrayList = new ArrayList<Passage>();
      
      Iterator passageIter = passageIndex.iterator();
      while (passageIter.hasNext()) {
        Passage passage = (Passage) passageIter.next();
        
//        System.out.println("passage.getId() = " + passage.getId());
//        System.out.println("QID (question.getId()) = " + QID);
        
//        if (QAIDs.get(passage.getId()).equals(QID)) {
          
          if ((passage.getId()).equals(QID)) {
          
//          System.out.println(passage);
          passageArrayList.add(passage);          
        }        
      }
      
      int len = passageArrayList.size();
      
      FSArray passageFSArray = new FSArray(aJCas, len);
      
      for (int i = 0; i < len; i++) {
        Passage p = (Passage) passageArrayList.get(i);
        p.setQuestion(question);
        passageFSArray.set(i, p);
      }
      
      annotation.setPassageFSArray(passageFSArray);
      
      annotation.addToIndexes();      
    }
  }
}







