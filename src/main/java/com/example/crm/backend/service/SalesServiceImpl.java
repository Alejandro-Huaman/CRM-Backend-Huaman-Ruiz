package com.example.crm.backend.service;

import com.example.crm.backend.domain.salesAggregate.model.entity.Customer;
import com.example.crm.backend.domain.salesAggregate.model.entity.Sales;
import com.example.crm.backend.domain.salesAggregate.model.entity.Status;
import com.example.crm.backend.domain.salesAggregate.model.enumeration.StatusName;
import com.example.crm.backend.domain.salesAggregate.persistence.CustomerRepository;
import com.example.crm.backend.domain.salesAggregate.persistence.SalesRepository;
import com.example.crm.backend.domain.salesAggregate.persistence.StatusRepository;
import com.example.crm.backend.domain.salesAggregate.service.SalesService;
import com.example.crm.backend.domain.userAggregate.model.entity.Rol;
import com.example.crm.backend.domain.userAggregate.model.enumeration.RolName;
import com.example.crm.backend.domain.userAggregate.persistence.UserRepository;
import com.example.crm.shared.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SalesServiceImpl implements SalesService {
    List<Sales> getsalesbyStatusList;
    private static final String ENTITY = "Sales";
    private static final String ENTITY2 = "Customer";
    private static final String ENTITY3 = "User";

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StatusRepository statusRepository;

    public SalesServiceImpl() {
    }

    @Override
    public List<Sales> getSales() {
        return salesRepository.findAll();
    }

    @Override
    public Page<Sales> getSales(Pageable pageable) {
        return salesRepository.findAll(pageable);
    }

    @Override
    public Sales getSaleById(Long saleId) {
        return salesRepository.findById(saleId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, saleId));
    }

    @Override
    public List<Sales> getSaleByStatus(Long statusId) {
        List<Sales> listsales = salesRepository.findAll();
        List newlist = new ArrayList();

        return statusRepository.findById(statusId).map(status->{
            for(Sales sale:listsales){
                for (Status onestatus:sale.getStatus()){
                    String s=onestatus.getStatusname().name();

                    if (s.equals(status.getStatusname().name())){
                        newlist.add(sale);
                    }
                }
            }
            return newlist;
        }).orElseThrow(() -> new ResourceNotFoundException("Status", statusId));
    }

    @Override
    public Sales createSale(Long userId,Long customerId, Sales sale) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY2, customerId));

        return userRepository.findById(userId)
                .map(users -> {
                    Set<Status> status = new HashSet<>();
                    status.add(statusRepository.findByStatusname(StatusName.Qualification).get());
                    sale.setUser(users);
                    sale.setCustomer(customer);
                    sale.setStatus(status);
                    sale.setMonth(sale.getFinishdate().getMonth()+1);
                    Calendar calendar = new GregorianCalendar();
                    calendar.setTime(sale.getFinishdate());
                    sale.setYear(calendar.get(Calendar.YEAR));
                    sale.setStatusName(StatusName.Qualification);
                    sale.setCreated_at(new Date());
                    return salesRepository.save(sale);
                })
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY3, userId));
    }

    @Override
    public Sales updateStatusSales(Long saleId, Sales request) {
        return salesRepository.findById(saleId).map(post->{
            Set<Status> status = new HashSet<>();
            status.add(statusRepository.findByStatusname(request.getStatusName()).get());
            post.setStatus(status);
            post.setStatusName(request.getStatusName());
            salesRepository.save(post);
            return post;
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, saleId));
    }

    @Override
    public ResponseEntity<?> deleteSale(Long saleId) {
        return salesRepository.findById(saleId).map(post -> {
            salesRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, saleId));
    }

    @Override
    public List<Sales> getSaleByUserId(Long userId) {
        return salesRepository.findByUserId(userId);
    }

    @Override
    public List<Sales> getSaleByCustomerId(Long customerId) {
        return salesRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Sales> getSaleByMonth(Integer month) {
        return salesRepository.findByMonth(month);
    }

    @Override
    public List<Sales> getSaleByYear(Integer year) {
        return salesRepository.findByYear(year);
    }

    @Override
    public Long getNumberofSaleByCustomerId(Long customerid) {
        return salesRepository.CountSaleByCustomerId(customerid);
    }

    @Override
    public List<Sales> getSaleByMonthAndYear(Integer month, Integer year) {
        return salesRepository.findByMonthAndYear(month,year);
    }

    @Override
    public List<Sales> getSaleByUserIdAndMonthAndYear(Long userId, Integer month, Integer year) {
        return salesRepository.findByUserIdAndMonthAndYear(userId,month,year);
    }

}
