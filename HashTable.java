import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HashTable {
    private static int tableSize;
    private static HashEntry[] hashTable;

    public HashTable(int size) {
        tableSize = size;
        hashTable = new HashEntry[tableSize];
    }

    public void insert(int key, String name, String address) {
        int hash = hashFunction(key);

        int i = 0;
        while (hashTable[hash] != null) {
            // Resolving collision using quadratic probing
            hash = (hash + (i * i)) % tableSize;
            i++;
        }

        hashTable[hash] = new HashEntry(key, name, address);
    }

    public void displayTable() {
        System.out.println("Hash Table:");
        System.out.println("Index\tHash\tKey\tName\tAddress");
        for (int i = 0; i < tableSize; i++) {
            HashEntry entry = hashTable[i];
            System.out.print(i + "\t");
            if (entry != null) {
                int hash = hashFunction(entry.getKey());
                System.out.println(hash + "\t" + entry.getKey() + "\t" + entry.getName() + "\t" + entry.getAddress());
            } else {
                System.out.println("-\t-\t-\t-");
            }
        }
    }

    private int hashFunction(int key) {
        // Using h(x) = x mod m as the hash function
        return key % tableSize;
    }

    public static void main(String[] args) {
        // Reading data from EMPLEADO.TXT file
        String fileName = "C:\\\\Users\\\\victor\\\\Desktop\\\\EMPLEADO.TXT";
        int employeeCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            // Read the employee count from the first line of the file
            employeeCount = Integer.parseInt(reader.readLine());

            // Calculate the table size (m)
            tableSize = (int) (employeeCount + (0.4 * employeeCount));

            // Create the hash table
            HashTable hashTable = new HashTable(tableSize);

            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into code, name, and address
                String[] data = line.split(",");
                int code = Integer.parseInt(data[0]);
                String name = data[1];
                String address = data[2];

                // Insert the employee into the hash table
                hashTable.insert(code, name, address);
            }

            // Display the resulting hash table
            hashTable.displayTable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
