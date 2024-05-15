package com.farkhod.hotelmanagement.service.implementation;

import com.farkhod.hotelmanagement.exception.ResourceNotFoundException;
import com.farkhod.hotelmanagement.model.Room;
import com.farkhod.hotelmanagement.repository.RoomRepository;
import com.farkhod.hotelmanagement.service.IRoomService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.math.BigDecimal;
import java.sql.Blob;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements IRoomService {

    private final RoomRepository roomRepository;

    @SneakyThrows
    @Override
    public Room addNewRoom(MultipartFile photo, String roomType, BigDecimal roomPrice
    ) {
        Room room = new Room();
        room.setRoomType(roomType);
        room.setRoomPrice(roomPrice);
        if (!photo.isEmpty()) {
            byte[] photoBytes = photo.getBytes();
            Blob photoBlob = new SerialBlob(photoBytes);
            room.setPhoto(photoBlob);
        }
        return roomRepository.save(room);
    }

    @Override
    public List<String> getAllRoomTypes() {
        return roomRepository.findDistinctRoomTypes();
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @SneakyThrows
    @Override
    public byte[] getRoomPhotoByRoomId(Long roomId) {
        Optional<Room> theRoom = roomRepository.findById(roomId);
        if (theRoom.isEmpty()) {
            throw new ResourceNotFoundException("Sorry Room not found");
        }
        Blob photoBlob = theRoom.get().getPhoto();
        if (photoBlob != null) {
            return photoBlob.getBytes(1, (int) photoBlob.length());
        }
        return null;
    }


}
