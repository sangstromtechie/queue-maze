import java.util.NoSuchElementException;

/**
 * Stack - A class for creating and manipulating a stack of nodes containing generic data of type E.
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
public class Stack<E> implements Cloneable {
    private Node<E> head;
    private int size;

    /**
     * Constructor, initializes private fields for size and head.
     */
    public Stack() {
    }

    /**
     * Creates a new Node with the new element and adds it to the top of the stack.
     * @param element
     */
    public void push(E element) {
        Node<E> newEle;
        if(isEmpty()) {
            newEle = new Node<E>(element, null, null);
            this.head = newEle;
        } else {
            newEle = new Node<E>(element, head, null);
            this.head.setNext(newEle);
            this.head = newEle;
        }
        this.size += 1;
    }

    /**
     * @return the top element on the stack without removing it from the data structure.
     */
    public E top() {
        if(isEmpty())
            throw  new NoSuchElementException("top() not allowed on Empty Stack!");

        E top = this.head.getElement();
        return top;
    }

    /**
     * @return the top element on the stack, removing it from the data structure.
     */
    public E pop() {
        if(isEmpty())
            throw new NoSuchElementException("pop() not allowed on Empty Stack!");

        E top = this.head.getElement();
        if(this.size > 1) {
            this.head = this.head.getPrevious();
            this.head.setNext(null);
        } else
            this.head = null;

        this.size -= 1;
        return top;

    }

    /**
     * Empty all elements from the list.
     */
    public void clear(){
        this.head = null;
        this.size = 0;
    }

    /**
     * @return the private size field.
     */
    public int getSize() {
        return size;
    }

    /**
     * @return the top Node.
     */
    public Node<E> getHead() {
        return head;
    }

    /**
     * @return true if the list is empty, else return false.
     */
    public boolean isEmpty(){
        return this.size == 0;
    }
}
