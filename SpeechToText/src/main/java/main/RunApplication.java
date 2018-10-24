package main;

import java.io.IOException;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.result.WordResult;

public class RunApplication {

	public static void main(String[] args) {
		Configuration configuration = new Configuration();

        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
        configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
        
        LiveSpeechRecognizer recognizer;
        VoiceManager vm;
        Voice v;
		try {
			recognizer = new LiveSpeechRecognizer(configuration);
			recognizer.startRecognition(true);
			
			vm = VoiceManager.getInstance();
			v = vm.getVoice("kevin16");
			v.allocate();
			v.speak("I am happy to participate in the HACKATHON");
			
			SpeechResult result = recognizer.getResult();
			
			for (WordResult r : result.getWords()) {
			    System.out.println(r);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
