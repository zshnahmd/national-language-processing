 

/**
 *
 * @author User
 */
class DictEntry<K, V> {
    private K key;
    private V value;

    public DictEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "(" +  key + ", " + value + ')';
    }
    

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }


    
    
}
