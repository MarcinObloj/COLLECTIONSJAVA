import java.util.*;

public class Contact {
    String name;
    Set<String> emails;
    Set<String> phones;

    public Contact(String name) {
        this.name = name;
        this.emails = new HashSet<>();
        this.phones = new HashSet<>();
    }

    public Contact(String name, String email) {
       this(name);
        if(email != null){
            emails.add(email);
        }

    }
    public Contact(String name, long phone) {
        this(name);
        String phoneNumber = String.valueOf(phone);
        if(phoneNumber.length() < 9){
            System.out.println("The phone number has to be greater than 9 ");
            return;
        }

        this.phones.add("("+phoneNumber.substring(0,3)+")" +phoneNumber.substring(3,6)+ "-"+phoneNumber.substring(6) );


    }



    public Contact(String name, String email, long phone) {
       this(name,phone);
        if(email != null){
            emails.add(email);
        }

    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "%s : %s %s".formatted(this.name, this.emails, this.phones);
    }
    public Contact mergeContactData(Contact contact){
        Contact newContact = new Contact(this.name);
      newContact.phones.addAll(contact.phones);
      newContact.phones.addAll(this.phones);
      newContact.emails.addAll(contact.emails);
      newContact.emails.addAll(this.emails);
      return newContact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        return getName().equals(contact.getName());
    }

    @Override
    public int hashCode() {
        return 31 * getName().hashCode();
    }
    public void addEmail(String companyName){
        String[] names = name.split(" ");
        String email= "%c%s@%s.com".formatted(name.charAt(0),names[names.length-1],companyName.replaceAll(" ","").toLowerCase());
        emails.add(email);
    }
}

class ContactData{
    private static String phoneData = """
  John Doe, 1234567890
  Jane Smith, 9876543210
  John Doe, 1234509876
  Alice Johnson, 5551234567
  Bob Brown, 1112223333
  Alice Johnson, 5557654321
  Bob hohnson, 5557654321
  Job hohnson, 5557654321
  Alice Johnson, 5557654321
  Jane Smith, 9876501234
  Alice Johnson, 5557654321
  """;

    private static String emailData = """
  John Doe, john.doe@example.com
  John Doe, john.doe@example.com
  John Doe, john.doe@example.com
  Jane Smith, jane.smith@example.com
  John Doe, john.doe.work@example.com
  Alice Johnson, alice.j@example.com
  Bob Brown, bob.brown@example.com
  Jane Smith, jane.smith.work@example.com
  Alice Johnson, alice.j.work@example.com
  """;

    public static List<Contact> getData(String type){
        ArrayList<Contact> dataList = new ArrayList<>();
        Scanner scanner = new Scanner(type.equals("phone") ? phoneData : emailData);
        while(scanner.hasNextLine()){
           String[] data = scanner.nextLine().split(",");
           String name = data[0].trim();
           if(type.equals("phone")){
               dataList.add(new Contact(name, Long.parseLong(data[1].trim())));
//               System.out.println(dataList);
           }else if(type.equals("email")){
               dataList.add(new Contact(name, data[1].trim()));
//               System.out.println(dataList);
           }


        }
        return dataList;
    }
    public static void printData(String header, Collection<Contact> contacts) {
        System.out.println("\n=== " + header + " ===");
        contacts.forEach(System.out::println);

    }
}