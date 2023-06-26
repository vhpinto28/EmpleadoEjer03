class HashEntry {
    private int key;
    private String name;
    private String address;

    public HashEntry(int key, String name, String address) {
        this.key = key;
        this.name = name;
        this.address = address;
    }

    public int getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}