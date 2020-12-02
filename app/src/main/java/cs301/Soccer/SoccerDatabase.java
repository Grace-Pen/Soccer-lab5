package cs301.Soccer;

import android.util.Log;
import cs301.Soccer.soccerPlayer.SoccerPlayer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Soccer player database -- presently, all dummied up
 *
 * @author *** Grace Penunuri ***
 * @version *** put date of completion here ***
 *wow
 */
public class SoccerDatabase implements SoccerDB {


    Hashtable<String, SoccerPlayer> playerInfo = new Hashtable<String, SoccerPlayer>();

    /**
     * add a player
     *
     * @see SoccerDB#addPlayer(String, String, int, String)
     */
    @Override
    public boolean addPlayer(String firstName, String lastName, int uniformNumber, String teamName) {
        String fullName=  firstName + "##" + lastName;

        if (playerInfo.containsKey(fullName)) {

            return false;
        } //Already present

       else{
            SoccerPlayer newPlayer = new SoccerPlayer(firstName, lastName, uniformNumber, teamName);
            playerInfo.put(fullName,newPlayer );
            return true;

        }






    }
    // Turns a first and last name into a hash map key



    /**
     * remove a player
     *
     * @see SoccerDB#removePlayer(String, String)
     */
    @Override
    public boolean removePlayer(String firstName, String lastName) {
        String fullName=  firstName + "##" + lastName;
        if(playerInfo.containsKey(fullName)) {
            playerInfo.remove(fullName);
            return true;
        }
       else {
            return false;
        }
    }

    @Override
    public SoccerPlayer getPlayer(String firstName, String lastName) {
        String fullName=  firstName + "##" + lastName;
        if(playerInfo.containsKey(fullName)) {
            return playerInfo.get(fullName);
        }
      else {
          return null;
        }


    }

    /**
     * look up a player
     *
     * @see SoccerDB#getPlayer(String, String)
     */



    /**
     * increment a player's goals
     *
     * @see SoccerDB#bumpGoals(String, String)
     */
    @Override
    public boolean bumpGoals(String firstName, String lastName) {
        String fullName=  firstName + "##" + lastName;


       if(playerInfo.containsKey(fullName)) {
          getPlayer(firstName, lastName).bumpGoals();
          return true;
        }
       else {
           return false;
       }
    }

    /**
     * increment a player's assists
     *
     * @see SoccerDB#bumpAssists(String, String)
     */
    @Override
    public boolean bumpAssists(String firstName, String lastName) {
        String fullName=  firstName + "##" + lastName;


        if(playerInfo.containsKey(fullName)) {
            getPlayer(firstName, lastName).bumpAssists();
            return true;
        }
        else {
            return false;
        }

    }

    /**
     * increment a player's shots
     *
     * @see SoccerDB#bumpShots(String, String)
     */
    @Override
    public boolean bumpShots(String firstName, String lastName) {
        String fullName=  firstName + "##" + lastName;


        if(playerInfo.containsKey(fullName)) {
            getPlayer(firstName, lastName).bumpShots();
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * increment a player's saves
     *
     * @see SoccerDB#bumpSaves(String, String)
     */
    @Override
    public boolean bumpSaves(String firstName, String lastName) {
        String fullName=  firstName + "##" + lastName;


        if(playerInfo.containsKey(fullName)) {
            getPlayer(firstName, lastName).bumpSaves();
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * increment a player's fouls
     *
     * @see SoccerDB#bumpFouls(String, String)
     */
    @Override
    public boolean bumpFouls(String firstName, String lastName) {
        String fullName=  firstName + "##" + lastName;


        if(playerInfo.containsKey(fullName)) {
            getPlayer(firstName, lastName).bumpFouls();
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * increment a player's yellow cards
     *
     * @see SoccerDB#bumpYellowCards(String, String)
     */
    @Override
    public boolean bumpYellowCards(String firstName, String lastName) {
        String fullName=  firstName + "##" + lastName;


        if(playerInfo.containsKey(fullName)) {
            getPlayer(firstName, lastName).bumpYellowCards();
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * increment a player's red cards
     *
     * @see SoccerDB#bumpRedCards(String, String)
     */
    @Override
    public boolean bumpRedCards(String firstName, String lastName) {
        String fullName=  firstName + "##" + lastName;


        if(playerInfo.containsKey(fullName)) {
            getPlayer(firstName, lastName).bumpRedCards();
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * tells the number of players on a given team
     *
     * @see SoccerDB#numPlayers(String)
     */
    @Override
    // report number of players on a given team (or all players, if null)
    public int numPlayers(String teamName) {
        int playerCount = 0;
        Set<String> keys = playerInfo.keySet();
        if (teamName == null) {
            return playerInfo.size();
        }
        else {

            for (String key : keys) {
                String[] name = key.split("##");
                if(getPlayer(name[0], name[1]).getTeamName() == teamName)

                playerCount++;
            }

            return playerCount;
        }

    }




    /**
     * gives the nth player on a the given team
     *
     * @see SoccerDB#playerNum(int, String)
     */
    // get the nTH player
    @Override
    public SoccerPlayer playerNum(int idx, String teamName) {

        int count =0;
        if( idx > numPlayers(teamName)){
            return null;
        }
        else if (teamName == null){
            Set<Map.Entry<String, SoccerPlayer>> entryset = playerInfo.entrySet();
            for(Map.Entry<String, SoccerPlayer> ent : entryset){
            if(count == idx) {
               return ent.getValue();
                 }
                       count++;
                    }
              }
        else{
            Set<Map.Entry<String, SoccerPlayer>> entrset = playerInfo.entrySet();
            for(Map.Entry<String, SoccerPlayer> ent : entrset){
                if(ent.getValue().getTeamName().equals(teamName) && idx == count){
                    return ent.getValue();
                }
                else if(ent.getValue().getTeamName().equals(teamName) && idx > count){
                    count ++;
                }
            }
        }



        return null;
    }

    /**
     * reads database data from a print
     *
     * @see SoccerDB#readData(java.io.File)
     */
    // read data from print
    @Override
    public boolean readData(File print) {
        try{
            Scanner printReader = new Scanner(print);
            for(int i=0; i<11; i++){
                String firstName = printReader.nextLine();
                String lastName = printReader.nextLine();
                int uniform = printReader.nextInt();
                int goals = printReader.nextInt();
                int assists = printReader.nextInt();
                int shots= printReader.nextInt();
                int fouls = printReader.nextInt();
                int saves = printReader.nextInt();
                int yellowCards = printReader.nextInt();
                int redCards = printReader.nextInt();
                String teamName = printReader.nextLine();
                addPlayer(firstName, lastName, uniform, teamName);
                while(goals>0){
                   bumpGoals(firstName,lastName);
                   goals--;
                }
                while(assists>0){
                    bumpAssists(firstName,lastName);
                    assists--;
                }
                while(shots>0){
                    bumpShots(firstName,lastName);
                    shots--;
                }
                while(saves>0){
                    bumpSaves(firstName,lastName);
                    saves--;
                }
                while(fouls>0){
                    bumpFouls(firstName,lastName);
                    fouls--;
                }
                while(yellowCards>0){
                    bumpYellowCards(firstName,lastName);
                    yellowCards--;
                }
                while(redCards>0){
                    bumpRedCards(firstName,lastName);
                    redCards--;
                }
               return true;
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * write database data to a print
     *
     * @see SoccerDB#writeData(java.io.File)
     */
    // write data to print
    @Override
    public boolean writeData(File print) {
        try{
            File writtenData = new File(print+".txt");
            PrintWriter printWriter = new PrintWriter(print);
            for(SoccerPlayer player: playerInfo.values()){
                printWriter.println(player.getFirstName()+"\n");
                printWriter.println(player.getLastName()+"\n");
                printWriter.write(player.getUniform()+"\n");
                printWriter.write(player.getGoals()+"\n");
                printWriter.write(player.getAssists()+"\n");
                printWriter.write(player.getShots()+"\n");
                printWriter.write(player.getSaves()+"\n");
                printWriter.write(player.getFouls()+"\n");
                printWriter.write(player.getYellowCards()+"\n");
                printWriter.write(player.getRedCards()+"\n");
                printWriter.write(player.getTeamName()+"\n");
            }
            printWriter.close();
            return true;
        }

        catch (IOException e) {
            return false;
        }


}

    /**
     * helper method that logcat-logs a string, and then returns the string.
     * @param s the string to log
     * @return the string s, unchanged
     */
     private String logString(String s) {
        Log.i("write string", s);
        return s;
    }

    /**
     * returns the list of team names in the database
     *
     * @see cs301.Soccer.SoccerDB#getTeams()
     */
    // return list of teams
    @Override
    public HashSet<String> getTeams() {
        return new HashSet<String>();
    }

}
