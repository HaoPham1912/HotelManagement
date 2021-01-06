package com.h2.hotelmangement.api;

import com.h2.hotelmangement.entity.CancelPolicy;
import com.h2.hotelmangement.model.dto.CancelPolicyDTO;
import com.h2.hotelmangement.model.mapper.CancelPolicyMapper;
import com.h2.hotelmangement.service.CancelPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.h2.hotelmangement.common.util.CommonConstants.PREFIX_API;

@RestController
@RequestMapping(PREFIX_API)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CancelPolicyAPI {

    @Autowired
    private CancelPolicyService cancelPolicyService;

    public CancelPolicyMapper cancelPolicyMapper = new CancelPolicyMapper();

    @GetMapping("/policy")
    public ResponseEntity<List<CancelPolicyDTO>> getAllPolicy() {
        List<CancelPolicy> cancelPolicyList = cancelPolicyService.findAll();
        List<CancelPolicyDTO> cancelPolicyDTOList = cancelPolicyMapper.convertListEntityToDto(cancelPolicyList);
        return new ResponseEntity<>(cancelPolicyDTOList, HttpStatus.OK);
    }

    @GetMapping("/policy/{id}")
    public ResponseEntity<CancelPolicyDTO> getCancelPolicyById(@PathVariable String id) {
        int cancelId = Integer.parseInt(id);
        CancelPolicy cancelPolicy = cancelPolicyService.getById(cancelId);
        CancelPolicyDTO cancelPolicyDTO = cancelPolicyMapper.convertEntittyToDto(cancelPolicy);
        return new ResponseEntity<>(cancelPolicyDTO, HttpStatus.OK);
    }

    @PostMapping("/policy")
    public ResponseEntity<HttpStatus> addCancelPolicy(@RequestBody CancelPolicyDTO cancelPolicyDTO) {
        System.out.println(cancelPolicyDTO.toString());
        CancelPolicy cancelPolicy = cancelPolicyMapper.covertDtoToEntity(cancelPolicyDTO);

        System.out.println(cancelPolicy.toString());

        cancelPolicyService.save(cancelPolicy);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/policy/{id}")
    public ResponseEntity<HttpStatus> updateCancelPolicy(@PathVariable String id, @RequestBody CancelPolicyDTO cancelPolicyDTO) {
        int policyId = Integer.parseInt(id);
        CancelPolicy cancelPolicy = cancelPolicyService.getById(policyId);
        if (cancelPolicy != null) {
            cancelPolicy.setCode(cancelPolicyDTO.getPolicyCode());
            cancelPolicy.setTitle(cancelPolicyDTO.getTitle());
            cancelPolicy.setDetail(cancelPolicyDTO.getDetail());
            cancelPolicy.setDayLong(Integer.parseInt(cancelPolicyDTO.getRangeDate()));

            cancelPolicyService.save(cancelPolicy);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
