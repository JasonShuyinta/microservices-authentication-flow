package com.dotjson.service;

import com.dotjson.dao.RestaurantOrderDAO;
import com.dotjson.dto.OrderResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterviewService {
    @Autowired
    private RestaurantOrderDAO orderDAO;

    public String greeting() {
        return "Welcome to Swiggy Restaurant service";
    }

    public OrderResponseDTO getOrder(String orderId) {
        return orderDAO.getOrders(orderId);
    }


}
