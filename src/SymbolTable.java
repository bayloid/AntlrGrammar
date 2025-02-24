import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private final Map<String,Object> table;

    public SymbolTable() {
        this.table = new HashMap<>();
    }

    public void put(String varName, Object value){
        table.put(varName,value);
    }

    public Object get(String varName){
        return table.containsKey(varName);
    }

    public void print(){
        System.out.println("=== Symbol Table After Evaluation ===");
        for(Map.Entry<String, Object> entry : table.entrySet()) {
            System.out.println(entry.getKey() + " = "+entry.getValue());
        }
    }
    
    public int getOrDefault(String key, Object defaultValue){
        return (int) table.getOrDefault(key,defaultValue);
    }

    public Boolean containsKey(String key){
        return containsKey(key);
    }
}
