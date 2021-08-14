package com.terminusgroup.ae.controller;


import com.terminusgroup.ae.dto.DeviceDto;
import com.terminusgroup.ae.model.Device;
import com.terminusgroup.ae.repository.DeviceRepository;
import com.terminusgroup.ae.service.iDeviceService;
import com.terminusgroup.ae.util.Message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v2.0/devices")
@CrossOrigin("*")
public class DeviceController {


        @Autowired
        iDeviceService iDeviceService;
        @Autowired
        DeviceRepository deviceRepository;
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @PostMapping("/register")
    public ResponseEntity<Message<String>> register(@Valid @RequestBody DeviceDto deviceDto)
    {


        log.info("Start Registering New Device" + new Date());
        Message<String> message = null;

        try {
            log.info("imei==>"+ deviceDto.getImei());
            message  = iDeviceService.register(deviceDto);
            log.info("Response==> "+message.getStatus() +" " + message.getMessage());



        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }

        return   ResponseEntity.status(message.getStatus()).body(message);






    }

    @PostMapping("/listalldevices")
    public List<Device> listresponsivedevices()

    {


        List<Device> u= deviceRepository.findAllByimeiEnabledtrue();
        return u;
    }

    @PostMapping("/configuredevice")
    public ResponseEntity<Message<String>> configureddevice(@RequestBody DeviceDto deviceDto) {
        log.info("Start Configuring Device");
        try {
            Message<String> res = iDeviceService.configuredevice(deviceDto);
            log.info(res.getMessage());
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            log.info(e.getCause().toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }






}
