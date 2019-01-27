import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.result.WordResult;

/**
 * Created By: aj
 */
public class Transcriber {

    public static void main(String[] args) throws Exception {

    Configuration configuration = new Configuration();
    configuration
            .setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
    configuration
            .setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
    configuration
            .setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");

        LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);
        //try {
        // Start recognition process pruning previously cached data.
            boolean isRunning = true;
        recognizer.startRecognition(isRunning);

         while (isRunning) {
             SpeechResult result = recognizer.getResult();
             for (WordResult r : result.getWords()) {
                 System.out.println(r.getWord());
                 if (r.getWord().toString().contentEquals("exit")) {
                        isRunning=false;
                 }
             }

         }

//        } catch (Exception e) {
//
//        } finally {
//            recognizer.stopRecognition();
//        }

         // Pause recognition process. It can be resumed then with startRecognition(false).
    }

}
