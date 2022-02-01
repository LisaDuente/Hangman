public class Player {
    private String name;
    private int won;
    private int matches;

    public Player(String name){
        this.name = name;
        this.won = 0;
        this.matches = 0;
    }

    public void saveNewPlayer(FileManager manager){
        if(!manager.checkData(this.name)){
            manager.addToTempSave(this.name +" "+this.won+" "+this.matches);
        }else{
            System.out.println("Redan finns!");
        }
    }

    public void savePlayerStats(FileManager manager){
        if(manager.checkData(this.name)) {
            for(int i = 0; i< manager.getTempSave().size(); i++) {
                String[] partHolder = manager.getTempSave().get(i).split(" ");
                if (partHolder[0].equals(this.name)) {
                    manager.getTempSave().set(i, this.name + " " + this.won + " " + this.matches);
                }
            }
        }
        System.out.println(manager.getTempSave());
    }


    public void loadPlayer(FileManager manager, String name){
        if(manager.checkData(name)){
            String[] playerStatistic = manager.takeOutData(name);
            this.name = playerStatistic[0];
            this.won = Integer.parseInt(playerStatistic[1]);
            this.matches = Integer.parseInt(playerStatistic[2]);
        }
    }

    public String getName(){
        return this.name;
    }

    public void printPlayerInfo(){
        System.out.println(this.name+" "+this.won+" "+this.matches);
    }

    public String playerInfo(){
        return this.name+" has won "+this.won+" matches and played "+this.matches;
    }

    public void increaseWinning(){
        this.won ++;
        this.matches++;
    }

    public void increaseLosing(){
        this.matches++;
    }

    public void setStats(String name){
        this.name = name;
        this.won = 0;
        this.matches = 0;
    }
}
