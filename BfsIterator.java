import java.util.Arrays;
import java.util.LinkedList;

public class BfsIterator {

    private UndirectedGraph g;
    private Integer s;
    private String[][] colors;
    private Integer[][] distances;
    private Integer[][] fathers;
    private LinkedList<Integer> queue;

    public BfsIterator(UndirectedGraph g, Integer s) {
        // init
        this.g = g;
        this.s = s;
        int length = this.g.V.length;
        this.colors = new String[length][2];
        this.distances = new Integer[length][2];
        this.fathers = new Integer[length][2];
        for (int i=0; i<length; i++) {
            this.colors[i][0] = Integer.toString(this.g.V[i]);
            this.colors[i][1] = "w";
            this.distances[i][0] = this.g.V[i];
            this.distances[i][1] = Integer.MAX_VALUE;
            this.fathers[i][0] = this.g.V[i];
        }

        // start with s
        for (Integer i=0; i<this.g.V.length; i++) {
            if (this.colors[i][0].equals(Integer.toString(s))) {
                this.colors[i][1] = "g";
            }
            if (this.distances[i][0].equals(s)) {
                this.distances[i][1] = 0;
            }
        }
        this.queue = new LinkedList<Integer>();
        this.queue.add(s);

        // function
        while (this.queue.size() != 0) {
            Integer u = next();
            Integer[] uAL = new Integer[1];
            uAL[0] = u;
            for (Integer i=0; i<this.g.E.length; i++) {
                if (this.g.E[i][0].equals(u)) {
                    uAL = Arrays.copyOfRange(uAL,0,uAL.length+1);
                    uAL[uAL.length-1] = this.g.E[i][1];
                }
                if (this.g.E[i][1].equals(u)) {
                    uAL = Arrays.copyOfRange(uAL,0,uAL.length+1);
                    uAL[uAL.length-1] = this.g.E[i][0];
                }
            }
            for (int i=1; i<=uAL.length; i++) {
                String vColor = null;
                Integer vPos = null;
                for (Integer j=0; j<this.colors.length; j++) {
                    if (this.colors[j][0].equals(Integer.toString(uAL[i-1]))) {
                        vColor = this.colors[j][1];
                        vPos = j;
                    }
                }
                Integer uDist = null;
                for (Integer j=0; j<this.colors.length; j++) {
                    if (this.distances[j][0].equals(u)) {
                        uDist = this.distances[j][1];
                    }
                }
                if (vColor.equals("w")) {
                    this.colors[vPos][1] = "g";
                    this.distances[vPos][1] = uDist+1; // = uDIST+1
                    this.fathers[vPos][1] = u;
                    this.queue.add(uAL[i-1]);
                }
            }
            for (Integer i=0; i<this.colors.length; i++) {
                if (this.colors[i][0].equals(Integer.toString(u))) {
                    this.colors[i][1] = "b";
                }
            }
        }

        // print
        for (Integer i : this.g.V) {
            System.out.println("\t\t\tKnoten: " + i + " || Distanz: " + dist(i));
        }
    }

    public Integer next(){
        if (queue.size() == 0) {
            return null;
        } else {
            Integer res = this.queue.getFirst();
            this.queue.remove(res);
            return res;
        }
    }

    public Integer dist(Integer v) {
        Integer res = null;
        for (Integer j=0; j<this.distances.length; j++) {
            if (this.distances[j][0].equals(this.g.V[v - 1])) {
                res = distances[j][1];
            }
        }
        if (res.equals(Integer.MAX_VALUE)) {
            return -1;
        } else {
            return res;
        }
    }

}
