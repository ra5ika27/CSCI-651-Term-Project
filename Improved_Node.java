import java.util.ArrayList;
import java.util.Arrays;

public class Improved_Node {

    static int L_offset = 0;
    static int N_offset = 0;
    static ArrayList<String> L = new ArrayList<String>();
    static ArrayList<Improved_Node> N = new ArrayList<Improved_Node>();

    static int STRIDE_LENGTH = 4;
    boolean isLeaf = false;
    String nextHop = ""; // next hop only if node is a leaf
    int base0 = 0; // offset for leaf Array L
    int base1 = 0; // offset for Internal node Array N
    int[] vector = new int[STRIDE_LENGTH];

    private Improved_Node() {

        isLeaf = false;
        nextHop = "";
        base0 = 0;
        base1 = 0;
        for(int i=0;i<vector.length;i++) {
            vector[i] = -1;
        }
    }

    public static Improved_Node insert(Improved_Node n, String prefix, int index, String nextHop) {
        int nodeIndex;
        String p = index+STRIDE_LENGTH >= prefix.length()? prefix.substring(index):prefix.substring(index,index+STRIDE_LENGTH);
        nodeIndex = Integer.parseInt(p, 2);

        if(n == null) {
            n = new Improved_Node();
        }

        if(index+STRIDE_LENGTH == prefix.length() || p.length() != STRIDE_LENGTH){

            //set isLeaf
            n.isLeaf = true;

            //set next hop
            n.nextHop = nextHop;

            if(n.base0 == 0){
                n.base0 = L_offset;
            }

            //populate L
            L.add(L_offset,nextHop);
            L_offset = L_offset+1;
            n.vector[nodeIndex] = 0;
            return n;

        } else {
            n.vector[nodeIndex] = 1;
            if(n.base1==0) {
                n.base1 = N_offset;
            }
            N_offset += 1;
            N.add(N_offset-1,insert(N.get(N_offset),prefix,index+STRIDE_LENGTH,nextHop));

            //n.nextNode[nodeIndex] = insert(n.nextNode[nodeIndex],prefix,index+STRIDE_LENGTH,nextHop);

        }
        return n;
    }
    private void display () {
        System.out.println("Vector");
        for (int i = 0; i < vector.length; i++) {
            System.out.print(vector[i] + "|");
        }
        System.out.println("Base 0 " + base0);
        System.out.println("Base 1 " + base1);
    }

}
