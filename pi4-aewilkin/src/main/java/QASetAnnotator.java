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
    
    while (questionIter.hasNext()) {
      Question question = (Question) questionIter.next();
      String qID = question.getId();
      String questionSentence = question.getSentence();
      
      QAIDs.put(qID, questionSentence);
    }
    
    
    
    Iterator questionIter2 = questionIndex.iterator();
    
    while (questionIter2.hasNext()) {
      QASet annotation = new QASet(aJCas);
      
      Question question = (Question) questionIter2.next();
      
      annotation.setBegin(question.getBegin());
      annotation.setEnd(question.getEnd());
      
      annotation.setQuestion(question);
      
//      FSArray passageFSArray = new FSArray(aJCas);
      
      ArrayList<Passage> passageArrayList = new ArrayList<Passage>();
      
      Iterator passageIter = passageIndex.iterator();
      while (passageIter.hasNext()) {
        Passage passage = (Passage) passageIter.next();
        if (QAIDs.get(passage.getId()) != null) {
          passageArrayList.add(passage);          
        }        
      }
      
      int len = passageArrayList.size();
      
      FSArray passageFSArray = new FSArray(aJCas, len);
      
      for (int i = 0; i < len; i++) {
        Passage p = (Passage) passageArrayList.get(i);
        passageFSArray.set(i, p);
      }
      
      annotation.setPassageFSArray(passageFSArray);
      
      annotation.addToIndexes();
      
    }
    
    FSIndex QASetIndex = aJCas.getAnnotationIndex(QASet.type);
    Iterator qaIter = QASetIndex.iterator();
    while (qaIter.hasNext()) {
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
      
//      System.out.println(passageFSArray);
      
      int passageFSArrayLen = passageFSArray.size();
      
      for (int i = 0; i < passageFSArrayLen; i++) {
        Passage passage = (Passage) passageFSArray.get(i);
        
//        System.out.println(passage);
        
        int begin = passage.getBegin();
//        System.out.println(Integer.toString(begin));
        int end = passage.getEnd();
//        System.out.println(Integer.toString(end));
        
        List<Token> tokenPassageList = JCasUtil.selectCovered(aJCas, Token.class, passage.getBegin() - 1, passage.getEnd());
        
        int passageListLen = tokenPassageList.size();
        
        String[] tokenPassageStringArray = new String[passageListLen];
        
        for (int j = 0; j < passageListLen; j++) {
          tokenPassageStringArray[j] = tokenPassageList.get(j).getToStringValue();
        }

        int matchesCounter = 0;
        
        for (int k = 0; k < tokenQuestionStringArray.length; k++) {
          for (int L = 0; L < tokenPassageStringArray.length; L++) {
            if (tokenQuestionStringArray[k].equals(tokenPassageStringArray[L])) {
              matchesCounter++;
            }
          }
        }
        
        passage.setScore(matchesCounter / passageListLen);
        passage.addToIndexes();

      }
      qaSet.addToIndexes();
    }
  }
}
