import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapMain {
    public static void main(String[] args) {

        List<Contact> phones = ContactData.getData("phone");
        List<Contact> emails = ContactData.getData("email");
        List<Contact> fullList= new ArrayList<>(phones);
        fullList.addAll(emails);
        fullList.forEach(System.out::println);
        System.out.println("--------------------------------");
        Map<String,Contact> contacts= new HashMap<>();
        for(Contact contact: fullList){
            contacts.put(contact.getName(), contact);
        }
        contacts.forEach((k,v) -> System.out.println("key = "+ k + ", value = "+ v));
        System.out.println("--------------------------------");
        System.out.println(contacts.get("Alice Johnson"));
        contacts.clear();
        for(Contact contact: fullList){
            Contact duplicate = contacts.put(contact.getName(),contact);
            if(duplicate != null){
//                System.out.println("Duplicate found for "+contact.getName());
//                System.out.println("Current = " + contact);
                contacts.put(contact.getName(),contact.mergeContactData((duplicate)));

            }
        }
        contacts.clear();
        contacts.forEach((k,v) -> System.out.println("key = "+ k + ", value = "+ v));
        System.out.println("---------------------");
        for(Contact contact: fullList){
            contacts.putIfAbsent(contact.getName(), contact);
        }
        contacts.forEach((k,v) -> System.out.println("key = "+ k + ", value = "+ v));

        contacts.clear();
        System.out.println("---------------------");
        fullList.forEach(contact -> contacts.merge(contact.getName(),contact, (previous,current) ->{
            Contact merged = previous.mergeContactData(current);
            return merged;
        }));


        for(String contactName: new String[] {"Daisy Duck", "Daffy Duck",});
    }
}
