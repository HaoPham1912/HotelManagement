package com.h2.hotelmangement.api;

import com.h2.hotelmangement.entity.CancelPolicy;
import com.h2.hotelmangement.model.dto.CancelPolicyDTO;
import com.h2.hotelmangement.model.mapper.CancelPolicyMapper;
import com.h2.hotelmangement.service.CancelPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.h2.hotelmangement.common.util.CommonConstants.PREFIX_API;

@RestController
@RequestMapping(PREFIX_API)
@CrossOrigin(origins = "http://localhost:3454")
public class CancelPolicyAPI {

    @Autowired
    private CancelPolicyService cancelPolicyService;

    public CancelPolicyMapper cancelPolicyMapper = new CancelPolicyMapper();

    @GetMapping("/policy")
    public ResponseEntity<List<CancelPolicyDTO>> getAllPolicy(){
        List<CancelPolicy> cancelPolicyList = cancelPolicyService.findAll();
        List<CancelPolicyDTO> cancelPolicyDTOList = cancelPolicyMapper.convertListEntityToDto(cancelPolicyList);
        return new ResponseEntity<>(cancelPolicyDTOList, HttpStatus.OK);
    }
}
