package com.h2.hotelmangement.repository;

import com.h2.hotelmangement.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@Transactional
public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findRoomByRoomCode(String roomCode);

    @Query("SELECT r FROM Room r WHERE r.roomId not in :roomIdList and r.roomBranch.branchCode in :branchCode")
    Optional<Set<Room>> findRoomAvailable(Set<Long> roomIdList, String branchCode);

    Optional<Set<Room>> findRoomByRoomBranch(String branchCode);

}
