package com.h2.hotelmangement.api;

import com.h2.hotelmangement.entity.Bed;
import com.h2.hotelmangement.entity.Services;
import com.h2.hotelmangement.model.dto.BedDTO;
import com.h2.hotelmangement.model.dto.ServicesDTO;
import com.h2.hotelmangement.model.mapper.BedMapper;
import com.h2.hotelmangement.service.BedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.h2.hotelmangement.common.util.CommonConstants.PREFIX_API;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(PREFIX_API)
public class BedAPI {

    @Autowired
    private BedService bedService;

    public BedMapper bedMapper = new BedMapper();


    @GetMapping("/bed")
    public ResponseEntity<Map<String, Object>> getAllBed(@RequestParam(required = false) String name,
                                                         @RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
                                                         @RequestParam(value = "size", defaultValue = "3") int size) {
        try {
            List<Bed> bedList = new ArrayList<Bed>();
            Page<Bed> bedPages;
            if (name == null) {
                bedPages = bedService.findAllBed(pageNo, size);
            } else {
                bedPages = bedService.findAllBedByName(name, pageNo, size);
            }
            bedList = bedPages.getContent();
            System.out.println(bedList.get(0).toString());
            List<BedDTO> bedDTOList = bedMapper.listBedEntityToDto(bedList);
            Map<String, Object> response = new HashMap<>();
            response.put("beds", bedDTOList);
            response.put("currentPage", bedPages.getNumber());
            response.put("totalItems", bedPages.getTotalElements());
            response.put("totalPages", bedPages.getTotalPages());
            //List<BedDTO> bedDTOList = bedMapper.listBedEntityToDto(bedList);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/bed")
    public ResponseEntity<HttpStatus> addBed(@RequestBody BedDTO bedDTO) {
        try {
            Bed bed = bedMapper.convetBedDTOToEntity(bedDTO);
            bedService.save(bed);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/bed/{id}")
    public ResponseEntity<HttpStatus> updateBedInfor(@PathVariable String id, @RequestBody BedDTO bedDTO) {
        Long bedId = Long.valueOf(id);
        Bed bed = bedService.getBedById(bedId);
        if (bed != null) {
            bed.setName(bedDTO.getName());
            bed.setPrice(Double.valueOf(bedDTO.getPrice()));
            bed.setAmountPeople(Integer.valueOf(bedDTO.getAmountPeople()));
            bed.setDescription(bedDTO.getDescription());

            bedService.save(bed);

            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/bed/{id}")
    public ResponseEntity<HttpStatus> disableBed(@PathVariable String id) {
        Long bedId = Long.valueOf(id);
        Bed bed = bedService.getBedById(bedId);
        try {
            bedService.delete(bedId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/bed/{id}")
    public ResponseEntity<BedDTO> getBedById(@PathVariable String id){
        Long bedId = Long.valueOf(id);
        Bed bed = bedService.getBedById(bedId);
        if(bed != null){
            BedDTO bedDTO= bedMapper.bedEntityToDto(bed);
            return  new ResponseEntity<>(bedDTO, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/all-bed")
    public ResponseEntity<List<BedDTO>> getAllBedNoPaging(){
        List<Bed> bedList = bedService.getAllBedList();
        List<BedDTO> bedDTOList = bedMapper.listBedEntityToDto(bedList);
        return new ResponseEntity<>(bedDTOList, HttpStatus.OK);
    }
}
