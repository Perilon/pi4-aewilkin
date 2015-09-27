import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;

import java.util.HashMap;
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
    

    FSIndex passageIndex = aJCas.getAnnotationIndex(Passage.type);
    FSIndex questionIndex = aJCas.getAnnotationIndex(Question.type);

    
    Iterator passageIter = passageIndex.iterator();
    
    while (passageIter.hasNext()) {
      Passage passage = (Passage) passageIter.next();
      
      
//      HashMap<String, Integer> seenTokens = new HashMap<String, Integer>();
      
      
      Matcher matcher = mTokenPattern.matcher(passage.getSentence());
      int pos = 0;
      while (matcher.find(pos)) {
//        System.out.println(matcher);
        Token token = new Token(aJCas);
        
//        String ts = token.getCoveredText();
//                
//        if (seenTokens.get(ts) == null) {
//          seenTokens.put(ts, 1);
//        } else {
//          int val = seenTokens.get(ts);
//          seenTokens.put(ts, (val + 1));
//        }
//        
//        
//        int offset;
        
        
        token.setBegin(passage.getBegin() + matcher.start(1));
        token.setEnd(passage.getBegin() + matcher.end(1));
        
        String ts = token.getCoveredText();
        
        
//        ts.replaceAll("</?TEXT>", "");
//        ts.replaceAll("</?P>", "");
        
        StringBuilder builder = new StringBuilder();
       
        for (char c : ts.toCharArray()) {
          if (Character.isLetterOrDigit(c) || c == ' ') {
            builder.append(Character.isLowerCase(c) ? c: Character.toLowerCase(c));
          }
        }
        
        String finalToken = builder.toString();
        
        
        
        
//        token.setToStringValue(matcher.group(1));
        
        token.setToStringValue(finalToken);
        
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
        
        String ts = token.getCoveredText();
        
        
//      ts.replaceAll("</?TEXT>", "");
//      ts.replaceAll("</?P>", "");
      
        StringBuilder builder = new StringBuilder();
       
        for (char c : ts.toCharArray()) {
          
          /*Get rid of all punctuation here except the angle brackets that set off HTML tags; these, since they
          enclose words that we also want to not count as proper tokens, are left in here so that the whole
          tokens they enclose can be ignored later in InputDocumentAnnotator.*/
          
          if (Character.isLetterOrDigit(c) || c == '<' || c == '>') {
            builder.append(Character.isLowerCase(c) ? c: Character.toLowerCase(c));
          }
        }
        
        String finalToken = builder.toString();
        
        
        
        
  //      token.setToStringValue(matcher.group(1));
        
        token.setToStringValue(finalToken);        
        
//        token.setToStringValue(matcher.group(1));
        token.addToIndexes();
        pos = matcher.end();
      }
    }
  }
}

