
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sunat Praphanwong 6088130
 */
public class FullBinaryTreeTester {
	
	public static void inOrderTraverse(Node root)
	{
		if(root != null) 
                {              
                   inOrderTraverse(root.left);
                        System.out.print(root.id+ " ");
                        inOrderTraverse(root.right);
        }
    }
	
	public static boolean isFullBinTree(Node root)
	{	
        if(root == null)
        {
            return true;
        }
        if(root.left != null && root.right != null)
        {
           if(isFullBinTree(root.left) && isFullBinTree(root.right))
           {
               return true;
           }
        }
        if(root.left == null && root.right == null)
        {
            return true;
        }
        else return false;
        }
	public static void normalTester()
	{
		Node[] ts = new Node[7];
		int count = 0;
		ts[count++] = null;
		ts[count++] = new Node(16, null, null);
		
		ts[count++] = new Node(16, new Node(14, null, null), null);
		
		ts[count++] = new Node(1, new Node(3, new Node(6, null, null), new Node(7, null, null)), 
				new Node(4, new Node(8, null, null), new Node(10, null, null)));
		
		ts[count++] = new Node(1, new Node(3, null, null), 
				new Node(4, new Node(8, null, null), new Node(10, null, null)));
		
		ts[count++] = new Node(1, new Node(3, new Node(6, null, null), null), 
				new Node(4, new Node(8, null, null), new Node(10, null, null)));
		
		ts[count++] = new Node(1, new Node(3, new Node(6, null, null), new Node(7, null, null)), 
				null);
		
		for(int i = 0; i < ts.length; i++)
		{
			System.out.println("[T"+i+"] in-order: ");
			inOrderTraverse(ts[i]);
			System.out.println("\n[T"+i+"] is"+(isFullBinTree(ts[i])?" ":" NOT ")+"a full binary tree.\n");
		}
		
	}
	
	
	/**************BONUS START**************/
	public static void printBinTree(Node root)
        {
//            if(root == null)
//            {
//            }
//            else
//            {
//                ArrayList<LinkedList<Node>> levels = new ArrayList<>();
//                LinkedList<Node> level = new LinkedList<>();
//                while(true)
//                {
//                    level.add(root);
//                    levels.add(level);
//                    int a = level.size();
//                    if(a==0){
//                        break;
//                    }
//                    while(a>0)
//                    {
//                        Node n = level.peek();
//                        System.out.println(root.id + " ");
//                        Node remove = level.remove();
//                        if(n.left != null &&n.right != null)
//                        {
//                            level.add(n.left);
//                            level.add(n.right);
//                        }
//                       
//                    }
//                    a--;
//                }
//                System.out.println();
//            }
	Node x = root;
		ArrayList<LinkedList<Node>> levels = new ArrayList<>();
		LinkedList<Node> level = new LinkedList<>();
		level.add(root);
		levels.add(level);
		LinkedList<Node> next = level;
		while (!next.isEmpty()) {
			next = new LinkedList<>();
			for (Node a : level) {
				if (a.left != null && a.right != null) {
					next.add(a.left);
                                        next.add(a.right);
				}
			}
			levels.add(next);
			level = next;
		}

		for (int i = 0; i < levels.size(); i++) {
			for (int j = 0; j < ((levels.size() * 2) - i * 4); j++) {
				System.out.print(" ");
			}
			for (Node a : levels.get(i)) {
				System.out.print(a.id);
				for (int k = 0; k < (levels.size() - i) * 2; k++) {
					System.out.print(" ");
				}
			}
			System.out.println();

		}
	}

	public static void iOTraverse(Node root, ArrayList<Integer> a) {
		if (root != null) {
			if (root.left == null && root.right == null) {
				a.add(root.id);
                                
			}
			else {
				if (root.left != null && root.right != null) {
					inOrderTraverse(root.left);  
                                        //System.out.print(" ");
                                       inOrderTraverse(root.right);
				}
				a.add(root.id);
			}
                        System.out.println();
		}
	}
	public static void iOPTraverse(Node root, ArrayList<Integer> a) {
		if (root != null) {
			if (root.left == null && root.right == null) {
				root.id = a.get(0);
				a.remove(0);
			}
			else {
				if (root.left != null && root.right != null) {
					inOrderTraverse(root.left);
                                        inOrderTraverse(root.right);
				}
				root.id = a.get(0);
				a.remove(0);
				
			}
		}
	}
	
	
	
	public static Node getBinSearchTree(Node root)
	{	//YOUR BONUS CODE GOES HERE
		ArrayList<Integer> ta = new ArrayList<>();
		iOTraverse(root, ta);
		Collections.sort(ta);
		iOPTraverse(root, ta);
		return root;
	}
	public static void bonusTester()
	{
		Node t = new Node(1, new Node(3, new Node(6, null, null), new Node(7, null, null)), 
				new Node(4, new Node(8, null, null), new Node(10, null, null)));
		System.out.println("Before Transforming: ");
		printBinTree(t);
		System.out.println("After Transforming: ");
		getBinSearchTree(getBinSearchTree(t));
		
	}
	/**************BONUS END**************/
	
	
	
	public static void main(String[] args)
	{
		normalTester();
		
		//Uncomment for bonus
		bonusTester();
	}

}

