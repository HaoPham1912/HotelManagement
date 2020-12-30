package com.h2.hotelmangement.api;

import com.h2.hotelmangement.entity.Account;
import com.h2.hotelmangement.entity.Bed;
import com.h2.hotelmangement.entity.Customer;
import com.h2.hotelmangement.entity.Employee;
import com.h2.hotelmangement.model.dto.BedDTO;
import com.h2.hotelmangement.model.dto.CustomerDTO;
import com.h2.hotelmangement.model.mapper.CustomerMapper;
import com.h2.hotelmangement.service.AccountService;
import com.h2.hotelmangement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
public class CustomerAPI {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AccountService accountService;

    private CustomerMapper customerMapper = new CustomerMapper();

    @GetMapping("/customer")
    public ResponseEntity<Map<String, Object>> getAllCustomerInfo(@RequestParam(required = false) String name,
                                                                  @RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
                                                                  @RequestParam(value = "size", defaultValue = "3") int size){
        try {
            List<Customer> customerList = new ArrayList<Customer>();
            Page<Customer> customerPage;
            if (name == null) {
                customerPage = customerService.findAllCustomer(pageNo, size);
            } else {
                customerPage = customerService.findAllCustomerByName(name, pageNo, size);
            }
            customerList = customerPage.getContent();
            System.out.println(customerList.get(0).toString());
            List<CustomerDTO> customerDTOList = customerMapper.listCustomerEnittyToDto(customerList);
            Map<String, Object> response = new HashMap<>();
            response.put("customers", customerDTOList);
            response.put("currentPage", customerPage.getNumber());
            response.put("totalItems", customerPage.getTotalElements());
            response.put("totalPages", customerPage.getTotalPages());
            //List<BedDTO> bedDTOList = bedMapper.listBedEntityToDto(bedList);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable String id){
        Long cusId = Long.valueOf(id);
        Customer customer = customerService.getCustomerById(cusId);

        CustomerDTO customerDTO = customerMapper.customerEntityToDto(customer);

        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<HttpStatus> updateCustomerInfor(@PathVariable String id, @RequestBody CustomerDTO customerDTO){
        Long cusId = Long.valueOf(id);
        Customer customer = customerService.getCustomerById(cusId);
        if(customer != null){
            customer.setEmail(customerDTO.getEmail());
            customer.setIdCard(customerDTO.getIdCard());
            customer.setName(customerDTO.getName());
            customer.setPhone(customerDTO.getPhone());

            customerService.save(customer);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    @PostMapping("customer")
    public ResponseEntity<HttpStatus> addnewEmployee(@RequestBody CustomerDTO customerDTO){
        Customer customer = new Customer();
        customer.setCusCode(customerDTO.getCustomerCode());
        customer.setEmail(customerDTO.getEmail());
        customer.setName(customerDTO.getName());
        customer.setPhone(customerDTO.getPhone());
        customer.setCustomerType(null);
        customer.setAccountCus(null);
        customerService.save(customer);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<HttpStatus> deleteEmp(@PathVariable("id") String id) {
        Long cusId = Long.valueOf(id);
        Customer customer = customerService.getCustomerById(cusId);
        if(customer != null){
            try {
                customerService.deleteCustomer(cusId);
                Account account = customer.getAccountCus();
                if(account != null){
                    Boolean status = account.getStatus();
                    account.setStatus(!status);
                    accountService.save(account);
                }
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
