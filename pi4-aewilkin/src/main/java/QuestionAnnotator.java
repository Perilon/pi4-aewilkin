import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

import type.Passage;
import type.Question;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuestionAnnotator extends JCasAnnotator_ImplBase {

  private Pattern mQuestionPattern = 

//          Pattern.compile("(Q)([ \t]+)(.*)\\?");
          
          Pattern.compile("([0-9]{4})[\\s]+(QUESTION)[\\s]+(.*)([\\?]?)");
          
//          Pattern.compile("((0-9))(.*)(?)");
  
  public void process(JCas aJCas) {
    
    String docText = aJCas.getDocumentText(); 
    
    Matcher matcher = mQuestionPattern.matcher(docText);
    int pos = 0;
    while (matcher.find(pos)) {
      // match found - create the match as annotation in 
      // the JCas with some additional meta information
      Question annotation = new Question(aJCas);
      annotation.setBegin(matcher.start(3));
      annotation.setEnd(matcher.end(3));
      
      annotation.setId(matcher.group(1));
      
      String questionText = matcher.group(3);
      
      /*      Removes most html tags, then all non-letter and non-digit and non-space characters from the string representing the question*/
      
//      questionText.replaceAll("</?TEXT>", "");
//      questionText.replaceAll("</?P>", "");
//      
//      StringBuilder builder = new StringBuilder();
//     
//      for (char c : questionText.toCharArray()) {
//        if (Character.isLetterOrDigit(c) || c == ' ') {
//          builder.append(Character.isLowerCase(c) ? c: Character.toLowerCase(c));
//        }
//      }
//      
//      String finalSentence = builder.toString();
      
      annotation.setSentence(questionText);
      
      annotation.addToIndexes();
      
      pos = matcher.end();
    }
      
  } 
  
}

//2370 QUESTION  When was the first potato chip made?
//2371 QUESTION  What type of bee drills holes in wood?
//2374 QUESTION  What is the tallest statue in the world?