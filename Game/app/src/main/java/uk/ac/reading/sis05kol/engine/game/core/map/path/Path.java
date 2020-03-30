package uk.ac.reading.sis05kol.engine.game.core.map.path;

import android.util.Log;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import uk.ac.reading.sis05kol.engine.game.core.map.Position;

public class Path {
    private Pair<Integer,Integer> validTileSize;
    private Node first;
    private static String loggerTag="PATH";
    private static Path instance;
    public static class Node{
        private Position position;
        private List<Node> links=new ArrayList<Node>();
        private Random random=new Random();

        public Node(Position position, List<Node> links) {
            this.position = position;
            this.links = links;
        }
        public Node(Position position) {
            this.position = position;
        }
        public Node(Position position, Node link) {
            this.position = position;
            this.links.add(link);
        }

        public Node addNode(Node node){
            this.links.add(node);
            return node;
        }

        public Position getPosition() {
            return position;
        }

        public List<Node> getLinks() {
            return links;
        }

        /**
         * this will choose a next node at random , this will only work if all possible
         * combinations of paths will lead to the terminal node , something that will hold for every
         * graph of this type
         * @return Node
         */
        public Node getRandomNext() {
            int next = Math.abs(random.nextInt(links.size()));
            return links.get(next);
        }

    }

    public Node getFirst() {
        return first;
    }

    public static Path getTesting(){
        if(instance==null) {
            Node i42ref;
            Node i53ref;
            Node i84ref;

            Node i11 = new Node(new Position(0, 0));
            Node i12 = new Node(new Position(1, 0));
            Node i22 = new Node(new Position(1, 1));
            Node i32 = new Node(new Position(1, 2));
            Node i42 = new Node(new Position(1, 3));

            i42ref = i11.
                    addNode(i12)
                    .addNode(i22)
                    .addNode(i32)
                    .addNode(i42);

            {
                Node i33 = new Node(new Position(2, 2));
                Node i34 = new Node(new Position(3, 2));
                Node i44 = new Node(new Position(3, 3));
                Node i54 = new Node(new Position(3, 4));
                Node i53 = new Node(new Position(2, 4));

                i53ref = i33
                        .addNode(i34)
                        .addNode(i44)
                        .addNode(i54)
                        .addNode(i53);

                i32.addNode(i33);
            }


            Node i52 = new Node(new Position(1, 4));
            Node i62 = new Node(new Position(1, 5));
            Node i61 = new Node(new Position(0, 5));
            Node i71 = new Node(new Position(0, 6));
            Node i81 = new Node(new Position(0, 7));
            Node i82 = new Node(new Position(1, 7));
            Node i83 = new Node(new Position(2, 7));
            Node i84 = new Node(new Position(3, 7));
            i84ref = i52
                    .addNode(i62)
                    .addNode(i61)
                    .addNode(i71)
                    .addNode(i81)
                    .addNode(i82)
                    .addNode(i83)
                    .addNode(i84);

            i42ref.addNode(i52);
            i53ref.addNode(i52);

            Log.i(loggerTag, "i84ref linksize is" + i84ref.getLinks().size());
            instance=new Path(new Pair<>(4,8),i11);

        }
        return instance;

    }

    public Path(Pair<Integer, Integer> validTileSize, Node first) {
        this.validTileSize = validTileSize;
        this.first = first;
    }
}
