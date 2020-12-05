package com.h2.hotelmangement.model.mapper;

import com.h2.hotelmangement.entity.CancelPolicy;
import com.h2.hotelmangement.model.dto.CancelPolicyDTO;

import java.util.ArrayList;
import java.util.List;

public class CancelPolicyMapper {

    public CancelPolicyDTO convertEntittyToDto(CancelPolicy cancelPolicy){
        CancelPolicyDTO cancelPolicyDTO = new CancelPolicyDTO();
        cancelPolicyDTO.setPolicyId(String.valueOf(cancelPolicy.getId()));
        cancelPolicyDTO.setPolicyCode(cancelPolicy.getCode());
        cancelPolicyDTO.setTitle(cancelPolicy.getTitle());
        cancelPolicyDTO.setDetail(cancelPolicy.getDetail());
        return cancelPolicyDTO;
    }

    public List<CancelPolicyDTO> convertListEntityToDto(List<CancelPolicy> cancelPolicyList){
        List<CancelPolicyDTO> cancelPolicyDTOList = new ArrayList<>();
        for (CancelPolicy cancelPolicy:
                cancelPolicyList ) {
            CancelPolicyDTO cancelPolicyDTO = convertEntittyToDto(cancelPolicy);
            cancelPolicyDTOList.add(cancelPolicyDTO);
        }
        return cancelPolicyDTOList;
    }
}
