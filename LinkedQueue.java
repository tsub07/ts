package queue;

/**
   A first-in, first-out queue built from linked nodes.
   Elements are added at the back and removed from the front.
*/
public class LinkedQueue<E>
{
    private static class Node<E>
    {
        private E data;
        private Node<E> next;

        public Node(E data)
        {
            this.data = data;
            this.next = null;
        }
    }

    private Node<E> first;
    private Node<E> last;
    private int size;

    public LinkedQueue()
    {
        first = null;
        last = null;
        size = 0;
    }

    public void add(E element)
    {
        Node<E> newNode = new Node<>(element);

        if (isEmpty())
        {
            first = newNode;
            last = newNode;
        }
        else
        {
            last.next = newNode;
            last = newNode;
        }

        size++;
    }

    public E remove()
    {
        if (isEmpty())
        {
            throw new QueueException("Queue is empty");
        }

        E removedElement = first.data;
        first = first.next;
        size--;

        if (isEmpty())
        {
            last = null;
        }

        return removedElement;
    }

    public E peek()
    {
        if (isEmpty())
        {
            throw new QueueException("Queue is empty");
        }

        return first.data;
    }

    public int size()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public String toString()
    {
        String result = "[";
        Node<E> current = first;

        while (current != null)
        {
            result = result + current.data;

            if (current.next != null)
            {
                result = result + ", ";
            }

            current = current.next;
        }

        return result + "]";
    }

    public static void main(String[] args)
    {
        LinkedQueue<String> q = new LinkedQueue<>();

        System.out.println(q.isEmpty() + " Expected: true");

        q.add("Frodo");
        q.add("Sam");
        q.add("Pippin");

        System.out.println(q + " Expected: [Frodo, Sam, Pippin]");
        System.out.println(q.peek() + " Expected: Frodo");
        System.out.println(q.remove() + " Expected: Frodo");
        System.out.println(q.size() + " Expected: 2");

        q.remove();
        q.remove();

        System.out.println(q + " " + q.isEmpty() + " Expected: [] true");

        q.add("Merry");
        System.out.println(q + " Expected: [Merry]");

        q.remove();

        try
        {
            q.remove();
            System.out.println("No exception Expected: QueueException");
        }
        catch (QueueException e)
        {
            System.out.println("QueueException Expected: QueueException");
        }
    }