import java.util.ArrayList;
import java.util.NavigableSet;
import java.util.TreeSet;

public class Theathre {
    String theathreName;
    int seatsPerRow;
     NavigableSet<Seat> seats = new TreeSet<>();
    public Theathre(String theathreName,int numberOfRows, int numberOfSeats){
        this.theathreName = theathreName;
        if(numberOfRows>26){
            numberOfRows =26;
        }
        this.seatsPerRow = numberOfSeats/numberOfRows;

        for(int c = 0; c < numberOfRows; c++ ){
            char c1 = (char) ('A' + c);
            for(int i =0; i<seatsPerRow; i++){
                seats.add(new Seat(c1, i+1));
            }

        }
    }
    public void printSeatMap(){

        int i = 0;
        for(Seat s: seats){
            i++;
            System.out.print(s+ " "+ (s.reserved ? ":R " : ""));
            if(i%seatsPerRow == 0){
                System.out.println();
            }

        }
        System.out.println();
    }

    public NavigableSet<Seat> getSeats() {
        return seats;
    }
    public Seat getSeatByName(String name){
        for(var s: seats){
            if(s.seatNum.contains(name)){
                return s;
            }
        }
        return null;
    }

    public void reserveSeat(Seat seat){

        if(seat.reserved){

            var newSeat= seats.higher(seat);
            if(newSeat!=null){
                newSeat.reserved = true;
            }
            System.out.println("Seat is already reserved, you reserved " + newSeat);
            return;
        }
        if(seats.contains(seat)){
            seat.reserved = true;
        }

    }
    public void reserveSeat(char row, int seatNumber){
        for(var c : seats){
            if(c.seatNum.charAt(0)== row && seatNumber == Integer.parseInt(c.seatNum.substring(1))){
                if (c.reserved) {
                    System.out.println("This seat is already reserved, try to reserve another seat.");
                    return;
                }
                c.reserved = true;
                System.out.println("Seat " + c.seatNum + " has been successfully reserved.");
                return;
            }

        }

        System.out.println("Seat " + row + seatNumber + " does not exist.");
    }
    public void reserveAdjacentSeats(int placesToRes, char startRow, int startSeatNumber) {
        int reservedSeatsCount = 0;
        boolean reservationFailed = false;


        var firstUnreservedSeat = new ArrayList<Seat>();


        for (var r : seats) {

            if (r.seatNum.charAt(0) == startRow && Integer.parseInt(r.seatNum.substring(1)) >= startSeatNumber) {

                if (!r.reserved && reservedSeatsCount < placesToRes) {
                    r.reserved = true;
                    reservedSeatsCount++;
                    firstUnreservedSeat.add(r);


                    if (reservedSeatsCount == placesToRes) {
                        System.out.println("Successfully reserved " + placesToRes + " adjacent seats starting from " + startRow + startSeatNumber);
                        return;
                    }
                } else if (r.reserved) {
                    reservationFailed = true;
                    break;
                }
            }
        }


        if (reservationFailed) {
            for (var seat : firstUnreservedSeat) {
                seat.reserved = false;
            }
            System.out.println("Not enough available adjacent seats, reservation failed.");
        } else if (reservedSeatsCount < placesToRes) {
            System.out.println("Not enough available adjacent seats.");
        }
    }


    @Override
    public String toString() {
        return "Theathre{" +
                "seats=" + seats +
                '}';
    }

    class Seat implements Comparable<Seat>{
        String seatNum;
        public boolean reserved;
        public Seat(char row, int seatNumber) {
            this.seatNum= "%c%03d".formatted(row,seatNumber);
            this.reserved=false;
        }

        @Override
        public String toString() {
            return seatNum;
        }

        @Override
        public int compareTo(Seat o) {
            return this.seatNum.compareTo(o.seatNum);
        }
    }
}

class Main1{
    public static void main(String[] args) {
        Theathre royalTheatre = new Theathre("Royal Theatre", 10, 50);
//
//        var seats= royalTheatre.getSeats();
//        var seat = royalTheatre.getSeatByName("A001");
//        royalTheatre.reserveSeat(seat);
////        royalTheatre.reserveSeat(royalTheatre().Seat('A', 10));
//        royalTheatre.printSeatMap();
//        royalTheatre.reserveSeat(seat);
//        royalTheatre.printSeatMap();
//        royalTheatre.reserveSeat('A',3);
//        royalTheatre.printSeatMap();
//        System.out.println("Number of seats: " + royalTheatre.seats.size());
//        System.out.println("First seat: " + royalTheatre.seats.first());
//        System.out.println("Last seat: " + royalTheatre.seats.pollLast());
        System.out.println("Test 1: Rezerwacja 5 miejsc w rzędzie A zaczynając od A001");
        royalTheatre.reserveAdjacentSeats(20, 'A', 1);
        royalTheatre.printSeatMap();
    }
}