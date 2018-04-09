/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sunat Praphanwong 6088130
 */

public class Node {
	public int id;
	public Node left = null;
	public Node right = null;
	
	public Node(int _id, Node _left, Node _right)
	{	id = _id;
		left = _left;
		right = _right;
	}
}
