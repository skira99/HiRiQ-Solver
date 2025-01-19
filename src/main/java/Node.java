package main.java;
import java.util.ArrayList;

public class Node{
  //The boolean array of the current node
  private boolean current[] = new boolean[33];

  //A dynamically sized array of the nodes containing the configurations reachable from the current configuration
  private ArrayList<Node> children = new ArrayList<Node>();

  //A string of the move taken to get from the parent configuration to this one. The solver will be called recursively and will return a string which will be the sum of the move strings to reach a solved combination
  private String move = "";

  //The total number of true in the current configuration
  private byte weight;
  
  //Constructor will create a node based on input values and apply a swap if a move is specific at creation. The input weight to the constructor is the intended weight of the configuration after any input swap is applied, not the weight of the input configuration
  public Node(boolean[] config, String s, byte w){
    //Copy the input data to the node
    copy(config, current);
    weight = w;
    move = s;

    //If a move was specified, apply the move now and modify weight accordingly
    if(s.length() != 0 ){
      swap(s);
    }
  }

  //Function to apply a swap based on an input string of format "xx[BY]yy", where xx and yy denote the two cells on the outside of the swap, and B or W in the middle
  //represent whether we are making a white substitution or a black substitution
  private void swap(String s){
    if(s.contains("W")){
      weight--;
    }

    else if(s.contains("B")){
      weight++;
    }

    //Extract the move to be made
    String[] container = s.split("[WB]");
    
    int i = Integer.parseInt(container[0]);
    int j = Integer.parseInt(container[1]);

    // horizontal swap
    if(j-i == 2 || j-i == -2){
      current[i] = !current[i];
      current[((i+j)/2)] = !current[((i+j)/2)];
      current[j] = !current[j];
    }

    //Vertical swap for any combination that includes squares 0-6
    else if( i < 7 || j < 7 ){
      current[i] = !current[i];
      current[((i+j)/2)-1] = !current[((i+j)/2)-1];
      current[j] = !current[j];
    }

    //Vertical swap for any combination that includes squares 27-32
    else if(i < 26 || j < 26){
      current[i] = !current[i];
      current[((i+j)/2)+1] = !current[((i+j)/2)+1];
      current[j] = !current[j];
    }

    //Vertical swap for any combination in the middle
    else{
      current[i] = !current[i];
      current[((i+j)/2)] = !current[((i+j)/2)];
      current[j] = !current[j];
    }

    return;
  }

  //Because we can only pass the array of boolean by reference, we always have to copy the parent array to a child node by copying it 1:1
  public void copy(boolean[] input, boolean[] target){
    for(int i = 0; i < 33; i++){
      target[i] = input[i];
    }
    return;
  }

  //Method to generale the list of children configurations from the current configuration  
  public void genChildren(){
    //Pull each triplets from the main class and check all 4 possible configurations of those triplets
    //If the configuration exists, create a node of the child configuration from applying a move
    int i, j, k;
    int[] triplet;
    for(int y = 0; y < 38; y++){
      triplet = HiRiQ_Solver.getTriplet(y);
      i = triplet[0];
      j = triplet[1];
      k = triplet[2];

      if(!current[i] && current[j] & current[k]){ //If false true true, which is a W sub
        children.add(new Node(current, String.valueOf(k) + "W" + String.valueOf(i), getWeight()));
      }
      else if(current[i] && current[j] & !current[k]){ //If true true false, which is a W sub
        children.add(new Node(current, String.valueOf(i) + "W" + String.valueOf(k), getWeight()));
      }
      else if(!current[i] && !current[j] & current[k]){ //If false false true, which is a B sub
        children.add(new Node(current, String.valueOf(i) + "B" + String.valueOf(k), getWeight()));
      }
      else if(current[i] && !current[j] & !current[k]){//If true false false, which is a B sub
        children.add(new Node(current, String.valueOf(k) + "B" + String.valueOf(i), getWeight()));
      }
    }
  }

  public Node getChild(int index){
    return children.get(index);
  }

  public ArrayList <Node> getChildren(){
    return children;
  }

  public boolean[] getCurrent(){
    return current;
  }

  public void setWeight(byte w){
    weight = w;
    return;
  }

  public byte getWeight(){
    return weight;
  }

  public String getMove(){
    return move;
  }

  //Test if the current configuration is solved
  public boolean isSolved(){
    if(weight == 1 && current[16] == true){
      return true;
    }
    return false;
  }
}
