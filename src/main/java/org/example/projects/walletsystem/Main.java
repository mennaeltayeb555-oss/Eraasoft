package org.example.walletsystem;

import org.example.walletsystem.service.ApplicationService;
import org.example.walletsystem.service.imp.ApplicationServiceImp;

public class Main {
    public static void main(String[] args) throws Exception {
        ApplicationService applicationService = new ApplicationServiceImp();
        applicationService.start();


    }
}
