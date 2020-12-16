package com.h2.hotelmangement.api;

import com.h2.hotelmangement.entity.Bed;
import com.h2.hotelmangement.model.dto.BedDTO;
import com.h2.hotelmangement.model.mapper.BedMapper;
import com.h2.hotelmangement.service.BedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.h2.hotelmangement.common.util.CommonConstants.PREFIX_API;

@CrossOrigin(origins = "http://localhost:3454")
@RestController
@RequestMapping(PREFIX_API)
public class BedAPI {

    @Autowired
    private BedService bedService;

    public BedMapper bedMapper = new BedMapper();


    @GetMapping("/bed")
    public ResponseEntity<List<BedDTO>> getAllBed(){
        List<Bed> bedList = bedService.findAllBed();
        List<BedDTO> bedDTOList = bedMapper.listBedEntityToDto(bedList);
        return new ResponseEntity<>(bedDTOList, HttpStatus.OK);
    }

    @PostMapping("/bed")
    public void addBed(@RequestBody Bed bed){
        bedService.save(bed);
    }
}
