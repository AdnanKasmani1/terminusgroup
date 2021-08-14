package com.terminusgroup.ae.service;


import com.terminusgroup.ae.dto.DeviceDto;
import com.terminusgroup.ae.model.Device;
import com.terminusgroup.ae.repository.DeviceRepository;
import com.terminusgroup.ae.util.Message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class DeviceService implements iDeviceService {

    @Autowired
    DeviceRepository deviceRepository;
/*
    @Autowired
    private AmqpTemplate amqpTemplate;
*/

    @Override
    public Message<String> register(DeviceDto deviceDto) throws Exception {
        try {
            Message<String> messages = new Message<>();
            Device calldevice = deviceRepository.findOneByimei(deviceDto.getImei());

            if(calldevice==null)
            {
                String imei = deviceDto.getImei();
                String sim = deviceDto.getSim();
                Device device = new Device();
                device.setImei(imei);
                device.setSim(sim);
                device.setEnable(true);
                device.setConfigured(false);


                deviceRepository.saveAndFlush(device);

    /*            RabbitAdmin admin = new RabbitAdmin(connectionFactory());
                Queue queue = new Queue(imei+".queue");
                admin.declareQueue(queue);
                DirectExchange exchange = new DirectExchange(imei+".direct");
                admin.declareExchange(exchange);
                admin.declareBinding(BindingBuilder.bind(queue).to(exchange).with(imei+".routingkey"));
                Monitoring monitoring = new Monitoring();
                monitoring.setImei(imei);
                monitoring.setTid(tid);
                monitoring.setMn(mn);
                monitoring.setLp("0");
                monitoring.setLt("0");
                monitoringRepository.saveAndFlush(monitoring);*/

                messages.setData("1")
                        .setMessage("Device Register successfully in database")
                        .setStatus(200)
                        .setCode("successful");

                return messages;


            }
            else {

                messages.setData("2")
                        .setMessage("Device Alredy Registered")
                        .setStatus(400)
                        .setCode("Unsuccessful");
                return messages;


            }


        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            Message<String> messages = new Message<>();
            messages.setStatus(400).setMessage("Sim , MerchantName , PAN , Imei already exist in some record."
            ).setCode("unsuccessful");
            messages.setData(e.getCause().getMessage());
            return messages;
        }
    }

    @Override
    public Message<String> blockdevice(DeviceDto deviceDto) {
        try {
            Device calldevice = deviceRepository.findOneByimei(deviceDto.getImei());
            calldevice.setEnable(false);
            deviceRepository.saveAndFlush(calldevice);


            Long userId = calldevice.getId();

            Message<String> messages = new Message<>();
            messages.setData(userId.toString())
                    .setMessage("Device block successfully")
                    .setStatus(200)
                    .setCode("successful");

            //  generateVerificationKeyAndEmail(user);
//            generateConfirmationEmail(user);
            return messages;
        } catch (Exception e) {
            e.printStackTrace();
            Message<String> messages = new Message<>();
            messages.setStatus(400).setMessage("Error Device is not unblock"
            ).setCode("unsuccessful");
            messages.setData(e.getCause().getMessage());
            return messages;
        }
    }

    @Override
    public Message<String> unblockdevice(DeviceDto deviceDto) {
        try {
            Device calldevice = deviceRepository.findOneByimei(deviceDto.getImei());
            calldevice.setEnable(true);
            deviceRepository.saveAndFlush(calldevice);


            Long userId = calldevice.getId();

            Message<String> messages = new Message<>();
            messages.setData(userId.toString())
                    .setMessage("user  updated successfully")
                    .setStatus(200)
                    .setCode("successful");

            //  generateVerificationKeyAndEmail(user);
//            generateConfirmationEmail(user);
            return messages;
        } catch (Exception e) {
            e.printStackTrace();
            Message<String> messages = new Message<>();
            messages.setStatus(400).setMessage("Error Device is not disabled"
            ).setCode("unsuccessful");
            messages.setData(e.getCause().getMessage());
            return messages;
        }
    }

    @Override
    public Message<String> configuredevice(DeviceDto deviceDto) {
        System.out.println(deviceDto);
        Message<String> messages = new Message<>();
        Device calldevice = deviceRepository.findOneByimei(deviceDto.getImei());
        try {

            if(calldevice==null)
            {

                messages.setData("")
                        .setMessage("Device not found")
                        .setStatus(400)
                        .setCode("unsuccessful");


            }
            else
            {
/*
                RabbitAdmin admin = new RabbitAdmin(connectionFactory());
                admin.purgeQueue(calldevice.getImei()+".queue",false);
                amqpTemplate.convertAndSend(calldevice.getImei()+".direct", calldevice.getImei()+".routingkey",calldevice);
*/
              //  calldevice.setConfigured(true);
                deviceRepository.saveAndFlush(calldevice);
                messages.setData("")
                        .setMessage("Device Configured Successfully Waiting for Device to sync with server")
                        .setStatus(200)
                 .setCode("successful");


            }




            //  generateVerificationKeyAndEmail(user);
//            generateConfirmationEmail(user);

        } catch (Exception e) {
            e.printStackTrace();
            messages.setStatus(400).setMessage("Error Device is not configured"
            ).setCode("unsuccessful");
            messages.setData(e.getCause().getMessage());

        }
        return messages;
    }

    @Override
    public Message<String> update(DeviceDto deviceDto) {
        try {


            String imei = deviceDto.getImei();

            Message<String> messages = new Message<>();
            Device calldevice = deviceRepository.findOneByimei(imei);

            if(calldevice==null)
            {



                messages.setData("1")
                        .setMessage("Device not found")
                        .setStatus(400)
                        .setCode("successful");

                return messages;


            }
            else {

                Device device = deviceRepository.findOneByimei(imei);
                device.setConfigured(false);
                deviceRepository.saveAndFlush(device);
                messages.setData("1")
                        .setMessage("Device updated successfully")
                        .setStatus(200)
                        .setCode("successful");

                return messages;


            }


        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            Message<String> messages = new Message<>();
            messages.setStatus(400).setMessage("Error occured might be Sim , MerchantName , PAN , Imei already exist in some record."
            ).setCode("unsuccessful");
            messages.setData(e.getCause().getMessage());
            return messages;
        }
    }

    @Override
    public Message<String> deletedevice(@RequestBody DeviceDto deviceDto) {
        try {
            Message<String> messages = new Message<>();
            Device calldevice = deviceRepository.findOneByimei(deviceDto.getImei());

            if(calldevice==null)
            {



                messages.setData("400")
                        .setMessage("Device not found")
                        .setStatus(400)
                        .setCode("successful");

                return messages;


            }
            else {

                Device device = deviceRepository.findOneByimei(deviceDto.getImei());


                deviceRepository.delete(device);
                messages.setData("1")
                        .setMessage("Device delete successfully")
                        .setStatus(200)
                        .setCode("successful");

                return messages;


            }


        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            Message<String> messages = new Message<>();
            messages.setStatus(400).setMessage("Error occured."
            ).setCode("unsuccessful");
            messages.setData(e.getCause().getMessage());
            return messages;
        }
    }

/*    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(Constants.spring_host);
        connectionFactory.setUsername(Constants.spring_username);
        connectionFactory.setPassword(Constants.spring_pass);
        return connectionFactory;
    }*/
}
