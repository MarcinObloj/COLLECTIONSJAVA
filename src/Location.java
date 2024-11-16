import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

enum Compass{
    NORTH,SOUTH,EAST,WEST;
    public static Compass fromString(String input) {
        return switch (input.toLowerCase()) {
            case "north", "n" -> NORTH;
            case "south", "s" -> SOUTH;
            case "east", "e" -> EAST;
            case "west", "w" -> WEST;
            default -> throw new IllegalArgumentException("Invalid compass direction: " + input);
        };
    }

    public String toString(){
        return name().toLowerCase();
    }
}
public class Location {
    static String description;
    Map<Compass,String> nextPlaces;

    public Location(String description, Map<Compass, String> nextPlaces) {
        this.description = description;
        this.nextPlaces = nextPlaces;
    }
    public String getDescription() {
        return description;
    }

    public Map<Compass, String> getNextPlaces() {
        return nextPlaces;
    }
    public String getNextLocation(Compass direction){
        return nextPlaces.getOrDefault(direction, null);
    }

    @Override
    public String toString() {
        return description;
    }
}
class AdventureGame{
    String currentLocation;
    Map<String, Location> adventureMap;
    public AdventureGame(String initialData) {
        this.adventureMap = new HashMap<>();
        loadLocations(initialData);
        currentLocation="road";
    }
    void loadLocations(String data){

        String[] lines= data.split("\\R");
        for (String line : lines) {
            String[] parts = line.split(",",3);
            String description = parts[1].trim();
            String placeKey = parts[0].trim();
            Map<Compass, String> nextPlaces = loadDirections(parts[2]);

            adventureMap.put(placeKey, new Location(description,nextPlaces));
        }
        adventureMap.forEach((k,v) -> System.out.println(k + " " +  v));
    }
    Map<Compass,String> loadDirections(String direction){
        Map<Compass,String> directions = new HashMap<>();
        String[] parts = direction.split(",");
        for(var part : parts){
            String[] directionParts = part.split(":");
            Compass compass = Compass.fromString(directionParts[0].trim());
            String locationKey = directionParts[1].trim();
            directions.put(compass, locationKey);
        }
        System.out.println("Loaded directions: " + directions);
        return directions;
    }
    public void play(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Adventure Game!");
        while(true){
            Location location = adventureMap.get(currentLocation);
            System.out.println("you are staning at"+ location);
            System.out.println("Available directions: ");
            location.getNextPlaces().forEach((key, value) -> System.out.println("- " + key + " to " + value));
            System.out.println("Adventure Map:");


            String input = scanner.nextLine().trim().toLowerCase();
            System.out.print("Enter direction (or Q to quit): ");
            if (input.equals("q")) {
                System.out.println("Thanks for playing!");
                break;
            }
            Compass direction;
            try {
                direction = Compass.fromString(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid direction. Try again.");
                continue;
            }
            if (location.getNextPlaces().containsKey(direction)) {
                currentLocation = location.getNextPlaces().get(direction);
            } else {
                System.out.println("You can't go that way!");
            }
        }
    }
}

 class Main3 {
    public static void main(String[] args) {
        String gameData = """
                road,at the end of the road, W: hill, E:well house,S:valley,N:forest
                hill,on top of hill with a view in all directions,N:forest, E:road
                well house,inside a well house for a small spring,W:road,N:lake,S:stream
                valley,in a forest valley beside a tumbling stream,N:road,W:hill,E:stream
                forest,at the edge of a thick dark forest,S:road,E:lake
                lake,by an alpine lake surrounded by wildflowers,W:forest,S:well house
                stream,near a stream with a rocky bed,W:valley, N:well house
            """;

        AdventureGame game = new AdventureGame(gameData);
        game.play();
    }
}


