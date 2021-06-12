package qlyhocvienttav.Model.DTO;

public class Room {
    String roomId;
    String roomName;
    int capacity;

    public Room(String roomId, String roomName, int capacity) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.capacity = capacity;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean checkContain(String str){
//          id.toUpperCase().contains(str.toUpperCase()) == true 
//                ||
        if (
            roomId.toUpperCase().contains(str.toUpperCase())==true ||
            roomName.toUpperCase().contains(str.toUpperCase())==true ||
            String.valueOf(capacity).toUpperCase().contains(str.toUpperCase())==true
        ){        
            return true;
        }
        else{
            return false;
        }
    }




}
