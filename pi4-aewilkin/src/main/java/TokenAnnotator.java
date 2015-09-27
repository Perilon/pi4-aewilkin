import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import type.Passage;
import type.Question;
import type.Token;


public class TokenAnnotator extends JCasAnnotator_ImplBase {
  
  private Pattern mTokenPattern = 
          Pattern.compile("([\\S]+)");

  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    

    FSIndex answerIndex = aJCas.getAnnotationIndex(Passage.type);
    FSIndex questionIndex = aJCas.getAnnotationIndex(Question.type);

    
    Iterator answerIter = answerIndex.iterator();
    
    while (answerIter.hasNext()) {
      Passage answer = (Passage) answerIter.next();
      
      Matcher matcher = mTokenPattern.matcher(answer.getSentence());
      int pos = 0;
      while (matcher.find(pos)) {
//        System.out.println(matcher);
        Token token = new Token(aJCas);
        token.setBegin(answer.getBegin() + matcher.start(1));
        token.setEnd(answer.getBegin() + matcher.end(1));
        token.setToStringValue(matcher.group(1));
        token.addToIndexes();
        pos = matcher.end();
      }
    }
    
    Iterator questionIter = questionIndex.iterator();
    
    while (questionIter.hasNext()) {
      Question question = (Question) questionIter.next();
      
      Matcher matcher = mTokenPattern.matcher(question.getSentence());
      int pos = 0;
      while (matcher.find(pos)) {
//        System.out.println(matcher);
        Token token = new Token(aJCas);
        token.setBegin(question.getBegin() + matcher.start(1));
        token.setEnd(question.getBegin() + matcher.end(1));
        token.setToStringValue(matcher.group(1));
        token.addToIndexes();
        pos = matcher.end();
      }
    }
  }
}

