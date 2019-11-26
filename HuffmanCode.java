import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanCode {
    public static void main(String args[]){

        HuffmanCode code = new HuffmanCode();
        code.run("awwwwwqasdfsdf");

    }

    void run(String inputString){
        Map<Character, Integer> numChar =  new HashMap<>();
        Map<Character, Node>  charNode = new HashMap<>();
        for(int i  = 0; i < inputString.length(); i++){
            char c =  inputString.charAt(i);
            if(numChar.containsKey(c)){
                numChar.put(inputString.charAt(i),numChar.get(c)+1);
            }else{
                numChar.put(c,1);
            }
        }
        PriorityQueue<Node>  priorityQueue = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : numChar.entrySet()){
            Leaf node = new Leaf(entry.getKey(),entry.getValue());
            priorityQueue.add(node);
            charNode.put(entry.getKey(),node);
        }
        while (priorityQueue.size() > 1) {
            Node first = priorityQueue.poll();
            Node second = priorityQueue.poll();
            priorityQueue.add(new InteranlNode(first,second));
        }
        Node root = priorityQueue.poll();
        root.CharCode("");
        String encodedMSG = "";
        for(int i = 0; i < inputString.length(); i++){
            char c = inputString.charAt(i);
            encodedMSG  += charNode.get(c).code;
        }
        System.out.println(encodedMSG);
    }

}

class Node implements Comparable<Node>{
    int sum;
    String code;

    public Node(int sum) {
        this.sum = sum;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(sum, o.sum);
    }

    public void CharCode(String code){
        this.code = code;
    }

}


class InteranlNode extends  Node{

    Node leftNode;
    Node rightNode;

    public InteranlNode(Node leftNode, Node rightNode){
        super(leftNode.sum + rightNode.sum);
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public void CharCode(String code){
        super.CharCode(code);
        leftNode.CharCode(code + "0");
        rightNode.CharCode(code + "1");
    }
}


class Leaf extends Node {
    int count;
    char aChar;
    public Leaf(char aChar, int count) {
        super(count);
        this.aChar = aChar;
        this.count = count;
    }

    public void CharCode(String code){
        super.CharCode(code);
        System.out.println(code + ":" + aChar);
    }

}


