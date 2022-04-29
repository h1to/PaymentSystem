package kz.iitu.itse1908.murzaliev.controllers;

import kz.iitu.itse1908.murzaliev.repository.PaymentRepository;
import kz.iitu.itse1908.murzaliev.repository.ProviderRepository;
import kz.iitu.itse1908.murzaliev.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
public class MainController {
    private PaymentRepository paymentRepository;
    private ProviderRepository providerRepository;
    private ServiceRepository serviceRepository;

    @Autowired
    public MainController(PaymentRepository paymentRepository, ProviderRepository providerRepository, ServiceRepository serviceRepository) {
        this.paymentRepository = paymentRepository;
        this.providerRepository = providerRepository;
        this.serviceRepository = serviceRepository;
    }

//    @Autowired
//    private FileStorageService fileStorageService;
//
//    @PostMapping("/uploadFile")
//    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
//        String fileName = fileStorageService.storeFile(file);
//
//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/downloadFile/")
//                .path(fileName)
//                .toUriString();
//
//        return new UploadFileResponse(fileName, fileDownloadUri,
//                file.getContentType(), file.getSize());
//    }
}
