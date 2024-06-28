import javax.sound.sampled.*;
import java.util.HashMap;

// this class will handle the logic for our GUI
public class MorseCodeController {
    // we'll use a hashmap to translate user input into morse code
    // a hashmap is a data structure that stores key/value pairs
    // in this case, we'll use the letter as the key and the corresponding morse code as the value
    // this way, we can easily look up the morse code for any given letter by using the letter as the key

    // here I am declaring a hashmap to have a key of type "Character" with a value of type "String"
    private HashMap<Character, String> morseCodeMap;

    public MorseCodeController(){
        morseCodeMap = new HashMap<>();

        // uppercase
        morseCodeMap.put('A', ".-");
        morseCodeMap.put('B', "-...");
        morseCodeMap.put('C', "-.-.");
        morseCodeMap.put('D', "-..");
        morseCodeMap.put('E', ".");
        morseCodeMap.put('F', "..-.");
        morseCodeMap.put('G', "--.");
        morseCodeMap.put('H', "....");
        morseCodeMap.put('I', "..");
        morseCodeMap.put('J', ".---");
        morseCodeMap.put('K', "-.-");
        morseCodeMap.put('L', ".-..");
        morseCodeMap.put('M', "--");
        morseCodeMap.put('N', "-.");
        morseCodeMap.put('O', "---");
        morseCodeMap.put('P', ".--.");
        morseCodeMap.put('Q', "--.-");
        morseCodeMap.put('R', ".-.");
        morseCodeMap.put('S', "...");
        morseCodeMap.put('T', "-");
        morseCodeMap.put('U', "..-");
        morseCodeMap.put('V', "...-");
        morseCodeMap.put('W', ".--");
        morseCodeMap.put('X', "-..-");
        morseCodeMap.put('Y', "-.--");
        morseCodeMap.put('Z', "--..");

        // lowercase
        morseCodeMap.put('a', ".-");
        morseCodeMap.put('b', "-...");
        morseCodeMap.put('c', "-.-.");
        morseCodeMap.put('d', "-..");
        morseCodeMap.put('e', ".");
        morseCodeMap.put('f', "..-.");
        morseCodeMap.put('g', "--.");
        morseCodeMap.put('h', "....");
        morseCodeMap.put('i', "..");
        morseCodeMap.put('j', ".---");
        morseCodeMap.put('k', "-.-");
        morseCodeMap.put('l', ".-..");
        morseCodeMap.put('m', "--");
        morseCodeMap.put('n', "-.");
        morseCodeMap.put('o', "---");
        morseCodeMap.put('p', ".--.");
        morseCodeMap.put('q', "--.-");
        morseCodeMap.put('r', ".-.");
        morseCodeMap.put('s', "...");
        morseCodeMap.put('t', "-");
        morseCodeMap.put('u', "..-");
        morseCodeMap.put('v', "...-");
        morseCodeMap.put('w', ".--");
        morseCodeMap.put('x', "-..-");
        morseCodeMap.put('y', "-.--");
        morseCodeMap.put('z', "--..");

        // numbers
        morseCodeMap.put('0', "-----");
        morseCodeMap.put('1', ".----");
        morseCodeMap.put('2', "..---");
        morseCodeMap.put('3', "...--");
        morseCodeMap.put('4', "....-");
        morseCodeMap.put('5', ".....");
        morseCodeMap.put('6', "-....");
        morseCodeMap.put('7', "--...");
        morseCodeMap.put('8', "---..");
        morseCodeMap.put('9', "----.");

        // special characters
        morseCodeMap.put(' ', "/");
        morseCodeMap.put(',', "--..--");
        morseCodeMap.put('.', ".-.-.-");
        morseCodeMap.put('?', "..--..");
        morseCodeMap.put(';', "-.-.-.");
        morseCodeMap.put(':', "---...");
        morseCodeMap.put('(', "-.--.");
        morseCodeMap.put(')', "-.--.-");
        morseCodeMap.put('[', "-.--.");
        morseCodeMap.put(']', "-.--.-");
        morseCodeMap.put('{', "-.--.");
        morseCodeMap.put('}', "-.--.-");
        morseCodeMap.put('+', ".-.-.");
        morseCodeMap.put('-', "-....-");
        morseCodeMap.put('_', "..--.-");
        morseCodeMap.put('"', ".-..-.");
        morseCodeMap.put('\'', ".----.");
        morseCodeMap.put('/', "-..-.");
        morseCodeMap.put('\\', "-..-.");
        morseCodeMap.put('@', ".--.-.");
        morseCodeMap.put('=', "-...-");
        morseCodeMap.put('!', "-.-.--");
    }

    public String translateToMorse(String textToTranslate){
        StringBuilder translatedText = new StringBuilder();
        for(Character letter : textToTranslate.toCharArray()){
            // translate the letter and then append to the returning value (to be displayed to the GUI)
            translatedText.append(morseCodeMap.get(letter) + " ");
        }
        return translatedText.toString();
    }

    // morseMessage - contains the morse message after being translated
    public void playSound(String[] morseMessage) throws LineUnavailableException, InterruptedException {
        // audio format specifies the audio properties (i.e. the type of sound we want)
        AudioFormat audioFormat = new AudioFormat(44100, 16, 1, true, false);

        // create the data line (to play incoming audio data)
        DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);
        SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
        sourceDataLine.open(audioFormat);
        sourceDataLine.start();

        // duration of the sound to be played (I just messed around with the values to get it close enough)
        int dotDuration = 200;
        int dashDuration = (int) (1.5 * dotDuration);
        int slashDuration = 2 * dashDuration;

        for(String pattern : morseMessage){
            System.out.println(pattern);

            // play the letter sound
            for(char c : pattern.toCharArray()){
                if(c == '.'){
                    playBeep(sourceDataLine, dotDuration);
                    Thread.sleep(dotDuration);
                }else if(c == '-'){
                    playBeep(sourceDataLine, dashDuration);
                    Thread.sleep(dotDuration);
                }else if(c == '/'){
                    Thread.sleep(slashDuration);
                }
            }

            // waits a bit before playing the next sequence
            Thread.sleep(dotDuration);
        }

        // close audio output line (cleans up resources)
        sourceDataLine.drain();
        sourceDataLine.stop();
        sourceDataLine.close();
    }

    // sends audio data to be played to the data line
    private void playBeep(SourceDataLine line, int duration){
        // create audio data
        byte[] data = new byte[duration * 44100 / 1000];

        for(int i = 0; i < data.length; i++){
            // calculates the angle of the sine wave for the current sample based on the sample rate and frequency
            double angle = i / (44100.0/440) * 2.0 * Math.PI;

            // calculates the amplitude of the sine wave at the current angle and scale it to fit within the range of
            // a signed byte (-128, 127)
            // also in the context of audio processing, a signed bytes is often used to represent audio data because it
            // can represent both positive and negative amplitudes of sound waves
            data[i] = (byte) (Math.sin(angle) * 127.0);
        }

        // write the audio dat in the data line to be played
        line.write(data, 0, data.length);
    }
}


















