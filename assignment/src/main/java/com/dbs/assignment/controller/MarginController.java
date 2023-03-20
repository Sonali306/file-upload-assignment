package com.dbs.assignment.controller;

import com.dbs.assignment.helper.CSVHelper;
import com.dbs.assignment.model.FindMarginPayLoad;
import com.dbs.assignment.model.Margin;
import com.dbs.assignment.service.MarginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class MarginController {
    private static Logger log = LoggerFactory.getLogger(MarginController.class);
    @Autowired
    MarginService marginService;
    @PostMapping("/upload")
    public ResponseEntity<String> uploadMargins(@RequestParam("file") MultipartFile file ){
        String message = "";
        log.debug("File type: "+file.getContentType());
        if(CSVHelper.hasCSVFormat(file)) {
            marginService.save(file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            log.info("File uploaded successfully: "+file.getOriginalFilename());
            return new ResponseEntity<>(message,HttpStatus.OK);
        }
        message = "Invalid File Format";
        log.info("Invalid file format: "+file.getOriginalFilename());
        return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> getFile() {
        String filename = "tutorials.csv";
        InputStreamResource file = new InputStreamResource(marginService.load());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }

    @PostMapping("/find")
    public ResponseEntity<Margin> find(@RequestBody FindMarginPayLoad payLoad){
        Margin margin = marginService.findMarginByPayLoad(payLoad);
        return new ResponseEntity<>(margin,HttpStatus.OK);
    }
}
