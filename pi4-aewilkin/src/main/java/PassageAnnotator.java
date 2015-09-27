import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

import type.Passage;
import type.Question;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PassageAnnotator extends JCasAnnotator_ImplBase {

  private Pattern mPassagePattern = 

//          Pattern.compile("(Q)([ \t]+)(.*)\\?");
          
//          Pattern.compile("([0-9]{4})[\\s]+(QUESTION)[\\s]+(.*)");
          
          Pattern.compile("([0-9]{4})[\\s]+(.*[0-9]{4})[\\s](-?[12])[\\s]+(.*)");
  
  public void process(JCas aJCas) {
    
    String docText = aJCas.getDocumentText(); 
    
    Matcher matcher = mPassagePattern.matcher(docText);
    int pos = 0;
    while (matcher.find(pos)) {
      // match found - create the match as annotation in 
      // the JCas with some additional meta information
      Passage annotation = new Passage(aJCas);
      annotation.setBegin(matcher.start(4));
      annotation.setEnd(matcher.end(4));
      
      /*Set the ID of the passage, corresponding to the question ID*/
      
      annotation.setId(matcher.group(1));
      
      /*Set the document ID of the passage*/
      
      annotation.setSourceDocId(matcher.group(2));
      
      /*Set the label (true or false) of the passage*/
      
      if (matcher.group(3).equals("-1")) {
        annotation.setLabel(false);
      } else if (matcher.group(3).equals("1")) {
        annotation.setLabel(true);
      } else if (matcher.group(4).equals("2")) {
        annotation.setLabel(true);
      }
      
      String passageText = matcher.group(4);
      
      /*      Removes most html tags, then all non-letter and non-digit characters from the string representing the passage*/
      
//      passageText.replaceAll("</?TEXT>", "");
//      passageText.replaceAll("</?P>", "");
//      
//      StringBuilder builder = new StringBuilder();
//     
//      for (char c : passageText.toCharArray()) {
//        if (Character.isLetterOrDigit(c) || c == ' ') {
//          builder.append(Character.isLowerCase(c) ? c: Character.toLowerCase(c));
//        }
//      }
//      
//      String finalSentence = builder.toString();
      annotation.setSentence(passageText);
      
      
      annotation.addToIndexes();
      pos = matcher.end();
    }
      
  } 
  
}

//2370 QUESTION  When was the first potato chip made?
//2371 QUESTION  What type of bee drills holes in wood?
//2374 QUESTION  What is the tallest statue in the world?