package com.h2.hotelmangement.repository;

import com.h2.hotelmangement.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query("SELECT r FROM Room r WHERE r.roomId not in :roomIdList and r.roomBranch.branchCode Like %:branchCode% ")
    Set<Room> findRoomAvailable(Set<Long> roomIdList, String branchCode);

    Set<Room> findRoomByRoomBranch_BranchCode(String branchCode);

    Page<Room> findAllByRoomCodeContains(String roomCode, Pageable pageable);

    @Query("SELECT r FROM Room r WHERE r.roomId not in :roomIdList and r.roomBranch.branchCode Like %:branchCode%")
    Page<Room> findRoomAvailable2(Set<Long> roomIdList, String branchCode, Pageable pageable);

    Page<Room> findRoomByRoomBranch_BranchCode(String branchCode, Pageable pageable);


}
