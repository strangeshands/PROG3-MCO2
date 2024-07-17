public class DeluxeRoom extends Room {
    public DeluxeRoom(String name) {
        super(name);
        setRoomRate(1.20f);
        setPrice(RoomPrice * RoomRate);
    }
}