package queue;

public class ArrayQueue<E>
{
    private E[] elements;
    private int front;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayQueue()
    {
        elements = (E[]) new Object[10];
        front = 0;
        size = 0;
    }

    public void add(E element)
    {
        if (size == elements.length)
        {
            grow();
        }

        int back = (front + size) % elements.length;
        elements[back] = element;
        size++;
    }

    public E remove()
    {
        if (isEmpty())
        {
            throw new QueueException("Queue is empty");
        }

        E element = elements[front];
        elements[front] = null;
        front = (front + 1) % elements.length;
        size--;

        return element;
    }

    public E peek()
    {
        if (isEmpty())
        {
            throw new QueueException("Queue is empty");
        }

        return elements[front];
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public int size()
    {
        return size;
    }

    @SuppressWarnings("unchecked")
    private void grow()
    {
        E[] bigger = (E[]) new Object[elements.length * 2];

        for (int i = 0; i < size; i++)
        {
            bigger[i] = elements[(front + i) % elements.length];
        }

        elements = bigger;
        front = 0;
    }

    public String toString()
    {
        String result = "[";

        for (int i = 0; i < size; i++)
        {
            result += elements[(front + i) % elements.length];

            if (i < size - 1)
            {
                result += ", ";
            }
        }

        return result + "]";
    }
}