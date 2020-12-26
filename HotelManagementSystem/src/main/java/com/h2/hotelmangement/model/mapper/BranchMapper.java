package com.h2.hotelmangement.model.mapper;

import com.h2.hotelmangement.entity.Branch;
import com.h2.hotelmangement.entity.Employee;
import com.h2.hotelmangement.model.dto.BranchDTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BranchMapper {

    public BranchDTO convertEntityToDto(Branch branch){
        BranchDTO branchDTO = new BranchDTO();
        Set<String> empCodes = new HashSet<>();

        branchDTO.setBranchId(branch.getBranchId().toString());
        branchDTO.setAddress(branch.getAddress());
        branchDTO.setLocation(branch.getLocation());
        branchDTO.setBranchCode(branch.getBranchCode());
        branchDTO.setDescription(branch.getDescription());
        branchDTO.setBranchName(branch.getName());
        branchDTO.setMainImage(branch.getMainImage());
        branchDTO.setRating(String.valueOf(branch.getRating()));
        branchDTO.setStatus(String.valueOf(branch.getStatus()));
        for ( Employee employee: branch.getEmployeeSet()) {
            empCodes.add(employee.getEmpCode());
        }
        branchDTO.setEmpCodeSet(empCodes);

        return branchDTO;
    }

    public List<BranchDTO> convertListBranchEntityToDto(List<Branch> branchList){
        List<BranchDTO> branchDTOList = new ArrayList<>();
        for (Branch branch:
                branchList) {
            BranchDTO branchDTO = convertEntityToDto(branch);
            branchDTOList.add(branchDTO);
        }
        return branchDTOList;
    }

    public Branch convertDtoToEntity(BranchDTO branchDTO){
        Branch branch = new Branch();

        branch.setLocation(branchDTO.getLocation());
        branch.setBranchCode(branchDTO.getBranchCode());
        branch.setAddress(branchDTO.getAddress());
        branch.setDescription(branchDTO.getDescription());
        branch.setMainImage(branchDTO.getMainImage());
        branch.setName(branchDTO.getBranchName());
        Set<String> branchImage =  new HashSet<>(branchDTO.getThumbnailsBranchSet());
        branch.setThumbnailsHotelList(branchImage);
        return branch;
    }
}
