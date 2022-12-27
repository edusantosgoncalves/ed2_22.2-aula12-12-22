public class Node {
    int element;
    int h;  //for height
    Node leftChild;
    Node rightChild;

    //default constructor to create null node
    public Node()
    {
        leftChild = null;
        rightChild = null;
        element = 0;
        h = 0;
    }
    // parameterized constructor
    public Node(int element)
    {
        leftChild = null;
        rightChild = null;
        this.element = element;
        h = 0;
    }
}
