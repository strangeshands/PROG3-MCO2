public class ExecutiveRoom extends Room {
    public ExecutiveRoom(String name) {
        super(name);
        setRoomRate(1.35f);
        setPrice(RoomPrice * RoomRate);
    }
}