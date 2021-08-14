package com.terminusgroup.ae.service;


import com.terminusgroup.ae.dto.DeviceDto;
import com.terminusgroup.ae.util.Message.Message;

public interface iDeviceService {

    Message<String> register(DeviceDto deviceDto) throws Exception;
    Message<String> blockdevice(DeviceDto deviceDto);
    Message<String> unblockdevice(DeviceDto deviceDto);
    Message<String> configuredevice(DeviceDto deviceDto);
    Message<String> update(DeviceDto deviceDto);
    Message<String> deletedevice(DeviceDto deviceDto);


}
