package com.mimieye;

import org.apache.commons.lang.StringUtils;

/**
 * Created by Pierreluo on 2017/5/4.
 */
public class BinaryTreeTest<T extends Comparable> {

    Node root = new Node();
    class Node{
        Node parent;
        Node left;
        Node right;
        T value;
        public Node(){}
        public Node(T t, Node parent){this.value=t;this.parent=parent;}
        boolean compareNodeTo(T t){
            return (t.compareTo(value)<0);
        }
    }

    void putValue(T t){
        if(root.value == null){
            root.value = t;
        } else {
            // root值大于t, 将t放于子节点
            if(root.value.compareTo(t)>0){
                putNode(root, t);
            } else {
                // t大于root值, 节点的值向下移动
                putNode(root, root.value);
                root.value = t;
            }
        }
    }

    private void putNode(Node node, T t) {
        if(node.left == null){
            node.left = new Node(t, node);
        }else{
            // 左节点大于t,将t放于左节点之子节点
            if(node.left.compareNodeTo(t)){
                putNode(node.left, t);
            } else {
                // t大于等于左节点，与右节点比较
                if(node.right == null){
                    node.right = new Node(t, node);
                } else {
                    // 右节点大于t
                    if(node.right.compareNodeTo(t)){
                        // 将t放于右节点之子节点
                        putNode(node.right, t);
                    }else{
                        // t大于等于右节点, 右节点的值向下移动
                        putNode(node.right, node.right.value);
                        node.right.value = t;
                    }

                }

            }

        }
    }

    int deep = 0;
    void printAll(Node n, String f, int i){
        System.out.println(n.value + f+ "-" + i + "-" + (i==0?"root":n.parent.value)+ ", ");
        i++;
        deep = i>deep?i:deep;
        if(n.right != null){
            printAll(n.right, "r", i);
        }
        if(n.left != null){
            printAll(n.left, "l", i);
        }

    }
    void print(Node n, String f, int i){
        System.out.print(n.value);
        i++;
        if(n.right != null){
            print(n.right, "r", i);
        }
        if(n.left != null){
            print(n.left, "l", i);
        }

    }

    public static void main(String[] args) {
        BinaryTreeTest<Integer> bt = new BinaryTreeTest<>();
        bt.putValue(5);
        bt.putValue(6);
        bt.putValue(6);bt.putValue(6);
        bt.putValue(8);
        bt.putValue(4);
        bt.putValue(2);bt.putValue(2);
        bt.putValue(0);
        bt.putValue(1);
        bt.putValue(3);
        bt.putValue(7);
        System.out.println(bt.deep);
        bt.printAll(bt.root, "root", 0);
        System.out.println(bt.deep);
        bt.printTreeStruct(bt.root);
    }

    void printTreeStruct(Node node){
        int size = deep;
        int tSize = size;
        int prevLineOutterMsize=0;
        int prevLineNodeMiddleSpaceSize=0;
        int lineFirstSpaceSize,
                lineNodeMiddleSpaceSize,
                lineOutterMiddleSpaceSize;
        int keyFirstSpaceSize,
                keyNodeMiddleSpaceSize,
                keyOutterMiddleSpaceSize;
        int nodeSize,splitKeyNodePairSpaceSize=0,
            lineSize,splitLineNodePairSpaceSize=0;
        for(int i=1;i<=size;i++){
            keyFirstSpaceSize = keyFirstSpace(tSize);
            lineNodeMiddleSpaceSize = nodeMiddleSpace(tSize);
            lineFirstSpaceSize = keyFirstSpaceSize/2 + 1;
            if(i == 1){
                lineOutterMiddleSpaceSize = lineNodeMiddleSpaceSize;
                System.out.println(StringUtils.leftPad(String.valueOf(node.value), keyFirstSpaceSize));
                System.out.print(StringUtils.leftPad("/", lineFirstSpaceSize));
                System.out.print(StringUtils.leftPad("",   lineNodeMiddleSpaceSize));
                System.out.println("\\");
            } else {
                lineOutterMiddleSpaceSize = lineOutterMiddleSpace(prevLineOutterMsize,tSize+1);
                keyNodeMiddleSpaceSize = prevLineNodeMiddleSpaceSize+2;
                keyOutterMiddleSpaceSize = prevLineOutterMsize + 2;
                /**
                 *  nodeSize = pow(2, n-1);
                 *  nodeSize/2 * keyNodeMiddleSpaceSize + (nodeSize/2 -1) * splitKeyNodePairSpaceSize + nodeSize -2 = keyOutterMiddleSpaceSize;
                 *  splitKeyNodePairSpaceSize = (keyOutterMiddleSpaceSize + 2 - nodeSize - nodeSize/2 * keyNodeMiddleSpaceSize)/(nodeSize/2 -1)
                 */
                nodeSize = pow(2, i-1);
                if(nodeSize > 2)
                    splitKeyNodePairSpaceSize = (keyOutterMiddleSpaceSize + 2 - nodeSize - nodeSize/2 * keyNodeMiddleSpaceSize)/(nodeSize/2 -1);
                System.out.print(StringUtils.leftPad(String.valueOf(i), keyFirstSpaceSize));
                for(int k=2;k<=nodeSize;){
                    System.out.print(StringUtils.leftPad("", keyNodeMiddleSpaceSize));
                    if(k == nodeSize){
                        System.out.println(i);
                        k++;
                    } else {
                        System.out.print(i);
                        k++;
                    }

                    if(nodeSize>2 && k < nodeSize){
                        System.out.print(StringUtils.leftPad("", splitKeyNodePairSpaceSize));
                        System.out.print(i);
                        k++;
                    }
                }

                if(i != size){
                    /**
                     *  lineSize = pow(2, n);
                     *  lineSize/2 * lineNodeMiddleSpaceSize + (lineSize/2 -1) * splitKeyNodePairSpaceSize + lineSize -2 = lineOutterMiddleSpaceSize;
                     *  42 + 25 + 2 = 69
                     *  splitLineNodePairSpaceSize = (lineOutterMiddleSpaceSize + 2 - lineSize - lineSize/2 * lineNodeMiddleSpaceSize)/(lineSize/2 -1)
                     */
                    lineSize = pow(2, i);
                    if(lineSize > 2)
                        splitLineNodePairSpaceSize = (lineOutterMiddleSpaceSize + 2 - lineSize - lineSize/2 * lineNodeMiddleSpaceSize)/(lineSize/2 -1);
                    System.out.print(StringUtils.leftPad("/", lineFirstSpaceSize));
                    for(int k=2;k<=lineSize;){
                        System.out.print(StringUtils.leftPad("", lineNodeMiddleSpaceSize));
                        if(k == lineSize){
                            System.out.println((k%2 == 0)?"\\":"/");
                            k++;
                        } else {
                            System.out.print((k%2 == 0)?"\\":"/");
                            k++;
                        }

                        if(lineSize>2 && k < lineSize ){
                            System.out.print(StringUtils.leftPad("", splitLineNodePairSpaceSize));
                            System.out.print((k%2 == 0)?"\\":"/");
                            k++;
                        }
                    }
                }

            }
            prevLineOutterMsize = lineOutterMiddleSpaceSize;
            prevLineNodeMiddleSpaceSize = lineNodeMiddleSpaceSize;

            tSize--;
        }
    }

    Node getDeepNode(Node node, int deep){
        Node resultNode = null;
        Node temp = node;
        for (int i = 1; i < deep; i++) {
            temp = temp.left;
        }
        resultNode = temp;
        return resultNode;
    }

    String getNodeValue(Node node){
        return node.value == null?"":node.value.toString();
    }

    int power2sum(int n){
        if(n == -1) return 1;
        if(n == 0) return pow(2,0);
        return pow(2,n) + power2sum(n -1);
    }

    private int pow(int m, int n){
        return (int)(Math.pow(m, n));
    }

    int nodeMiddleSpace(int n){
        if(n == 2)return 1;
        if(n == 1)return 0;
        return power2sum(n-3) * 3;
    }

    int keyFirstSpace(int n){
        if(n == 1)return 0;
        return pow(2,n -2) * 3;
    }

    int lineOutterMiddleSpace(int prevOutterMsize, int n){
        if(n == 3)return prevOutterMsize + 4;
        if(n < 3)return 0;
        return prevOutterMsize + pow(2,n -3) * 3;
    }

}


