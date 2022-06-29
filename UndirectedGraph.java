import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class UndirectedGraph {

    Integer[][] E;
    Integer[] V;

    public UndirectedGraph(){
        this.E = null;
        this.V = null;
    }

    public UndirectedGraph(Integer n){
        this.E = new Integer[1][2];
        this.V = new Integer[1];
        for (int i=1; i<=n; i++) {
            this.addVertex(i);
        }
    }

    public void addVertex(Integer i){
        if (this.V == null) this.V = new Integer[1];
        if  (this.V[0] == null) {
            this.V[0] = i;
        } else {
            boolean isContained = false;
            for (Integer v : this.V) {
                if (v.equals(i)) {
                    isContained = true;
                }
            }
            if (!isContained) {
                this.V = Arrays.copyOf(V, V.length + 1);
                this.V[this.V.length - 1] = i;
            }
        }
        //Arrays.sort(this.V);
    }

    public void addEdge(Integer i, Integer j){
        if (!i.equals(j)) {
            this.addVertex(i);
            this.addVertex(j);
            if (this.E == null) this.E = new Integer[1][2];
            if (this.E[0][0] != null) {
                Integer[][] TEMP = new Integer[this.E.length+1][2];
                for (int c=0; c<this.E.length; c++) {
                    TEMP[c] = this.E[c].clone();
                }
                this.E = TEMP;
                this.E[E.length-1][0] = Math.min(i,j);
                this.E[E.length-1][1] = Math.max(i,j);
            } else {
                this.E[0][0] = Math.min(i,j);
                this.E[0][1] = Math.max(i,j);
            }
        }
        //Arrays.sort(this.E, (a1,a2) -> a1[0].compareTo(a2[0]));
    }

    public void deleteEdge(Integer i, Integer j){
        if (!i.equals(j)) {
            Integer min = Math.min(i,j);
            Integer max = Math.max(i,j);
            Integer dPos = -1;
            for (int c=0; c<this.E.length; c++) {
                if (this.E[c][0].equals(min) && this.E[c][1].equals(max)) {
                    dPos = c;
                    break;
                }
            }
            Integer[][] TEMP = new Integer[this.E.length-1][2];
            for (int c=0; c<dPos; c++) {
                TEMP[c]  = this.E[c].clone();
            }
            for (int c=0; c<TEMP.length-dPos; c++) {
                TEMP[c+dPos] = this.E[c+dPos+1].clone();
            }
            this.E = TEMP;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        UndirectedGraph test = new UndirectedGraph();
        test.addEdge(1,2);
        test.addEdge(1,3);
        test.addEdge(2,3);
        BfsIterator testBFS = new BfsIterator(test,1);
            /*
			Knoten: 1 || Distanz: 0
			Knoten: 2 || Distanz: 1
			Knoten: 3 || Distanz: 1
             */

        UndirectedGraph zachary = new UndirectedGraph();
        Scanner scanner1 = new Scanner(new File("src/out.ucidata-zachary.sec"));
        while (scanner1.hasNext()) {
            String s = scanner1.nextLine();
            Integer i = Integer.parseInt(s.substring(0,s.indexOf(" ")));
            Integer j = Integer.parseInt(s.substring(s.indexOf(" ")+1,s.length()));
            zachary.addEdge(i,j);
        }
        BfsIterator zacharyBFS = new BfsIterator(zachary,1);
            /*
			Knoten: 1 || Distanz: 0
			Knoten: 2 || Distanz: 1
			Knoten: 3 || Distanz: 1
			Knoten: 4 || Distanz: 1
			Knoten: 5 || Distanz: 1
			Knoten: 6 || Distanz: 1
			Knoten: 7 || Distanz: 1
			Knoten: 8 || Distanz: 1
			Knoten: 9 || Distanz: 1
			Knoten: 10 || Distanz: 2
			Knoten: 11 || Distanz: 1
			Knoten: 12 || Distanz: 1
			Knoten: 13 || Distanz: 1
			Knoten: 14 || Distanz: 1
			Knoten: 15 || Distanz: 3
			Knoten: 16 || Distanz: 3
			Knoten: 17 || Distanz: 2
			Knoten: 18 || Distanz: 1
			Knoten: 19 || Distanz: 3
			Knoten: 20 || Distanz: 1
			Knoten: 21 || Distanz: 3
			Knoten: 22 || Distanz: 1
			Knoten: 23 || Distanz: 3
			Knoten: 24 || Distanz: 3
			Knoten: 25 || Distanz: 2
			Knoten: 26 || Distanz: 2
			Knoten: 27 || Distanz: 3
			Knoten: 28 || Distanz: 2
			Knoten: 29 || Distanz: 2
			Knoten: 30 || Distanz: 3
			Knoten: 31 || Distanz: 2
			Knoten: 32 || Distanz: 1
			Knoten: 33 || Distanz: 2
			Knoten: 34 || Distanz: 2
             */

        UndirectedGraph twitter = new UndirectedGraph();
        Scanner scanner2 = new Scanner(new File("src/soc-twitter-follows.txt"));
        int c=0;
        while (scanner2.hasNext()) {
            System.out.println(c);
            c += 1;
            String s = scanner2.nextLine();
            Integer i = Integer.parseInt(s.substring(0,s.indexOf(" ")));
            Integer j = Integer.parseInt(s.substring(s.indexOf(" ")+1,s.length()));
            twitter.addEdge(i,j);
        }
        BfsIterator twitterBFS = new BfsIterator(twitter,1);
            /*
            +++ Laufzeit zu lang, nach 10h manuell abgebrochen +++
             */

    }

}
