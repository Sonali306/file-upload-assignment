package com.dbs.assignment.service;

import com.dbs.assignment.exception.MarginNotFoundException;
import com.dbs.assignment.helper.CSVHelper;
import com.dbs.assignment.model.FindMarginPayLoad;
import com.dbs.assignment.model.Margin;
import com.dbs.assignment.repository.MarginRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MarginService {
    private static Logger log = LoggerFactory.getLogger(MarginService.class);
    @Autowired
    MarginRepository marginRepository;

    public void save(MultipartFile file) {
        try {
            List<Margin> margins = CSVHelper.csvToMargin(file.getInputStream());
            log.debug("---------------------Deleting entries-----------------------");
            marginRepository.deleteAll();
            log.debug("---------------------Saving entries-----------------------");
            marginRepository.saveAll(margins);
        } catch (IOException e) {
            throw new RuntimeException("Failed to store csv data: "+e.getMessage());
        }
    }

    public ByteArrayInputStream load() {
        Iterable<Margin> iterable = marginRepository.findAll();
        List<Margin> margins = new ArrayList<>();
        iterable.forEach(margin->margins.add(margin));
        ByteArrayInputStream result = CSVHelper.marginsToCSV(margins);
        log.debug("Loading data: "+result.toString());
        return result;
    }

    public Margin findMarginByPayLoad (FindMarginPayLoad payLoad) {
        List<Margin> margins = marginRepository.findMarginByPayLoad(payLoad.getInstruction(), payLoad.getBaseCcy(), payLoad.getTermCcy(),payLoad.getTradeTier(),payLoad.getAmount());
        if(margins.size()==0) {
            log.debug("Margin not found for payload: "+payLoad);
            throw new MarginNotFoundException("Margin Not found");
        }
        log.debug("Returning margin: "+margins.get(0));
        return margins.get(0);
    }
}
