import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiManager extends JFrame{
    JFrame frame;
    JTextField inputField;
    JTextArea menuText;
    JButton enter;
    JLabel playerInfo;
    String input;
    Game game;
    Player player;
    FileManager manager;
    JLabel saveInfo;
    JLabel info;

    public GuiManager(Game currentGame, Player player, FileManager manager) {
        this.game = currentGame;
        this.player = player;
        this.manager = manager;
        this.playerInfo = new JLabel("Your player: " + player.playerInfo());
        this.saveInfo = new JLabel("Do you want to save your progress?");
        this.info = new JLabel("Please choose a Name!");
    }

        public String getInput(){
        this.input = inputField.getText();
            return this.input;
       }

       public void setMenuText(){
        menuText.setText(game.menuText());
       }

       public void showMenu(){
           JFrame frame = new JFrame("Hangman Extreme");


           JButton spela = new JButton("play game");
           JButton load = new JButton("load");
           JButton save = new JButton("save");
           JButton create = new JButton("create");
           JButton close = new JButton("close");
           JLabel label = new JLabel();
           ImageIcon startImg = new ImageIcon("C:\\Users\\Lisa\\git\\HangmanWithGUI\\src\\Titel.gif");


           frame.setSize(500, 500);

           frame.add(label);
           frame.add(playerInfo);
           frame.add(spela);
           frame.add(save);
           frame.add(load);
           frame.add(close);
           frame.add(create);
           playerInfo.setBounds(100,400,400,25);
           label.setBounds(50,25,400,100);
           spela.setBounds(175,150,100,25);
           save.setBounds(175,200,100,25);
           load.setBounds(175,250,100,25);
           close.setBounds(175,350,100,25);
           create.setBounds(175,300,100,25);
           label.setIcon(startImg);


           frame.setLayout(null);
           frame.setVisible(true);

           spela.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   playGame(game);
               }
           });

           save.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   savePlayer();
               }
           });

           load.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                    loadPlayer();
               }
           });

           close.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   frame.setVisible(false);
                   frame.dispose();
               }
           });

           create.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   createPlayer();
               }
           });
       }

       public void createPlayer(){
        manager.takeOutDataFromFile();
           JFrame frame = new JFrame("Hangman Extreme");
           JButton create = new JButton("create");
           JTextField inputField = new JTextField();

           frame.setSize(500, 200);
           frame.add(info);
           frame.add(create);
           frame.add(inputField);

           info.setBounds(125,50,300,25);
           inputField.setBounds(75, 75, 200, 25);
           create.setBounds(275, 75, 100, 25);

           frame.setLayout(null);
           frame.setVisible(true);

           create.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   String s = inputField.getText();
                   String[] partHolder = s.split(" ");

                   if (manager.checkData(partHolder[0])) {
                       info.setText("Player already exists!");
                       Timer timer = new Timer(1500, new ActionListener() {
                           @Override
                           public void actionPerformed(ActionEvent e) {
                               info.setText("Please choose a name:");
                           }
                       });
                       timer.start();
                       inputField.setText("");
                   } else {
                       player.setStats(partHolder[0]);
                       player.saveNewPlayer(manager);
                       manager.fillInData();
                       info.setText("You created: " + player.getName());
                       Timer timer = new Timer(1500, new ActionListener() {
                           @Override
                           public void actionPerformed(ActionEvent e) {
                               frame.setVisible(false);
                               frame.dispose();
                           }
                       });
                       timer.start();
                   }
               }
           });
       }

       public void savePlayer(){
           if(player.getName().equals("")){
               playerInfo.setText("Please load a player first!");
               Timer timer = new Timer(1500, new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                       playerInfo.setText("Your player: "+player.playerInfo());
                   }
               });
               timer.start();

           }else{
               saveInfo.setText("Do you want to save your progress?");
               JFrame frame = new JFrame("Hangman Extreme");
               JButton yes = new JButton("Yes");
               JButton no = new JButton("No");

               frame.setSize(500, 200);
               frame.add(saveInfo);
               frame.add(yes);
               frame.add(no);

               saveInfo.setBounds(125,50,300,25);
               yes.setBounds(100,100, 100, 25);
               no.setBounds(250,100, 100, 25);

               frame.setLayout(null);
               frame.setVisible(true);

               manager.takeOutDataFromFile();

               no.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                       saveInfo.setText("Get fucked.");
                       Timer timer = new Timer(700, new ActionListener() {
                           @Override
                           public void actionPerformed(ActionEvent e) {
                               frame.setVisible(false);
                               frame.dispose();
                           }
                       });
                       timer.start();
                   }
               });

               yes.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                       //save the changes in the array
                       player.savePlayerStats(manager);
                       manager.fillInData();
                       saveInfo.setText("You saved Player "+ player.getName());
                       Timer timer = new Timer(700, new ActionListener() {
                           @Override
                           public void actionPerformed(ActionEvent e) {
                               frame.setVisible(false);
                               frame.dispose();
                           }
                       });
                       timer.start();
                   }
               });
           }

    }

       public void loadPlayer(){
        manager.takeOutDataFromFile();
           JFrame frame = new JFrame("Hangman Extreme");
           JLabel infoText = new JLabel("Please insert a player's name to load your player.");
           JTextField inputField = new JTextField();
           JButton enter = new JButton("enter");
           JTextArea showPlayers = new JTextArea(manager.getPlayerArray());

           frame.add(infoText);
           frame.add(inputField);
           frame.add(enter);
           frame.add(showPlayers);

           infoText.setBounds(75,50,300,25);
           inputField.setBounds(50, 100,200,25);
           enter.setBounds(275,100,100,25);
           showPlayers.setBounds(50,150,300,200);

           frame.setSize(500,500);
           frame.setLayout(null);
           frame.setVisible(true);

           enter.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   String s = inputField.getText();
                   player.loadPlayer(manager, s);
                   updatePlayerInfoLabel();
                   Timer timer = new Timer(500, new ActionListener() {
                       @Override
                       public void actionPerformed(ActionEvent e) {
                           frame.setVisible(false);
                           frame.dispose();
                       }
                   });
                   timer.start();
               }
           });


       }


       public void playGame(Game currentGame) {
        if(player.getName().equals("")){
            playerInfo.setText("Please load a player first!");
            Timer timer = new Timer(1500, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    playerInfo.setText("Your player: "+player.playerInfo());
                }
            });
            timer.start();

        }else{
            currentGame.selectGameWord();
            currentGame.setFillInWord();
            currentGame.setIsFound(false);
            this.game = currentGame;

            JFrame frame = new JFrame("Hangman Extreme");
            JTextField inputField = new JTextField();
            JTextArea menuText = new JTextArea();
            JButton enter = new JButton("enter");
            this.input = "Hey";

            frame.add(enter);
            frame.add(inputField);
            frame.add(menuText);

            frame.setSize(500, 500);
            menuText.setBounds(50, 80, 300, 100);
            menuText.setText(game.menuText());
            enter.setBounds(350, 50, 90, 25);
            inputField.setBounds(50, 50, 300, 25);

            frame.setLayout(null);
            frame.setVisible(true);

            enter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String s = inputField.getText();
                    game.update(s);
                    menuText.setText(game.menuText() + "\n" + game.getException());
                    inputField.setText("");
                    Timer exceptionTimer = new Timer(1500, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            game.setException("");
                        }
                    });
                    exceptionTimer.start();
                    if (game.getIsFound()) {
                        Timer timer = new Timer(1500, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                frame.setVisible(false);
                                frame.dispose();
                                game.clearGame();
                            }
                        });
                        timer.start();
                        if(game.isWon){
                            player.increaseWinning();
                            playerInfo.setText("Your player: "+player.playerInfo());
                        }else{
                            player.increaseLosing();
                            playerInfo.setText("Your player: "+player.playerInfo());
                        }
                    }
                }
            });
        }

       }

       public void updatePlayerInfoLabel(){
        this.playerInfo.setText("Your player: "+player.playerInfo());
       }

}
