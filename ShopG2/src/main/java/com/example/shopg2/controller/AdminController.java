package com.example.shopg2.controller;

import com.example.shopg2.entity.ShoeInformation;
import com.example.shopg2.repository.ShoeInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import java.sql.*;
import java.util.ArrayList;

@Controller
public class AdminController {

    @RequestMapping("/admin")
    public String admin() {   return "admin/newAdmin";    }

    @RequestMapping("/admin/manageStock")
    public String manageStock() {   return "admin/newManageStock";   }

    @RequestMapping("/admin/manageBill")
    public String manageBill() {return "admin/newManageBill";}

    @RequestMapping("/changeShoeInformation")
    public String changeShoeInformation() { return  "admin/changeShoeInformation";}

    @RequestMapping("/newShoeInformation")
    public String newShoeInformation() { return  "admin/addShoeInformation";}

    @RequestMapping("/stockIn")
    public String stockIn () {
        return  "admin/stockIn";
    }

    @RequestMapping("/admin/analytic")
    public String analytic () {
        return  "admin/analytic";
    }
}
