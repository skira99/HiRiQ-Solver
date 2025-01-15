package main.java;
import java.util.ArrayList;

public class Node {
  //The boolean array of the current node
  private boolean current[] = new boolean[33];
  //A dynamically sized array of the nodes containing the configurations reachable from the current configuration
  private ArrayList<Node> children = new ArrayList<Node>();
  //A string of the move taken to get from the parent configuration to this one. The solver will be called recursively and will return a string which will be the sum of the move strings to reach a solved combination
  private String move = "";
  //The total number of true in the current configuration
  private byte weight = 0;
  
  //Constructor will create a node based on input values and apply a swap if a move is specific at creation. The input weight to the constructor is the intended weight of the configuration after any input swap is applied, not the weight of the input configuration
  public Node(boolean[] config, String s, byte w){
    if(!s.equals("")){
      swap(config, s);
    }
    
    current = config;
    move = s;
    weight = w;
  }

  public void swap(boolean[] config, String s){
    //Extract the move to be made
    String[] container = s.split("[WB]");
    
    int i = Integer.parseInt(container[0]);
    int j = Integer.parseInt(container[1]);

    // horizontal swap
    if(j-i == 2 || j-i == -2){
      config[i] = !config[i];
      config[((i+j)/2)] = !config[((i+j)/2)];
      config[j] = !config[j];
    }

    //Vertical swap for any combination that includes squares 0-6
    else if( i < 7 || j < 7 ){
      config[i] = !config[i];
      config[((i+j)/2)-1] = !config[((i+j)/2)-1];
      config[j] = !config[j];
    }

    //Vertical swap for any combination that includes squares 27-32
    else if(i < 26 || j < 26){
      config[i] = !config[i];
      config[((i+j)/2)+1] = !config[((i+j)/2)+1];
      config[j] = !config[j];
    }

    //Vertical swap for any combination in the middle
    else{
      config[i] = !config[i];
      config[((i+j)/2)] = !config[((i+j)/2)];
      config[j] = !config[j];
    }

    return;
  }
    //Method to generale the list of children configurations from the current configuration
    
  public void genChildren(){
    if (!current[0] && current[1] & current[2]){
      children.add(new Node(current,"2W0",(byte) (weight-1)));
    }
  }

  public boolean[] getChild(int index){
    return children.get(index).getCurrent();
  }

  public boolean[] getCurrent(){
    return current;
  }

  //Test if the current configuration is solved
  public boolean isSolved(){
    if(weight == 1 && current[16] == true){
      return true;
    }
    return false;
  }
}
