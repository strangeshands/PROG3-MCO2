package ClassFiles;


/**
 * Represents an Executive Room, a subclass of Room.
 */
public class ExecutiveRoom extends Room {
    /**
     * Constructs an ExecutiveRoom with the given name and base price.
     * Sets the room rate to 1.35 and the price to the base price.
     * 
     * @param name the name of the room
     * @param base the base price of the room
     */
    public ExecutiveRoom(String name, float base) {
        super(name, base);
        setRoomRate(1.35f);
        setPrice(base);
    }

    

    /**
     * Returns a string representing the room name and type.
     * 
     * @return the room name and type (e.g. "101 -- Executive")
     */
    @Override
    public String getRoomNameType() {
        return getRoomName() + " -- Executive";
    }

    /**
     * Returns the type of the room.
     * 
     * @return the room type (always "Executive")
     */
    @Override
    public String getRoomType() {
        return "Executive";
    }
}
