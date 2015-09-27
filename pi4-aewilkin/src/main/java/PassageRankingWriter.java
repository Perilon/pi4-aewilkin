import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.impl.XmiCasSerializer;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;
import org.apache.uima.util.XMLSerializer;
import org.xml.sax.SAXException;

import type.Question;
import type.Passage;
import type.InputDocument;
import type.QASet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class PassageRankingWriter extends CasConsumer_ImplBase {
    
  /**
   * Name of configuration parameter that must be set to the path of a directory into which the
   * output files will be written.
   */
  
  public static final String PARAM_OUTPUTDIR = "OutputDir";
  
//  public static final String PARAM_OUTPUTDIR = "src/main/resources/outputData/";
  
  public static final String PARAM_INPUTDIR = "InputDir";
  
//  public static final String PARAM_INPUTDIR = "src/main/resources/inputData/";


  private File mOutputDir;

  private int mDocNum;

  public void initialize() throws ResourceInitializationException {
    mDocNum = 0;
    mOutputDir = new File((String) getConfigParameterValue(PARAM_OUTPUTDIR));
//    mOutputDir = new File("/home/perilon/git/pi4-aewilkin/pi4-aewilkin/src/main/resources/outputData");
    if (!mOutputDir.exists()) {
      mOutputDir.mkdirs();
    }

  }


  public void processCas(CAS aCAS) throws ResourceProcessException {
    
    String modelFileName = null;

    JCas jcas;
    try {
      jcas = aCAS.getJCas();
    } catch (CASException e) {
      throw new ResourceProcessException(e);
    }
    
    String writeToFileName = "passageRanking.txt";

 // retreive the filename of the input file from the CAS
    FSIterator it = jcas.getAnnotationIndex(SourceDocumentInformation.type).iterator();
    File outFile = null;
    if (it.hasNext()) {
      SourceDocumentInformation fileLoc = (SourceDocumentInformation) it.next();
      File inFile;
      try {
        inFile = new File(new URL(fileLoc.getUri()).getPath());

        
        outFile = new File(mOutputDir, writeToFileName);
        modelFileName = mOutputDir.getAbsolutePath() + "/" + inFile.getName() + ".ecore";
      } catch (MalformedURLException e1) {
        // invalid URL, use default processing below
      }
    }
    
    
    if (outFile == null) {
      outFile = new File(mOutputDir, "doc" + mDocNum++);
    }
    
          
     String filenameString = (String) getConfigParameterValue(PARAM_OUTPUTDIR) + writeToFileName;
     
     System.out.println("filenameString = " + filenameString);
     
//     creates "nullpassageRanking.txt" and saves to pi4-aewilkin
     
//     --------------------------------------------------------------------------------------------

     
     FSIndex inputDocumentIndex = jcas.getAnnotationIndex(InputDocument.type);
     
     FSIterator inputDocumentIter = inputDocumentIndex.iterator();
     
     
     while (inputDocumentIter.hasNext()) {
     
       try {
         File file = new File(filenameString);
    
          // if file doesn't exist, then create it
          if (!file.exists()) {
            System.out.println("!file.exists");
            file.createNewFile();
            System.out.println("file created");
          }
    
          FileWriter fw = new FileWriter(file.getAbsoluteFile());
          BufferedWriter bw = new BufferedWriter(fw);
            
            InputDocument inputDocument = new InputDocument(jcas);
            inputDocument = (InputDocument) inputDocumentIter.next();
            
            FSIndex QASetIndex = jcas.getAnnotationIndex(QASet.type);
            FSIterator QASetIter = QASetIndex.iterator();
            
            while (QASetIter.hasNext()) {
              
              QASet qaSet = (QASet) QASetIter.next();
              
              FSArray passageFSArray = qaSet.getPassageFSArray();
              
              int len = passageFSArray.size();
              
              for (int i = 0; i < len; i++) {
                
                String Id = ((Passage) passageFSArray.get(i)).getId();
                String docID = ((Passage) passageFSArray.get(i)).getSourceDocId();
                String score = Double.toString(((Passage) passageFSArray.get(i)).getScore());
                String sentence = ((Passage) passageFSArray.get(i)).getSentence();
                
                bw.write( Id + " " + docID + " " + score + " " + sentence + "\n");
//                System.out.println( Id + " " + docID + " " + score + " " + sentence );
                
              }
            }
            bw.close();
            System.out.println("Done");
       }
          
      catch (IOException e) {
        e.printStackTrace();
      }
     }
  }
}
