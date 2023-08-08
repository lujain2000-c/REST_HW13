package com.example.bankmanagementsystem.Controller;

import com.example.bankmanagementsystem.ApiResponse.ApiResponse;
import com.example.bankmanagementsystem.Model.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/BankManagement")
public class BankManagementController {

    ArrayList<Customer> customers = new ArrayList<>();


  @GetMapping("/get")
  public ArrayList<Customer> getAllCustomers(){
      return customers;
  }

  @PostMapping("/add")
    public ApiResponse addCustomers(@RequestBody Customer newCustomer){
      customers.add(newCustomer);
      return new ApiResponse("Customer Added");
  }

  @PutMapping("/update/{index}")
  public ApiResponse updateCustomers(@PathVariable int index, @RequestBody Customer customer){

      customers.set(index,customer);
      return new ApiResponse("update info");
  }

  @DeleteMapping("/delete/{index}")
  public ApiResponse deletCustomer(@PathVariable int index){

      customers.remove(index);
      return new ApiResponse("delete customer");

  }

  @PutMapping("/depositmoney/{index}")
    public ApiResponse depositMoney(@PathVariable int index){

      double newBalance = customers.get(index).getBalance() + 50000;
      customers.get(index).setBalance(newBalance);
      return new ApiResponse("ok");

  }

    @PutMapping("/withdrawmoney/{index}")
    public ApiResponse withdrawMoney(@PathVariable int index){

      if(customers.get(index).getBalance() >= 5000 && customers.get(index).getBalance() != 0) {

          double newBalance = customers.get(index).getBalance() - 5000;
          customers.get(index).setBalance(newBalance);
      }else{
          return new ApiResponse("Your budget is less than what you want to withdraw");
      }
        return new ApiResponse("ok");

    }



}
