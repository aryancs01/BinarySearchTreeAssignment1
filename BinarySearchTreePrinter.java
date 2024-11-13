package BinarySearchTree;
import java.util.ArrayList;
import java.util.Scanner;

public class BinarySearchTreePrinter {

    // structure of node - data left right
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
    }

    // make new node and according to binary search tree
    public static Node insert(Node root,int val){
        if(root == null){
            return new Node(val);
        }

        if(root.data > val){
            root.left = insert(root.left, val);
        }
        else if(root.data < val){
            root.right = insert(root.right, val);
        }

        return root;
    }

    public static int findGreaterNumber(int number1,int number2){
        return (number1>number2)?number1:number2;
    }

    // Get the height of whole tree structure 
    private static int getDepth(Node root) {
        if (root == null) return 0;
        return 1 + findGreaterNumber(getDepth(root.left), getDepth(root.right));
    }

    // print the resulted strings
    private static void printTree(Node root) {
        int depth = getDepth(root);
        ArrayList<String> lines = new ArrayList<>();

        binarySearchTreeStructure(root, 0, depth, lines);

        for(int i = 0; i < lines.size(); i++) {
            System.out.println(lines.get(i));
        }
        
    }

    //This is a Logic of how to print tree structure
    private static void binarySearchTreeStructure(Node root, int level, int depth, ArrayList<String> lines) {
        int maxWidth = (int) Math.pow(2, depth) * 2;
    
        for (int i = lines.size(); i <= level; i++) {      
            lines.add("");
        }
    
        StringBuilder currentLine = new StringBuilder(lines.get(level));
    
        if (root == null) {
            int spacesToAdd = maxWidth / (level + 1);
            currentLine.append(" ".repeat(spacesToAdd));
            lines.set(level, currentLine.toString());
            return;
        }
    
        int spacing = maxWidth / (level + 1) / 2;
        
        String nodeStr = root.data + " ";
        
        String underscores = "_".repeat(spacing / 2);
    
        String formatted = " ".repeat(spacing / 2) + underscores + nodeStr + underscores + " ".repeat(spacing / 2);
        currentLine.append(formatted);
    
        lines.set(level, currentLine.toString());
    
     
        binarySearchTreeStructure(root.left, level + 1, depth, lines);
        binarySearchTreeStructure(root.right, level + 1, depth, lines);
    }

    
    // Call printtree function and Change root of tree
    public static void printingTheTree(Node root,int[] values,int changeRootResponse,int i){
        root = null;
        values[0] = changeRootResponse;
        for(int j=0;j<i;j++){
            root = insert(root, values[j]);
        }
        System.out.println("Binary Search Tree:");
        printTree(root);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Node root = null;

        int[] values = new int[15];
        int i = 0;
        while(true){
            System.out.print("Enter value to add in tree (0-999) or enter 0 to quit: ");
                try{
                    int input_response = input.nextInt();
                    if(input_response == 0){
                        break;
                    }else if(input_response > 0 && input_response<1000){
                        values[i] = input_response;
                        i++;
                    }else{
                        System.out.println("Enter number between 0 to 999 ");
                    }
                }catch(Exception error){
                    System.out.println("Enter Valid Integer");
                    input.next();
                    continue;
                }
           
        }

        printingTheTree(root, values, values[0], i);

        while(true){
            System.out.print("Do you want to replace the root of BST?, type number or enter 0 for quit ");
                try{
                    int changeRootResponse = input.nextInt();
                    if(changeRootResponse == 0){
                        printTree(root);
                        break;
                    }else{
                        printingTheTree(root,values,changeRootResponse,i);
                        break;
                    }
                }catch(Exception error){
                    System.out.println("Enter Valid Integer");
                    input.next();
                    continue;
                }
           
        }

        input.close();
    }
}
