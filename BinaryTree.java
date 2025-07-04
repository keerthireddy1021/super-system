class Node{
    int key;
    Node left, right;
    public Node(int item){
        key = item;
        left = right = null;
    }
}
public class BinaryTree {
        Node root;
        void traverseTree(Node node){
            if(node!=null){
                traverseTree(node.left);
                System.out.print(" "+node.key);
                traverseTree(node.right);
            }
        }
        public static void main(String[] args){
            BinaryTree tree=new BinaryTree();
            tree.root=new Node(1);
            tree.root.left=new Node(2);
            tree.root.right=new Node(3);
            tree.root.left.left=new Node(4);
            System.out.print("Binary Tree (Inorder):");
            tree.traverseTree(tree.root);


        }
}
