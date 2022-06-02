package com.example.project.global;

import com.example.project.model.Plant;

import java.util.ArrayList;
import java.util.List;

public class GlobalData {
    public static List<Plant> cart;
    static {
        cart = new ArrayList<Plant>();
    }
}
