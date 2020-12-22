package com.h2.hotelmangement.api;

import com.h2.hotelmangement.entity.Bed;
import com.h2.hotelmangement.model.dto.BedDTO;
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
           List<BedDTO> bedDTOList = bedMapper.listBedEntityToDto(bedList);
           Map<String, Object> response = new HashMap<>();
           response.put("beds", bedDTOList);
           response.put("currentPage", bedPages.getNumber());
           response.put("totalItems", bedPages.getTotalElements());
           response.put("totalPages", bedPages.getTotalPages());
           //List<BedDTO> bedDTOList = bedMapper.listBedEntityToDto(bedList);
           return new ResponseEntity<>(response, HttpStatus.OK);
       }catch (Exception e){
           return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
       }
    }

    @PostMapping("/bed")
    public void addBed(@RequestBody Bed bed) {
        bedService.save(bed);
    }
}
