//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//public class Main {
//    public static void main(String[] args) {
////        System.out.println("Hello world!");
////        String aText= "Hello";
////        String bText = "Hello";
////        String cText = String.join("l", "He","lo");
////        String eText= "hello";
////        System.out.println(cText);
////        List<String> hellos= Arrays.asList(aText, bText,cText,eText);
////
////        hellos.forEach(s -> System.out.println(s + ": " + s.hashCode()));
////        Set<String> mySet = new HashSet<>(hellos);
////        System.out.println("mySet = "+ mySet);
////        System.out.println("mySet = "+ mySet.size());
////        for(String setValue: mySet){
////            System.out.println(setValue+ " : ");
////            for(int i = 0; i<hellos.size(); i++){
////                if(setValue == hellos.get(i)){
////                    System.out.println(i + ", ");
////                }
////            }
////            System.out.println();
////        }
////        PlayingCard aceHearts = new PlayingCard("Hearts","Ace");
////        PlayingCard kingClubs = new PlayingCard("Clubs","King");
////        PlayingCard queenSpades = new PlayingCard("Spades","Queen");
////        PlayingCard queenSpades1 = new PlayingCard("Spades","Queen");
////        List<PlayingCard> cards= Arrays.asList(aceHearts,kingClubs,queenSpades,queenSpades1);
////        cards.forEach(s -> System.out.println(s + ": "+ s.hashCode()));
////
////        Set<PlayingCard> deck = new HashSet<>();
////        for(PlayingCard c: cards){
////            if(!deck.add(c)){
////                System.out.println("Duplicate card: " + c);
////            }
////        }
////        System.out.println(deck);
////        var contact= new Contact("Siemanko","siemanko@gmail.com",6963490576L);
////        var contact1= new Contact("Siemanko","siemanko1321@gmail.com",6963490573216L);
////        contact.mergeContactData(contact1);
////        System.out.println(contact);
////        System.out.println(contact1.getName().hashCode());
////        Contact contact2 = new Contact("Alice", "alice@example.com", 1234567890L);
////        Contact contact3 = new Contact("Alice", "alice@example.com", 1234567890L);
////        Contact mergedContact=contact2.mergeContactData(contact3);
////        System.out.println(mergedContact);
////        System.out.println(contact2.hashCode());
////        System.out.println(contact3.hashCode());
//
////        List<Contact> emails = ContactData.getData("email");
////        System.out.println("---------------------------");
////        List<Contact> phones = ContactData.getData("phone");
////        emails.forEach(System.out::println);
////
////        phones.forEach(System.out::println);
////        Set<Contact> emailContacts = new HashSet<>(emails);
////        Set<Contact> phoneContacts = new HashSet<>(phones);
////        ContactData.printData("Phone Contacts", phoneContacts);
////        ContactData.printData("Email Contacts", emailContacts);
////
////        int index = emails.indexOf(new Contact("John Doe"));
////        Contact johnDoe= emails.get(index);
////        johnDoe.addEmail("Sherwood Forest");
////        System.out.println(johnDoe);
////        Set<Contact> unionAB= new HashSet<>();
////        unionAB.addAll(emailContacts);
////        unionAB.addAll(phoneContacts);
////        ContactData.printData("Union of Email and Phone Contacts", unionAB);
////        Set<Contact> intersectAB = new HashSet<>(emailContacts);
////        intersectAB.retainAll(phoneContacts);
////        ContactData.printData("Intersect email (A) and phones (B)",intersectAB);
////        Set<Contact> intersectBA = new HashSet<>(phoneContacts);
////        intersectBA.retainAll(emailContacts);
////        ContactData.printData("Intersect email (B) and phones (A)",intersectBA);
//
////        Set<Contact> AMinusB = new HashSet<>(emails);
////        AMinusB.removeAll(phoneContacts);
////        ContactData.printData(" email (A) - phones (A)",AMinusB);
//         Set<Task> Bobtasks = TaskData.getTasksByAssignee("bob");
//        Bobtasks.forEach(System.out::println);
//         Set<Task> AnnTasks = TaskData.getTasksByAssignee("ann");
//        System.out.println("-------------------------");
//        AnnTasks.forEach(System.out::println);
//        Set<Task> AnnAndBobTasks = new HashSet<>(Bobtasks);
//        System.out.println("-----------------------");
//        AnnAndBobTasks.addAll(AnnTasks);
//        AnnAndBobTasks.forEach(System.out::println);
//
//
//    }
//
//}


import java.util.*;

public class Main {

    public static void main(String[] args) {

        Set<Task> tasks = TaskData.getTasks("all");
        sortAndPrint("All Tasks", tasks);

        Comparator<Task> sortByPriority = Comparator.comparing(Task::getPriority).reversed();
        Set<Task> annsTasks = TaskData.getTasks("Ann");
        Set<Task> BobTasks = TaskData.getTasks("Bob");
        Set<Task> CarolsTasks = TaskData.getTasks("carols");
        sortAndPrint("Ann's Tasks", annsTasks, sortByPriority);
        sortAndPrint("Bob's Tasks", BobTasks, sortByPriority);
        List<Set<Task>> listOfTasks = List.of(annsTasks,BobTasks,CarolsTasks,tasks);
        Set<Task> allAssignedTasks = getUnion(listOfTasks);
        sortAndPrint("Union of All Tasks", allAssignedTasks);
        Set<Task> allAssignedTasksIntersectAnnAndBob = getIntersect(annsTasks, BobTasks);
        sortAndPrint("Intersection of Ann and Bob's Tasks", allAssignedTasksIntersectAnnAndBob);
        Set<Task> getDIff = getDifference(annsTasks, BobTasks);
        sortAndPrint("Difference", getDIff);
    }

    private static void sortAndPrint(String header, Collection<Task> collection) {
        sortAndPrint(header, collection, null);
    }
    private static Set<Task> getIntersect(Set<Task> task1, Set<Task> task2){
        Set<Task> result = new HashSet<Task>(task1);
        result.retainAll(task2);
        return result;

    }
    private static Set<Task> getDifference(Set<Task> task1, Set<Task> task2){
        Set<Task> result = new HashSet<>(task1);
        result.removeAll(task2);
        return result;

    }

    private static void sortAndPrint(String header, Collection<Task> collection,
                                     Comparator<Task> sorter) {

        String lineSeparator = "_".repeat(90);
        System.out.println(lineSeparator);
        System.out.println(header);
        System.out.println(lineSeparator);

        List<Task> list = new ArrayList<>(collection);
        list.sort(sorter);
        list.forEach(System.out::println);
    }

    public static Set<Task> getUnion(List<Set<Task>> sets){
        Set<Task> union = new HashSet<>();
        for (Set<Task> set : sets) {
            union.addAll(set);
        }
        return union;
    }
}
