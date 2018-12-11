import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Poptrie_population {
    static void populate(Poptrie_Node root, ArrayList L, ArrayList N){

        if(root == null){
            return;
        }

        Queue<Poptrie_Node> queue = new LinkedList<>();
        queue.add(root);

//        System.out.println("*"+root +"->"+ Arrays.toString(root.nextNode));

//        while(queue.peek() != null){
        while(queue.size() > 0){

            Poptrie_Node node = queue.peek();
            queue.poll();

            if(node!= null) {
                System.out.println(node +"->"+Arrays.toString(node.nextNode)+"   IS LEAF -> "+node.isLeaf+" Next Hop -> "+Arrays.toString(node.nextHop));
                System.out.println("Vector -> "+Arrays.toString(node.vector));
                System.out.println("-------------------------");

                for (int i = node.nextNode.length-1; i >= 0; i--) {
                    if(node.nextNode[i]!= null) {
                        queue.add(node.nextNode[i]);
//                            N.add(node.nextNode[i]);

                    }
                }

                for(int i=node.vector.length-1;i>=0;i--){
                    if(node.vector[i] == 0){
                        L.add(node.nextHop[i]);
                        if(node.base0 == -1){
                            node.base0 = L.size()-1;
                            System.out.println("Base0: "+node.base0);
                        }

                    } else if(node.vector[i]== 1){
                        N.add(node.nextNode[i]);
                        if(node.base1 == -1){
                            node.base1 = N.size()-1;
                            System.out.println("Base1 :"+node.base1);
                        }
                    }

                }
                System.out.println("=------------------------=");

            }
        }
        System.out.println("N array :"+N);
        System.out.println("L array :"+L);


    }
}
