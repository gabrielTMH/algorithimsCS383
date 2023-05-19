package scrabble;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.TrieSET;

import java.util.ArrayList;

public class AI implements ScrabbleAI{

    private GateKeeper gateKeeper;
    private TrieSET trie;
    private ArrayList<Location> hooks;

    @Override
    public void setGateKeeper(GateKeeper gateKeeper) {
        this.gateKeeper = gateKeeper;
        In in = new In("words.txt");
        hooks = new ArrayList<>();
        this.trie = new TrieSET();
        while (!in.isEmpty()) {
            String key = in.readString();
            trie.add(key);
        }

    }

    @Override
    public ScrabbleMove chooseMove() {
        if (gateKeeper.getSquare(Location.CENTER) == Board.DOUBLE_WORD_SCORE) {
          //play bingo word with last tile landing on center

        }
        //findvalidmoveareas()return an array of locations
        //a locations avilable length is determined by how far a word can go
        // before hitting another placed tile and how many tiles it will be adjacent to
       //if(can play bingo word){
        //play bingo word
        //}
        return null;
    }


    public boolean isAnchor(Location x){
        for(int i =0;i<7;i++){

        }
        return false;
    }

    public Location boardchange(String prev, String now){
        for(int i =0;i<240;i++){
            //finds the start of the last played word
            //location of the tile will be row= (int)i/15  col = i%16
            if(prev.charAt(i)!=now.charAt(i)){
                //check if the new word is horizontal if not its vertical
                    if(prev.charAt(i+1)!=now.charAt(i+1)){

                    }
            }
        }
        return null;
    }
    public int getSlot(Location x,Location Direction){
        Location endBound= new Location(x.getRow(),x.getColumn());
        while(gateKeeper.getSquare(endBound)==' '){

        }
        return -1;
    }

    public boolean isValidHook(Location loc){
        ArrayList<Character> hand = gateKeeper.getHand();
        char value = gateKeeper.getSquare(loc);

    }

    public boolean isLetter(char c){
        return (c>=65&&c<=122);
    }

    public boolean isAlphabetical(Location l){
        return isLetter(gateKeeper.getSquare(l));
    }
}
//look at placement options for special tiles

//find placement options for board not concerned with special tiles
    //look for open square without letters check proximity around them?
    //