package com.terminusgroup.ae.repository;


import com.terminusgroup.ae.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device,Long> {

    Device findOneByimei(String imei);

    @Query(value = "select u from Device u where u.enable= false")
    List<Device> findAllByimeiEnabledfalse();

    @Query(value = "select u from Device u where u.enable= true")
    List<Device> findAllByimeiEnabledtrue();
}
