package main.game.core.map.path;

import android.util.Log;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import main.game.core.map.Position;

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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(position, node.position);
        }

        @Override
        public int hashCode() {
            return Objects.hash(position);
        }

        public Node(Position position, List<Node> links, int animatorIndex) {
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
            if(links.isEmpty()) return null;
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

    public static Path getWithTileCountX4(){
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
    //nodexy


    public static enum PathType {
        PATH1(genPath1(),1),
        PATH2(genPath2(),2),
        PATH3(genPath3(),3),
        PATH4(genPath4(),4);
        private Path path;
        private int id;

        PathType(Path path, int id) {
            this.path = path;
            this.id = id;
        }

        public Path getPath() {
            return path;
        }

        public int getId() {
            return id;
        }

        public static PathType fromIdToPath(int id){
            switch (id){
                case 1:
                    return PATH1;
                case 2:
                    return PATH2;
                case 3:
                    return PATH3;
                case 4:
                    return PATH4;
                default:
                    return PATH4;
            }
        }
    }
    public static Path genPath4(){
        //Main Path
        Node node10=new Node(new Position(1,0),RIGHTINDEX);
        Node node20=new Node(new Position(2,0),RIGHTINDEX);
        Node node30=new Node(new Position(3,0),RIGHTINDEX);

        Node node30ref= node10
                .addNode(node20)
                .addNode(node30);

        Node node40=new Node(new Position(4,0),DOWNINDEX);
        Node node41=new Node(new Position(4,1),DOWNINDEX);
        Node node42=new Node(new Position(4,2),DOWNINDEX);
        Node node43=new Node(new Position(4,3),DOWNINDEX);
        Node node44=new Node(new Position(4,4),DOWNINDEX);

        Node node44ref=node40
                .addNode(node41)
                .addNode(node42)
                .addNode(node43)
                .addNode(node44);

        node30ref.addNode(node40);


        Node node34=new Node(new Position(3,4),LEFTINDEX);
        Node node24=new Node(new Position(2,4),LEFTINDEX);
        Node node14=new Node(new Position(1,4),DOWNINDEX);

        Node node14ref = node34
                .addNode(node24)
                .addNode(node14);

        node44ref.addNode(node34);


        Node node15=new Node(new Position(1,5),DOWNINDEX);
        Node node16=new Node(new Position(1,6),DOWNINDEX);
        Node node17=new Node(new Position(1,7),DOWNINDEX);
        Node node18=new Node(new Position(1,8),DOWNINDEX);
        Node node19=new Node(new Position(1,9),LEFTINDEX);

        Node node19ref=node15
                .addNode(node16)
                .addNode(node17)
                .addNode(node18)
                .addNode(node19);

        node14ref.addNode(node15);

        Node node09=new Node(new Position(0,9),DOWNINDEX);
        Node node0_10=new Node(new Position(0,10),DOWNINDEX);
        Node node0_11=new Node(new Position(0,11),RIGHTINDEX);

        Node node0_11ref=node09
                .addNode(node0_10)
                .addNode(node0_11);

        node19ref.addNode(node09);


        Node node1_11=new Node(new Position(1,11),RIGHTINDEX);
        Node node2_11=new Node(new Position(2,11),RIGHTINDEX);
        Node node3_11=new Node(new Position(3,11),DOWNINDEX);

        Node node3_11ref=node1_11
                .addNode(node2_11)
                .addNode(node3_11);

        node0_11ref.addNode(node1_11);

        Node node3_12=new Node(new Position(3,12),DOWNINDEX);
        Node node3_13=new Node(new Position(3,13),RIGHTINDEX);

        Node node3_13ref=node3_12.addNode(node3_13);

        node3_11ref.addNode(node3_12);



        Node node4_13=new Node(new Position(4,13),RIGHTINDEX);
        Node node5_13=new Node(new Position(5,13),RIGHTINDEX);

        Node node5_13ref=node4_13
                .addNode(node5_13);

        node3_13ref.addNode(node4_13);
        //branch 01

        Node node54=new Node(new Position(5,4),RIGHTINDEX);
        Node node64=new Node(new Position(6,4),RIGHTINDEX);

        Node node64ref=node54
                .addNode(node64);

        node44.addNode(node54);


        Node node65=new Node(new Position(6,5),DOWNINDEX);
        Node node66=new Node(new Position(6,6),DOWNINDEX);
        Node node67=new Node(new Position(6,7),DOWNINDEX);
        Node node68=new Node(new Position(6,8),DOWNINDEX);
        Node node69=new Node(new Position(6,9),DOWNINDEX);
        Node node6_10=new Node(new Position(6,10),DOWNINDEX);
        Node node6_11=new Node(new Position(6,11),DOWNINDEX);
        Node node6_12=new Node(new Position(6,12),DOWNINDEX);

        Node node6_12ref=node65
                .addNode(node66)
                .addNode(node67)
                .addNode(node68)
                .addNode(node69)
                .addNode(node6_10)
                .addNode(node6_11)
                .addNode(node6_12);

        node64ref.addNode(node65);


        //last join tile
        Node node6_13=new Node(new Position(6,13),DOWNINDEX);
        Node node6_13ref1=node5_13ref.addNode(node6_13);
        Node node6_13ref2=node6_12.addNode(node6_13);


        return new Path(new Pair<>(7,14),node10);

    }
    public static Path genPath3(){
        Node node10=new Node(new Position(1,0),RIGHTINDEX);
        Node node20=new Node(new Position(2,0),DOWNINDEX);
        Node node21=new Node(new Position(2,1),DOWNINDEX);
        Node node22=new Node(new Position(2,2),DOWNINDEX);
        Node node23=new Node(new Position(2,3),DOWNINDEX);
        Node node24=new Node(new Position(2,4),DOWNINDEX);
        Node node25=new Node(new Position(2,5),DOWNINDEX);
        Node node26=new Node(new Position(2,6),LEFTINDEX);
        Node node16=new Node(new Position(1,6),DOWNINDEX);
        Node node17=new Node(new Position(1,7),DOWNINDEX);
        Node node18=new Node(new Position(1,8),DOWNINDEX);
        Node node19=new Node(new Position(1,9),DOWNINDEX);
        Node node1_10=new Node(new Position(1,10),RIGHTINDEX);
        Node node2_10=new Node(new Position(2,10),DOWNINDEX);
        Node node2_11=new Node(new Position(2,11),DOWNINDEX);
        Node node2_12=new Node(new Position(2,12),RIGHTINDEX);
        Node node3_12=new Node(new Position(3,12),DOWNINDEX);
        Node node3_13=new Node(new Position(3,13),RIGHTINDEX);
        Node node4_13=new Node(new Position(4,13),RIGHTINDEX);
        Node node5_13=new Node(new Position(5,13),RIGHTINDEX);
        Node node6_13=new Node(new Position(6,13),RIGHTINDEX);
        Node node6_13Ref=node10
                .addNode(node20)
                .addNode(node21)
                .addNode(node22)
                .addNode(node23)
                .addNode(node24)
                .addNode(node25)
                .addNode(node26)
                .addNode(node16)
                .addNode(node17)
                .addNode(node18)
                .addNode(node19)
                .addNode(node1_10)
                .addNode(node2_10)
                .addNode(node2_11)
                .addNode(node2_12)
                .addNode(node3_12)
                .addNode(node3_13)
                .addNode(node4_13)
                .addNode(node5_13)
                .addNode(node6_13);

        Node node33=new Node(new Position(3,3),RIGHTINDEX);
        Node node43=new Node(new Position(4,3),RIGHTINDEX);
        Node node53=new Node(new Position(5,3),DOWNINDEX);
        Node node54=new Node(new Position(5,4),DOWNINDEX);
        Node node55=new Node(new Position(5,5),DOWNINDEX);
        Node node56=new Node(new Position(5,6),DOWNINDEX);
        Node node57=new Node(new Position(5,7),LEFTINDEX);
        Node node47=new Node(new Position(4,7),DOWNINDEX);
        Node node48=new Node(new Position(4,8),DOWNINDEX);
        Node node49=new Node(new Position(4,9),DOWNINDEX);
        Node node4_10=new Node(new Position(4,10),RIGHTINDEX);
        Node node5_10=new Node(new Position(5,10),RIGHTINDEX);
        Node node6_10=new Node(new Position(6,10),DOWNINDEX);
        Node node6_11=new Node(new Position(6,11),DOWNINDEX);
        Node node6_12=new Node(new Position(6,12),DOWNINDEX);

        Node node6_12Ref=node33.addNode(node43)
                .addNode(node53)
                .addNode(node54)
                .addNode(node55)
                .addNode(node56)
                .addNode(node57)
                .addNode(node47)
                .addNode(node48)
                .addNode(node49)
                .addNode(node4_10)
                .addNode(node5_10)
                .addNode(node6_10)
                .addNode(node6_11)
                .addNode(node6_12);


        //join the two paths
        node23.addNode(node33);
        node6_12Ref.addNode(node6_13);



        return new Path(new Pair<>(7,14),node10);

    }
    public static Path genPath2(){
        Node node10=new Node(new Position(1,0),RIGHTINDEX);
        Node node20=new Node(new Position(2,0),DOWNINDEX);
        Node node21=new Node(new Position(2,1),DOWNINDEX);
        Node node22=new Node(new Position(2,2),DOWNINDEX);
        Node node23=new Node(new Position(2,3),DOWNINDEX);
        Node node24=new Node(new Position(2,4),DOWNINDEX);
        Node node25=new Node(new Position(2,5),DOWNINDEX);
        Node node26=new Node(new Position(2,6),LEFTINDEX);
        Node node16=new Node(new Position(1,6),DOWNINDEX);
        Node node17=new Node(new Position(1,7),DOWNINDEX);
        Node node18=new Node(new Position(1,8),DOWNINDEX);
        Node node19=new Node(new Position(1,9),DOWNINDEX);
        Node node1_10=new Node(new Position(1,10),RIGHTINDEX);
        Node node2_10=new Node(new Position(2,10),DOWNINDEX);
        Node node2_11=new Node(new Position(2,11),DOWNINDEX);
        Node node2_12=new Node(new Position(2,12),RIGHTINDEX);
        Node node3_12=new Node(new Position(3,12),DOWNINDEX);
        Node node3_13=new Node(new Position(3,13),RIGHTINDEX);
        Node node4_13=new Node(new Position(4,13),RIGHTINDEX);
        Node node5_13=new Node(new Position(5,13),RIGHTINDEX);
        Node node6_13=new Node(new Position(6,13),RIGHTINDEX);
        Node node6_13Ref=node10
                .addNode(node20)
                .addNode(node21)
                .addNode(node22)
                .addNode(node23)
                .addNode(node24)
                .addNode(node25)
                .addNode(node26)
                .addNode(node16)
                .addNode(node17)
                .addNode(node18)
                .addNode(node19)
                .addNode(node1_10)
                .addNode(node2_10)
                .addNode(node2_11)
                .addNode(node2_12)
                .addNode(node3_12)
                .addNode(node3_13)
                .addNode(node4_13)
                .addNode(node5_13)
                .addNode(node6_13);

        return new Path(new Pair<>(7,14),node10);

    }
    public static Path genPath1(){
        Node node10=new Node(new Position(1,0),RIGHTINDEX);
        Node node20=new Node(new Position(2,0),DOWNINDEX);
        Node node21=new Node(new Position(2,1),DOWNINDEX);
        Node node22=new Node(new Position(2,2),DOWNINDEX);
        Node node23=new Node(new Position(2,3),DOWNINDEX);
        Node node23Ref=node10
                .addNode(node20)
                .addNode(node21)
                .addNode(node22)
                .addNode(node23);

        Node node33=new Node(new Position(3,3),RIGHTINDEX);
        Node node43=new Node(new Position(4,3),RIGHTINDEX);
        Node node53=new Node(new Position(5,3),DOWNINDEX);
        Node node54=new Node(new Position(5,4),DOWNINDEX);
        Node node55=new Node(new Position(5,5),DOWNINDEX);
        Node node56=new Node(new Position(5,6),DOWNINDEX);
        Node node57=new Node(new Position(5,7),LEFTINDEX);
        Node node47=new Node(new Position(4,7),DOWNINDEX);
        Node node48=new Node(new Position(4,8),DOWNINDEX);
        Node node49=new Node(new Position(4,9),DOWNINDEX);
        Node node4_10=new Node(new Position(4,10),RIGHTINDEX);
        Node node5_10=new Node(new Position(5,10),RIGHTINDEX);
        Node node6_10=new Node(new Position(6,10),DOWNINDEX);
        Node node6_11=new Node(new Position(6,11),DOWNINDEX);
        Node node6_12=new Node(new Position(6,12),DOWNINDEX);
        Node node6_13=new Node(new Position(6,13),DOWNINDEX);

        Node node6_13Ref=node33.addNode(node43)
                .addNode(node53)
                .addNode(node54)
                .addNode(node55)
                .addNode(node56)
                .addNode(node57)
                .addNode(node47)
                .addNode(node48)
                .addNode(node49)
                .addNode(node4_10)
                .addNode(node5_10)
                .addNode(node6_10)
                .addNode(node6_11)
                .addNode(node6_12)
                .addNode(node6_13);


        //join the two paths
        node23.addNode(node33);



        return new Path(new Pair<>(7,14),node10);

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
