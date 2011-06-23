package unity.service;

import java.util.ArrayList;
import java.util.List;

import org.slim3.datastore.Datastore;

import unity.meta.RelationTagMeta;
import unity.meta.TagMeta;
import unity.model.RelationTag;
import unity.model.Tag;

import com.google.appengine.api.datastore.Key;

public class DepthFirstSearchService {

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

    public Tag depthFirstSearch(Key gamekey) {

        List<Tag> asList = Datastore.query(Tag.class).asList();
        String[] trvst = new String[asList.size()];
        int ii = 0;
        for (Tag t : asList) {
            trvst[ii] = t.getName();
            ii++;
        }

        Vertex[] vs = new Vertex[trvst.length];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new Vertex(trvst[i]);
        }
        for (int i = 0; i < vs.length; i++) {

            for (int j = 0; j < vs.length; j++) {

                Key tag1 =
                    Datastore
                        .query(Tag.class)
                        .filter(TagMeta.get().name.equal(trvst[i]))
                        .asSingle()
                        .getKey();

                Key tag2 =
                    Datastore
                        .query(Tag.class)
                        .filter(TagMeta.get().name.equal(trvst[j]))
                        .asSingle()
                        .getKey();

                RelationTag asSingle =
                    Datastore
                        .query(RelationTag.class)
                        .filter(RelationTagMeta.get().tag1.equal(tag1))
                        .filter(RelationTagMeta.get().tag2.equal(tag2))
                        .asSingle();

                if (asSingle != null) {
                    new Edge(vs[i], vs[j], asSingle.getRelationCount());
                }
            }
        }

        {
            // Edge ab = new Edge(vs[0], vs[1], 2);
        }

        for (int i = 0; i < vs.length - 1; i++) {
            for (int j = i + 1; j < vs.length; j++) {
                System.out.println("---------------");
                search(vs[i], vs[j]);
            }
        }
        return null;
    }

    public static void search0(Vertex v, Vertex goal, List<Vertex> vs) {

        // System.out.println(v);

        List<Vertex> vvs = new ArrayList<Vertex>();
        vvs.addAll(vs);
        vvs.add(v);

        if (v == goal && vvs.size() <= 5) {

            for (Vertex vertex : vvs) {
                for (Edge ve : vertex.adjlist) {
                    System.out.println(ve);
                }
            }

            System.out.println(vvs);

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
