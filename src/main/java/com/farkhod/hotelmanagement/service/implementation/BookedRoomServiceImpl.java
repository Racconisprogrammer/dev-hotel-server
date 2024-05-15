package com.farkhod.hotelmanagement.service.implementation;


import com.farkhod.hotelmanagement.model.BookedRoom;
import com.farkhod.hotelmanagement.service.IBookedRoomService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookedRoomServiceImpl implements IBookedRoomService {

    @Override
    public List<BookedRoom> getAllBookingsByRoomId(Long roomId) {
        return null;
    }
}
