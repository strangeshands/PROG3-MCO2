package ClassFiles;


/**
 * Represents a Deluxe Room, a subclass of Room.
 */
public class DeluxeRoom extends Room {
    /**
     * Constructs a DeluxeRoom with the given name and base price.
     * Sets the room rate to 1.20 and the price to the base price.
     * 
     * @param name the name of the room
     * @param base the base price of the room
     */
    public DeluxeRoom(String name, float base) {
        super(name, base);
        setRoomRate(1.20f);
        setPrice(base);
    }



    /**
     * Returns a string representing the room name and type.
     * 
     * @return the room name and type (e.g. "Room Name -- Deluxe")
     */
    @Override
    public String getRoomNameType() {
        return getRoomName() + " -- Deluxe";
    }

    /**
     * Returns the type of the room.
     * 
     * @return the room type (always "Deluxe")
     */
    @Override
    public String getRoomType() {
        return "Deluxe";
    }
}
