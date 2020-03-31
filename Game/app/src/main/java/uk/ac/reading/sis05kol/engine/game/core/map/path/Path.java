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

    private static final int DOWNINDEX=0;
    private static final int UPINDEX=1;
    private static final int RIGHTINDEX=2;
    private static final int LEFTINDEX=3;
    public static class Node{
        private Position position;
        private int animatorIndex=0;
        private List<Node> links=new ArrayList<Node>();
        private Random random=new Random();

        public Node(Position position, List<Node> links,int animatorIndex) {
            this.position = position;
            this.links = links;
            this.animatorIndex=animatorIndex;
        }
        public Node(Position position,int animatorIndex) {
            this.position = position;
            this.animatorIndex=animatorIndex;
        }
        public Node(Position position, Node link,int animatorIndex) {
            this.position = position;
            this.links.add(link);
            this.animatorIndex=animatorIndex;
        }

        public int getAnimatorIndex() {
            return animatorIndex;
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

        @Override
        public String toString() {
            return "Node{" +
                    "position=" + position +
                    '}';
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

            Node i11 = new Node(new Position(0, 0),DOWNINDEX);
            Node i12 = new Node(new Position(1, 0),DOWNINDEX);
            Node i22 = new Node(new Position(1, 1),DOWNINDEX);
            Node i32 = new Node(new Position(1, 2),DOWNINDEX);
            Node i42 = new Node(new Position(1, 3),DOWNINDEX);

            i42ref = i11.
                    addNode(i12)
                    .addNode(i22)
                    .addNode(i32)
                    .addNode(i42);

            {
                Node i33 = new Node(new Position(2, 2),LEFTINDEX);
                Node i34 = new Node(new Position(3, 2),LEFTINDEX);
                Node i44 = new Node(new Position(3, 3),DOWNINDEX);
                Node i54 = new Node(new Position(3, 4),DOWNINDEX);
                Node i53 = new Node(new Position(2, 4),DOWNINDEX);

                i53ref = i33
                        .addNode(i34)
                        .addNode(i44)
                        .addNode(i54)
                        .addNode(i53);

                i32.addNode(i33);
            }


            Node i52 = new Node(new Position(1, 4),DOWNINDEX);
            Node i62 = new Node(new Position(1, 5),LEFTINDEX);
            Node i61 = new Node(new Position(0, 5),LEFTINDEX);
            Node i71 = new Node(new Position(0, 6),DOWNINDEX);
            Node i81 = new Node(new Position(0, 7),RIGHTINDEX);
            Node i82 = new Node(new Position(1, 7),RIGHTINDEX);
            Node i83 = new Node(new Position(2, 7),RIGHTINDEX);
            Node i84 = new Node(new Position(3, 7),RIGHTINDEX);
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

    public Node getNodeByPosition(Position tilePosition){
        if(!existsInPath(tilePosition))return null;
        return _getNodeByPosition(tilePosition,getFirst());

    }
    public Node _getNodeByPosition(Position tilePosition,Node curr){
        if(curr.getPosition().equals(tilePosition))return curr;
        for (Node i :curr.getLinks()) {
            return _getNodeByPosition(tilePosition,i);
        }
        return null;
    }

    public boolean existsInPath(Position p){
        return _existsInPath(p,getFirst());
    }
    private boolean _existsInPath(Position p,Node curr){
        boolean b ;
        if(p.equals(curr.getPosition())){
            return true;
        }
        for(Node i:curr.getLinks()){
            b=_existsInPath(p,i);
            if(b)return true;
        }
        return false;
    }

    public Path(Pair<Integer, Integer> validTileSize, Node first) {
        this.validTileSize = validTileSize;
        this.first = first;
    }
}
