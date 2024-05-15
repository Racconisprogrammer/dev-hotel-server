package com.farkhod.hotelmanagement.service;


import com.farkhod.hotelmanagement.model.BookedRoom;

import java.util.List;

public interface IBookedRoomService {

    List<BookedRoom> getAllBookingsByRoomId(Long roomId);
}
