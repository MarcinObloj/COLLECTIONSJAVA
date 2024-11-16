//import com.sun.source.tree.Tree;
//
//import java.util.*;
//
//public class TreeSetMain {
//    public static void main(String[] args) {
//        List<Contact> phones = ContactData.getData("phone");
//        List<Contact> emails = ContactData.getData("emails");
////        NavigableSet<Contact> sorted = new TreeSet<>(phones);
//        Comparator<Contact> sort = Comparator.comparing(Contact::getName);
//        NavigableSet<Contact> sorted = new TreeSet<>(sort);
//        sorted.addAll(phones);
//        sorted.forEach(System.out::println);
//
//        NavigableSet<String> justNames = new TreeSet<>();
//        phones.forEach(c -> justNames.add(c.getName()));
//        System.out.println(justNames);
//
//        List<Contact> fullList= new ArrayList<>(phones);
//        fullList.addAll(emails);
//        fullList.sort(sorted.comparator());
//        System.out.println("------------");
//        fullList.forEach(System.out::println);
//
//        Contact daffy = new Contact("Daffy Duck");
//        Contact daisy = new Contact("Daisy Duck");
//        Contact snoopy = new Contact("Snoopy");
//        Contact archie = new Contact("Archie");
//        for(Contact c : List.of(daffy, daisy, snoopy, archie)){
//            System.out.printf("ceiling(%s)= %s%n", c.getName(), justNames.ceiling(c));
//        }
//
//
//    }
//
//}
