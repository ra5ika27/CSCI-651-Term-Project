import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Poptrie_populationLeaf {
    static void populate(Poptrie_NodeLeaf root, ArrayList L, ArrayList N){

        if(root == null){
            return;
        }

        Queue<Poptrie_NodeLeaf> queue = new LinkedList<>();
        queue.add(root);
        while(queue.size() > 0){

            Poptrie_NodeLeaf node = queue.peek();
            queue.poll();

            if(node!= null) {
                //System.out.println(node +"->"+Arrays.toString(node.nextNode)+"   IS LEAF -> "+node.isLeaf+" Next Hop -> "+Arrays.toString(node.nextHop));
                //System.out.println("Vector -> "+Arrays.toString(node.vector));
                //System.out.println("-------------------------");

                for (int i = node.nextNode.length-1; i >= 0; i--) {
                    if(node.nextNode[i]!= null) {
                        queue.add(node.nextNode[i]);
//                            N.add(node.nextNode[i]);

                    }
                }

                for(int i=node.vector.length-1;i>=0;i--){

                    if(node.vector[i] == 0){
                        if(node.base0 == -1){
                            L.add(node.nextHop[i]);
                            node.base0 = L.size()-1;
                            node.leafvec[i] = 1;
                            //System.out.println("Base0: "+node.base0);
                            //System.out.println("Leaf vector: "+Arrays.toString(node.leafvec));
                        } else if(node.base0 != -1){
                            if (!L.get(node.base0).equals(node.nextHop[i])){
                                L.add(node.nextHop[i]);
                                node.leafvec[i] = 1;
                                node.base0 = L.size()-1;
                                //System.out.println("Base0: "+node.base0);
                                //System.out.println("Leaf vector: "+Arrays.toString(node.leafvec));
                            }
                        }

                    } else if(node.vector[i]== 1){
                        N.add(node.nextNode[i]);
                        if(node.base1 == -1){
                            node.base1 = N.size()-1;
                            //System.out.println("Base1 :"+node.base1);
                        }
                    }

                }
                System.out.println("=------------------------=");

            }
        }
        //System.out.println("N array :"+N);
        System.out.println("L array size:"+L.size());


    }
}
