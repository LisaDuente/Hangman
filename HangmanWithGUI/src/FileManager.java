import java.io.*;
import java.util.*;
public class FileManager {
    private File save;
    private Scanner fileReader;
    private ArrayList<String> tempSave;

    public FileManager(){
        this.tempSave = new ArrayList<String>();
        this.save = new File("C:\\Users\\Lisa\\git\\HangmanWithGUI\\src\\SaveData.txt");
        try{
            this.fileReader = new Scanner(save);
        }catch(Exception e){
            e.printStackTrace();//gui manager soll ein fenster öffnen hier
        }
    }

    public void takeOutDataFromFile(){
       while(this.fileReader.hasNextLine()){
           tempSave.add(fileReader.nextLine());
       }
    }

    public void fillInData(){
        try{
            PrintWriter writer = new PrintWriter("C:\\Users\\Lisa\\git\\HangmanWithGUI\\src\\SaveData.txt");
            for(int i = 0; i<tempSave.size(); i++){
                writer.println(tempSave.get(i));
            }
            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void addToTempSave(String line){
        this.tempSave.add(line);
    }

    //checka när den stängas av eller hur långt den körs
    public boolean checkData(String playerName){
        for(String line: this.tempSave){
            String[] partHolder = line.split(" ");
            if(partHolder[0].equals(playerName)){
                return true;
            }
        }
        return false;
    }

    public String[] takeOutData(String playerName){
        for(String line: this.tempSave){
            String[] partHolder = line.split(" ");
            if(partHolder[0].equals(playerName)) {
            return partHolder;
            }
        }
        String[] empty = {"empty"};
        return empty;
    }

    public ArrayList<String> getTempSave(){
        return this.tempSave;
    }

    public String getPlayerArray(){
        String s = "";
        for(String line: tempSave){
            s = s + line + "\n";
        }
        return s;
    }
}
