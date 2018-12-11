/**This class simulates the population of
 * network prefixes from a given text file
 * in the Poptrie data structure and looks
 * up a given IP address.
 *
 * @author: Sudheeksha Garg
 * @author: Rasika Thorat
 * @author: Hanna Miller
 *
 */

import java.io.*;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Poptrie_Main {

    static ArrayList<String> L;
    static ArrayList<Poptrie_Node> N;

    /**
     * This function converts a IP address
     * to its respective network prefix for
     * a given subnet mask.
     * @param subnet
     * @param ipAddress
     * @return
     */
    private static String ipToPrefix(int subnet, String ipAddress){

        String[] splitIp = ipAddress.split("\\.");
        String networkPrefix = "";
        String binaryIp = "";

        for(String ipPart: splitIp) {

            int intIp = Integer.parseInt(ipPart);
            binaryIp += String.format("%8s", Integer.toBinaryString(intIp)).replace(" ", "0");
        }
        networkPrefix = binaryIp.substring(0,subnet);
        return networkPrefix;
    }


    /**
     * This function runs through the given file
     * and calls appropriate functions to
     * populates the Poptrie data structure.
     * @param args
     */
    public static void main(String[] args) throws UnknownHostException {

        BufferedReader br;
        Poptrie_Node root = null;
        try {

            br = new BufferedReader(new FileReader("Test_file.txt"));
            String c;

            //run through the file and extract IP address
            //and next hop value.
            while((c = br.readLine())!=null){
                String nextHop;
                String ipAddress = "";
                String subnet = "";
                char ch = c.charAt(0);

                int i = 0;
                while(ch != '/'){
                    ipAddress += ch;
                    i ++;
                    ch = c.charAt(i);
                }
                i++;
                ch = c.charAt(i);
                while(ch != ' '){
                    subnet += ch;
                    i++;
                    ch = c.charAt(i);
                }

                nextHop = c.substring(i+1,c.length());
                String netPrefix = ipToPrefix(Integer.parseInt(subnet),ipAddress);

                //if root is null, create a root.
                if(root == null) {
                    root = Poptrie_Node.insert(null, netPrefix, 0, nextHop);

                }else{
                    //Insert a network prefix
                    root = Poptrie_Node.insert(root, netPrefix, 0, nextHop);
                }
            }

            //create a leaf array
            L = new ArrayList<>();

            //create an internal node
            N = new ArrayList<>();

            //Populate with L, and N.
            Poptrie_population.populate(root,L,N);

        }catch (FileNotFoundException e){
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }


        Poptrie_Lookup find = new Poptrie_Lookup();

        //Get string for lookup.
        String key = "223.255.254.0";
        if(args.length!=0){
            key = args[0];
        }

        //Get network prefixes for the next hop
        String bin = ipToPrefix(32, key);

        //Lookup key in the Poptrie data structure
        find.lookup(root, N, L , bin);

    }

}
