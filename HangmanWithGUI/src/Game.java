import java.util.Arrays;
import java.util.Random;

public class Game {
    String[] wordList = {"LISA", "HALLO", "BÄR", "ARMEISENHAUFEN", "ROHRBRUCH", "HIMMELFAHRTSKOMMANDO", "SCHWEISSDRÜSEN"};
    String wordForGame;
    char[] wordToGuess;
    char[] wrongLetterArray = {0,0,0,0,0,0,0,0,0,0};
    char[] fillInWord;
    String exception = "";
    int wrongLetterCounter;
    boolean isFound;
    boolean isWon;

    public Game(){

    }
    public void clearGame(){
        isWon = false;
        Arrays.fill(wrongLetterArray, (char) 0);
        exception = "";
    }

    public String menuText(){
        return  "I think about a word with " + wordForGame.length() +" letters!\n"+
                "Your word: " + this.getFillInWordAsString() + "\n"+
                "You already guessed: "+ this.getWrongCharacters() +"\n"+
                "Please insert your next letter in the textfield!";
    }

    /**
     * starts the game and handles the input
     */
    public void update(String alpha) {
        //checks the input
        alpha = alpha.toUpperCase();
        char input;

        if(alpha.length()>1)
        {
            this.exception = "Just one letter at a time!";
            return;
        }
        input = Character.toUpperCase(alpha.charAt(0));

        if(!Character.isLetter(input))
        {
            this.exception ="Please insert a letter: ";
            return;
        }

        //fills in the word you are searching for
        if(this.wordForGame.contains(alpha))
        {

            for(int i = 0; i< this.wordToGuess.length; i++)
            {
                if (input == this.wordToGuess[i])
                {
                    this.fillInWord[i] = input;

                }
            }
        }else
        //handels the wrong letters
        {
            manageWrongCharacters(input);
            System.out.println(Arrays.toString(wrongLetterArray));
        }
        //win and lose conditions
        if(this.wrongLetterCounter == 10)
        {
            this.exception = "You lost!\n"
            +"Your word was: " + wordForGame+"\n";

            this.wrongLetterArray = new char[30];
            this.wrongLetterCounter = 0;
            this.isFound = true;
            this.isWon = false;

        }

        if(Arrays.equals(this.fillInWord, this.wordToGuess))
        {
            this.exception = "You won!\n"+
           "Your word was: " + wordForGame+"\n";

            this.wrongLetterArray = new char[10];
            this.wrongLetterCounter = 0;
            this.isFound = true;
            this.isWon = true;

        }
    }

    /**
     * method to generate a random word from the array
     */
    public void selectGameWord(){
        Random rand = new Random();
        int n = rand.nextInt(wordList.length);
        this.wordForGame = wordList[n];
        this.wordToGuess = new char[this.wordForGame.length()];

        for (int i = 0; i < this.wordForGame.length(); i++)
        {
            this.wordToGuess[i] = this.wordForGame.charAt(i);
        }
    }

    /**
     * creates a character array for a new game and fills it with _
     */
    public void setFillInWord() {
        this.fillInWord = new char[this.wordForGame.length()];
        Arrays.fill(this.fillInWord, '_');
    }

    /**
     * takes in a letter and checks if the letter exists in the array.
     * If so, input is ignored.
     * If not the letter is added in the array.
     * @param inputLetter Char
     */
    public void manageWrongCharacters(char inputLetter) {
        boolean isInArray = false;

        for(int i = 0; i<this.wrongLetterArray.length; i++)
        {
            if(inputLetter == this.wrongLetterArray[i])
            {
                isInArray = true;
            }

        }
        if(!isInArray)
        {
            this.wrongLetterArray[this.wrongLetterCounter] = inputLetter;
            this.wrongLetterCounter++;
        }
    }

    /**
     * translates the fillInWord-array to a string
     * @return String
     */
    public String getFillInWordAsString() {
        String word = "";
        for(int i = 0; i<this.wordForGame.length(); i++)
        {
            word = word + this.fillInWord[i] + " ";
        }
        return word;
    }

    /**
     * translates the wrongCharacters-array to a string
     * @return String
     */
    public String getWrongCharacters() {
        String word = "";
        for(int i = 0; i<10; i++)
        {
            if(this.wrongLetterArray[i] != 0)
            {
                word = word + this.wrongLetterArray[i] + " ";
            }
        }
        return word;
    }

    /**
     * returns a string with as many * as there were wrong letters
     * @return
     */
    public String getWrongCount() {
        String word = "";
        if(this.wrongLetterCounter > 0)
        {
            for(int i = 0; i<this.wrongLetterCounter; i++)
            {
                word = word + "*" + " ";
            }
        }
        return word;
    }

    public boolean getIsFound()
    {
        return this.isFound;
    }

    public void setIsFound(boolean bool)
    {
        this.isFound = bool;
    }

    public boolean getWon()
    {
        return this.isWon;
    }


    public String getException(){
        return this.exception;
    }

    public void setException(String exception){
        this.exception = exception;
    }
}
