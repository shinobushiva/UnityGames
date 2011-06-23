package unity.util;

import java.util.ArrayList;
import java.util.List;

public class DepthFirstSearch {

    public static class Edge {

        double weight;
        Vertex vertex1;
        Vertex vertex2;

        public Edge(Vertex v1, Vertex v2, double w) {
            vertex1 = v1;
            vertex1.adjlist.add(this);
            vertex2 = v2;
            vertex2.adjlist.add(this);
            weight = w;
        }

        @Override
        public String toString() {
            return vertex1.name + "-" + vertex2.name + "[" + weight + "]";
        }

    }

    public static class Vertex {

        String name;
        List<Edge> adjlist;

        public Vertex(String n) {
            name = n;
            adjlist = new ArrayList<Edge>();
        }

        @Override
        public String toString() {
            return name + "[" + adjlist.size() + "]";
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // String[] trvst = new String[] { "A", "B", "C", "D", "E" };
        String[] trvst = new String[7];
        for (int i = 0; i < trvst.length; i++) {

            trvst[i] = "" + i;

        }
        Vertex[] vs = new Vertex[trvst.length];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new Vertex(trvst[i]);
        }
        for (int i = 0; i < vs.length - 1; i++) {
            for (int j = i; j < vs.length; j++) {
                new Edge(vs[i], vs[j], 1);

            }
        }

        long t = System.currentTimeMillis();
        search(vs[0], vs[vs.length - 1]);
        for (int i = 0; i < vs.length - 1; i++) {
            for (int j = i + 1; j < vs.length; j++) {
                System.out.println("---------------");
                search(vs[i], vs[j]);
            }
        }
        System.out
            .println("time : " + (System.currentTimeMillis() - t) + " ms");
    }

    public static StringBuffer sb = new StringBuffer();

    public static void search0(Vertex v, Vertex goal, List<Vertex> vs) {

        // System.out.println(v);

        List<Vertex> vvs = new ArrayList<Vertex>();
        vvs.addAll(vs);
        vvs.add(v);

        if (v == goal && vvs.size() <= 5) {

            System.out.println(vvs);
            // sb.append(vvs.toString()).append("\n");
            return;
        } else {
            List<Edge> list = v.adjlist;
            for (Edge edge : list) {
                if (edge.vertex1 == v && !vvs.contains(edge.vertex2))
                    search0(edge.vertex2, goal, vvs);
                else if (edge.vertex2 == v && !vvs.contains(edge.vertex1))
                    search0(edge.vertex1, goal, vvs);
            }
        }

    }

    public static void search(Vertex s, Vertex g) {

        List<Vertex> vs = new ArrayList<Vertex>();
        search0(s, g, vs);

    }
}
