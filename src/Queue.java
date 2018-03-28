import java.util.NoSuchElementException;

/**
 * Queue - A class for creating and manipulating a queue of nodes containing generic data of type E.
 *
 * <pre>
 *
 * Assignment:     #1
 * Course:         ADEV-3001
 * Date Created:   March 6th 2018
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
public class Queue<E> implements Cloneable {
    private Node<E> head, tail;
    private int size;

    /**
     * Constructor, initializes private fields for size and head.
     */
    public Queue() {
    }

    /**
     * Creates a new Node with the new element and adds it to the top of the stack.
     * @param element
     */
    public void enqueue(E element) {
        Node<E> newEle;
        if(isEmpty()) {
            newEle = new Node<E>(element, head, tail);
            this.head = newEle;
            this.tail = newEle;
        } else {
            newEle = new Node<E>(element, tail, null);
            this.tail.setNext(newEle);
            this.tail = newEle;
        }
        this.size += 1;
    }

    /**
     * @return the top element on the stack without removing it from the data structure.
     */
    public E front() {
        if(isEmpty())
            throw new IllegalArgumentException();

        E front = this.head.getElement();
        return front;
    }

    /**
     * @return the top element on the stack, removing it from the data structure.
     */
    public E dequeue() {
        if(!isEmpty()) {
            E front = this.head.getElement();
            if(this.size == 1) {
                this.head = null;
                this.tail = null;
            } else {
                this.head = this.head.getNext();
                this.head.setPrevious(null);
            }
            this.size -= 1;
            return front;
        } else {
            throw new IllegalArgumentException();
        }
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
     * @return the tail node
     */
    public Node<E> getTail() {
        return tail;
    }

    /**
     * @return true if the list is empty, else return false.
     */
    public boolean isEmpty(){
        return this.size == 0;
    }
}
