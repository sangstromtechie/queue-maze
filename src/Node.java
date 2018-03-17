/**
 * Node - A class that is the building block of several data structures including the linked list.
 *        Holds generic element data and links to a previous Node and a next Node, if they are available
 *
 * <pre>
 *
 * Assignment:     #1
 * Course:         ADEV-3001
 * Date Created:   February 2nd 2018
 *
 * Revision Log
 * Who       When          Reason
 * --------- ------------- ---------------------------------
 *
 * </pre>
 *
 * @author Christian Wenham
 * @version 1.0
 *
 */
public class Node<E> {

    private Node<E> next, previous;
    private E element;

    /**
     * Constructor, initializes private fields to default values.
     */
    public Node(){

    }

    /**
     * Constructor, initializes only the passed parameter.
     * @param element
     */
    public Node(E element){
        this.element = element;
    }

    /**
     * Constructor, initializes private fields using passed parameters.
     * @param element
     * @param previousNode
     * @param nextNode
     */
    public Node(E element, Node<E> previousNode, Node<E> nextNode){
        this.element = element;
        this.previous = previousNode;
        this.next = nextNode;

    }

    /**
     * @return the element(data).
     */
    public E getElement() {
        return element;
    }

    /**
     * @return the reference to previous node.
     */
    public Node<E> getPrevious() {
        return previous;
    }

    /**
     * @return the reference to next node.
     */
    public Node<E> getNext() {
        return next;
    }

    /**
     * sets the element(data).
     * @param element
     */
    public void setElement(E element) {
        this.element = element;
    }

    /**
     * sets the reference to previous node.
     * @param previous
     */
    public void setPrevious(Node<E> previous) {
        this.previous = previous;
    }

    /**
     * sets the reference to next node.
     * @param next
     */
    public void setNext(Node<E> next) {
        this.next = next;
    }
}
