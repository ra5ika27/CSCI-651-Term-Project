import java.io.*;
import java.util.ArrayList;

public class Improved_Main {


    private static String ipToPrefix(int subnet, String ipAddress){

        String[] splitIp = ipAddress.split("\\.");
        String networkPrefix = "";
        String binaryIp = "";

        for(String ipPart: splitIp){

//            if(networkPrefix.length() != subnet) {
//                int intIp = Integer.parseInt(ipPart);
//                networkPrefix += String.format("%8s",Integer.toBinaryString(intIp)).replace(" ","0");
//            }

            int intIp = Integer.parseInt(ipPart);
            binaryIp += String.format("%8s",Integer.toBinaryString(intIp)).replace(" ","0");
//            if(subnet >= 8) {
//                networkPrefix += binaryIp;
//                subnet = subnet - 8;
//            }else if(subnet!=0){
//                networkPrefix += binaryIp
        }

        networkPrefix = binaryIp.substring(0,subnet);
        System.out.println("Prefix length"+networkPrefix.length());
        return networkPrefix;
    }

    public static void main(String[] args) {

        BufferedReader br;
        Poptrie_Node root = null;
        try {

            br = new BufferedReader(new FileReader("/Users/rasikathorat/IdeaProjects/FCN/src/CSCi-651-Term-Project/dataset.txt"));
            String c;
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
                System.out.println(ipAddress);
                i++;
                ch = c.charAt(i);
                while(ch != ' '){
                    subnet += ch;
                    i++;
                    ch = c.charAt(i);
                }

                nextHop = c.substring(i+1,c.length());
                System.out.println(nextHop);
                System.out.println(subnet);
                String netPrefix = ipToPrefix(Integer.parseInt(subnet),ipAddress);
                System.out.println(netPrefix);
                if(root == null)
                    root = Poptrie_Node.insert(null, netPrefix, 0, nextHop);

                else
                    root = Poptrie_Node.insert(root,netPrefix,0,nextHop);
            }

            ArrayList<Integer> L = new ArrayList<>();
            ArrayList<Integer> N = new ArrayList<>();
            //Poptrie_population.populate(root,L,N);

        }catch (FileNotFoundException e){
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
