// Tested by Pradipti
package queue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PartyLine
{
    private ArrayQueue<String> line;
    private int giftsGiven;

    public PartyLine(String fileName) throws FileNotFoundException
    {
        line = new ArrayQueue<>();
        giftsGiven = 0;

        Scanner scanner = new Scanner(new File(fileName));

        while (scanner.hasNextLine())
        {
            String event = scanner.nextLine().trim();

            if (!event.isEmpty())
            {
                processLine(event);
            }
        }

        scanner.close();
    }

    public void processLine(String event)
    {
        if (event.startsWith("ARRIVE:"))
        {
            String name = event.substring(7);
            line.add(name);
        }
        else if (event.equals("GIFT"))
        {
            if (line.isEmpty())
            {
                System.out.println("No one is waiting for a present");
            }
            else
            {
                String name = line.remove();
                System.out.println("Bilbo gives a present to " + name);
                giftsGiven++;
            }
        }
    }

    public int positionOf(String name)
    {
        int position = -1;
        int lineSize = line.size();

        for (int i = 1; i <= lineSize; i++)
        {
            String guest = line.remove();

            if (guest.equals(name) && position == -1)
            {
                position = i;
            }

            line.add(guest);
        }

        return position;
    }

    public ArrayQueue<String> getLine()
    {
        return line;
    }

    public int getGiftsGiven()
    {
        return giftsGiven;
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        PartyLine party = new PartyLine("PartyLine.txt");

        System.out.println("Gifts given: " + party.getGiftsGiven());
        System.out.println("Still waiting: " + party.getLine());
        System.out.println("Frodo is number " + party.positionOf("Frodo") + " in line");
        System.out.println("Gandalf is number " + party.positionOf("Gandalf") + " in line");
        System.out.println("Still waiting: " + party.getLine());
    }
}